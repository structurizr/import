package com.structurizr.documentation.importer;

import com.structurizr.documentation.Decision;
import com.structurizr.documentation.Documentable;
import com.structurizr.documentation.Format;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Imports architecture decision records created/managed by adr-tools (https://github.com/npryce/adr-tools).
 * The format for ADRs is as follows:
 *
 * Filename: {DECISION_ID:0000}-*.md
 * Content:
 * # {DECISION_ID}. {DECISION_TITLE}
 *
 * Date: {DECISION_DATE:YYYY-MM-DD}
 *
 * ## Status
 *
 * {DECISION_STATUS}
 *
 * {DECISION_CONTENT}
 */
public class AdrToolsDecisionImporter implements DocumentationImporter {

    private static final Pattern titleRegex = Pattern.compile("^# \\d*\\. (.*)$", Pattern.MULTILINE);
    private static final Pattern dateRegex = Pattern.compile("^Date: (\\d\\d\\d\\d-\\d\\d-\\d\\d)$",Pattern.MULTILINE);
    private static final Pattern statusRegex = Pattern.compile("## Status\\n\\n(\\w*)");

    private static final Pattern SUPERSEDED_BY_LINK_REGEX = Pattern.compile("^Superseded by \\[.*\\]\\((.*)\\)$", Pattern.MULTILINE);
    private static final Pattern SUPERCEDED_BY_LINK_REGEX = Pattern.compile("^Superceded by \\[.*\\]\\((.*)\\)$", Pattern.MULTILINE);
    private static final Pattern SUPERSEDES_LINK_REGEX = Pattern.compile("^Supersedes \\[.*\\]\\((.*)\\)$", Pattern.MULTILINE);
    private static final Pattern SUPERCEDES_LINK_REGEX = Pattern.compile("^Supercedes \\[.*\\]\\((.*)\\)$", Pattern.MULTILINE);
    private static final Pattern AMENDED_BY_LINK_REGEX = Pattern.compile("^Amended by \\[.*\\]\\((.*)\\)$", Pattern.MULTILINE);
    private static final Pattern REQUIRES_LINK_REGEX = Pattern.compile("^requires \\[.*\\]\\((.*)\\)$", Pattern.MULTILINE);
    private static final Pattern REFERENCE_LINK_REGEX = Pattern.compile("\\[.*\\]\\((.*)\\)", Pattern.MULTILINE);

    private static final String STATUS_PROPOSED = "Proposed";
    private static final String STATUS_SUPERSEDED = "Superseded";
    private static final String SUPERCEDED_ALTERNATIVE_SPELLING = "Superceded";

    private static final String LINK_SUPERSEDED_BY = "SupersededBy";
    private static final String LINK_SUPERSEDES = "Supersedes";
    private static final String LINK_AMENDED_BY = "AmendedBy";
    private static final String LINK_REQUIRES = "Requires";
    private static final String LINK_REFERENCES = "References";

    private String dateFormat = "yyyy-MM-dd";
    private TimeZone timeZone = TimeZone.getTimeZone("UTC");

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = TimeZone.getTimeZone(timeZone);
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Imports Markdown files from the specified path, one per decision.
     *
     * @param documentable      the item that documentation should be associated with
     * @param path              the path to import documentation from
     */
    @Override
    public void importDocumentation(Documentable documentable, File path) {
        if (documentable == null) {
            throw new IllegalArgumentException("A workspace or software system must be specified.");
        }

        if (path == null) {
            throw new IllegalArgumentException("A path must be specified.");
        } else if (!path.exists()) {
            throw new IllegalArgumentException(path.getAbsolutePath() + " does not exist.");
        }

        if (!path.isDirectory()) {
            throw new IllegalArgumentException(path.getAbsolutePath() + " is not a directory.");
        }

        try {
            Map<String,Decision> decisionsById = new LinkedHashMap<>();

            File[] markdownFiles = path.listFiles((dir, name) -> name.endsWith(".md"));
            if (markdownFiles != null) {
                Map<String,Decision> decisionsByFilename = new HashMap<>();

                for (File file : markdownFiles) {
                    Decision decision = importDecision(file);
                    documentable.getDocumentation().addDecision(decision);

                    decisionsById.put(decision.getId(), decision);
                    decisionsByFilename.put(file.getName(), decision);
                }

                for (Decision decision : decisionsById.values()) {
                    String content = decision.getContent();

                    // calculate any links between this and other ADRs
                    addLink(SUPERSEDED_BY_LINK_REGEX, decision, decisionsByFilename, decisionsById, LINK_SUPERSEDED_BY, true);
                    addLink(SUPERCEDED_BY_LINK_REGEX, decision, decisionsByFilename, decisionsById, LINK_SUPERSEDED_BY, true);
                    addLink(SUPERSEDES_LINK_REGEX, decision, decisionsByFilename, decisionsById, LINK_SUPERSEDES, true);
                    addLink(SUPERCEDES_LINK_REGEX, decision, decisionsByFilename, decisionsById, LINK_SUPERSEDES, true);
                    addLink(AMENDED_BY_LINK_REGEX, decision, decisionsByFilename, decisionsById, LINK_AMENDED_BY, true);
                    addLink(REQUIRES_LINK_REGEX, decision, decisionsByFilename, decisionsById, LINK_REQUIRES, true);
                    addLink(REFERENCE_LINK_REGEX, decision, decisionsByFilename, decisionsById, LINK_REFERENCES, false);

                    // and replace file references, for example "0008-some-decision.md" -> "#8"
                    for (String filename : decisionsByFilename.keySet()) {
                        content = content.replace(filename, calculateUrl(decisionsByFilename.get(filename)));
                    }

                    decision.setContent(content);
                }
            }
        } catch (Exception e) {
            throw new DocumentationImportException(e);
        }
    }

    private Decision importDecision(File file) throws Exception {
        String id = extractIntegerIDFromFileName(file);
        Decision decision = new Decision(id);

        String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        content = content.replace("\r", "");
        decision.setContent(content);

        decision.setTitle(extractTitle(content));
        decision.setDate(extractDate(content));
        decision.setStatus(extractStatus(content));
        decision.setFormat(Format.Markdown);

        return decision;
    }

    private void addLink(Pattern pattern, Decision decision, Map<String,Decision> index, Map<String,Decision> decisionMap, String linkType, boolean addAlways) {
        Matcher matcher = pattern.matcher(decision.getContent());
        while (matcher.find()) {
            String filename = matcher.group(1);

            if (index.containsKey(filename)) {
                String targetId = index.get(filename).getId();
                Decision targetDecision = decisionMap.get(targetId);

                if (addAlways || !decision.hasLinkTo(targetDecision)) {
                    decision.addLink(targetDecision, linkType);
                }
            }
        }
    }

    private String extractIntegerIDFromFileName(File file) {
        return "" + Integer.parseInt(file.getName().substring(0, 4));
    }

    private String extractTitle(String content) {
        Matcher matcher = titleRegex.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Untitled";
        }
    }

    private Date extractDate(String content) throws Exception {
        Matcher matcher = dateRegex.matcher(content);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(timeZone);

        if (matcher.find()) {
            return sdf.parse(matcher.group(1));
        } else {
            return new Date();
        }
    }

    private String extractStatus(String content) {
        Matcher matcher = statusRegex.matcher(content);
        if (matcher.find()) {
            String status = matcher.group(1);

            if (status.equals(SUPERCEDED_ALTERNATIVE_SPELLING)) {
                return STATUS_SUPERSEDED;
            } else {
                return status;
            }
        } else {
            return STATUS_PROPOSED;
        }
    }

    protected String calculateUrl(Decision decision) throws Exception {
        return "#" + urlEncode(decision.getId());
    }

    protected String urlEncode(String value) throws Exception {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
    }

}