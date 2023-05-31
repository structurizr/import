package com.structurizr.importer.documentation;

import com.structurizr.documentation.Decision;
import com.structurizr.documentation.Documentable;
import com.structurizr.documentation.Format;
import com.structurizr.util.StringUtils;

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
 *
 * Content:
 * # {DECISION_ID}. {DECISION_TITLE}
 *
 * Date: {DECISION_DATE:YYYY-MM-DD}
 *
 * ## Status
 *
 * {DECISION_STATUS and links}
 *
 * ## Context
 * ...
 */
public class MadrToolsDecisionImporter implements DocumentationImporter {

    private static final String STATUS_PROPOSED = "Proposed";
    private static final String STATUS_SUPERSEDED = "Superseded";
    private static final String SUPERCEDED_ALTERNATIVE_SPELLING = "Superceded";

    private static final String DATE_PREFIX = "- Date: ";
    private static final String LINKS_HEADING = "## Links";
    private static final String CONTEXT_HEADING = "## Context";

    private static final Pattern LINK_REGEX = Pattern.compile("(.*) \\[.*]\\((.*)\\)");
    private static final String STATUS_PREFIX = "- Status: ";

    private String dateFormat = "yyyy-MM-dd";
    private TimeZone timeZone = TimeZone.getTimeZone("UTC");

    /**
     * Sets the date format to use when parsing dates (the default is "yyyy-MM-dd").
     *
     * @param dateFormat    a date format, as a String
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Sets the time zone to use when parsing dates (the default is UTC)
     *
     * @param timeZone      a time zone as a String (e.g. "Europe/London" or "UTC")
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = TimeZone.getTimeZone(timeZone);
    }

    /**
     * Sets the time zone to use when parsing dates.
     *
     * @param timeZone      a TimeZone instance
     */
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
            throw new IllegalArgumentException("A workspace, software system, container, or component must be specified.");
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
            Map<String, Decision> decisionsById = new LinkedHashMap<>();

            File[] markdownFiles = path.listFiles((dir, name) -> name.endsWith(".md") && name.matches("^\\d{8}-.*"));
            if (markdownFiles != null) {
                Map<String,Decision> decisionsByFilename = new HashMap<>();

                for (File file : markdownFiles) {
                    Decision decision = importDecision(file);
                    documentable.getDocumentation().addDecision(decision);

                    decisionsById.put(decision.getId(), decision);
                    decisionsByFilename.put(file.getName(), decision);
                }

                for (Decision decision : decisionsById.values()) {
                    extractLinks(decision, decisionsByFilename);

                    // and replace file references, for example "0008-some-decision.md" -> "#8"
                    String content = decision.getContent();
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

    protected Decision importDecision(File file) throws Exception {
        Decision decision = new Decision(file.getName());

        String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        content = content.replace("\r", "");
        decision.setContent(content);

        String[] lines = content.split("\\n");
        decision.setTitle(extractTitle(lines));
        decision.setDate(extractDate(lines));
        decision.setStatus(extractStatus(lines));
        decision.setFormat(Format.Markdown);

        return decision;
    }

    protected String extractTitle(String[] lines) {
        // the title is assumed to be the first line of the content, in the format:
        // # {DECISION_TITLE}
        String titleLine = lines[0];

        return titleLine.substring(2);
    }

    protected Date extractDate(String[] lines) throws Exception {
        // the date is on a line of its own, in the format:
        // - Date: {DECISION_DATE:YYYY-MM-DD}
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(timeZone);

        for (String line : lines) {
            if (line.startsWith(DATE_PREFIX)) {
                String dateAsString = line.substring(DATE_PREFIX.length());

                return sdf.parse(dateAsString);
            }
        }

        return new Date();
    }

    protected String extractStatus(String[] lines) {
        // the status is on a line of its own, in the format:
        // - Status: {STATUS}
        for (String line : lines) {
            if(line.startsWith(STATUS_PREFIX)) {
                String statusInput = line.substring(STATUS_PREFIX.length());
                return Character.toUpperCase(statusInput.charAt(0)) + statusInput.substring(1);
            }
        }

        return STATUS_PROPOSED;
    }

    protected void extractLinks(Decision decision, Map<String,Decision> decisionsByFilename) {
        // MADR allows users to create arbitrary links between ADRs, which reside inside the ## Links section
        String[] lines = decision.getContent().split("\\n");
        boolean inLinksSection = false;
        for (String line : lines) {
            if (!inLinksSection) {
                if (line.startsWith(LINKS_HEADING)) {
                    inLinksSection = true;
                }
            } else {
                if (!StringUtils.isNullOrEmpty(line)) {
                    Matcher matcher = LINK_REGEX.matcher(line);
                    if (matcher.find()) {
                        String linkDescription = matcher.group(1);
                        String markdownFile = matcher.group(2);

                        Decision targetDecision = decisionsByFilename.get(markdownFile);
                        if (targetDecision != null) {
                            decision.addLink(targetDecision, linkDescription);
                        }
                    }
                }
            }
        }
    }

    protected String calculateUrl(Decision decision) throws Exception {
        return "#" + urlEncode(decision.getId());
    }

    protected String urlEncode(String value) throws Exception {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
    }

}