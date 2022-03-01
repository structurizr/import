package com.structurizr.documentation.importer;

import com.structurizr.Workspace;
import com.structurizr.documentation.Decision;
import com.structurizr.documentation.Documentation;
import com.structurizr.documentation.Format;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;


public class AdrToolsDecisionImporterTests {

    private AdrToolsDecisionImporter decisionImporter;
    private Workspace workspace;
    private Documentation documentation;

    @BeforeEach
    public void setUp() {
        decisionImporter = new AdrToolsDecisionImporter();
        workspace = new Workspace("Name", "Description");
        documentation = workspace.getDocumentation();
    }

    @Test
    public void test_importDocumentation_ThrowsAnException_WhenANullDocumentableIsSpecified() {
        try {
            decisionImporter.importDocumentation(null, null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("A workspace or software system must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_importDocumentation_ThrowsAnException_WhenADirectoryIsNotSpecified()  {
        try {
            decisionImporter.importDocumentation(workspace, null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("A path must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_importDocumentation_ThrowsAnException_WhenADirectoryIsSpecifiedButItDoesNotExist() {
        try {
            File directory = new File("foo");
            assertFalse(directory.exists());
            decisionImporter.importDocumentation(workspace, directory);
            fail();
        } catch (IllegalArgumentException iae) {
            assertTrue(iae.getMessage().endsWith("foo does not exist."));
        }
    }

    @Test
    public void test_importDocumentation_ThrowsAnException_WhenAPathIsSpecifiedButItIsNotADirectory() {
        try {
            decisionImporter.importDocumentation(workspace, new File("build.gradle"));
            fail();
        } catch (IllegalArgumentException iae) {
            assertTrue(iae.getMessage().endsWith("/build.gradle is not a directory."));
        }
    }

    @Test
    public void test_importDecisions() {
        decisionImporter.importDocumentation(workspace, new File("src/test/adrs"));

        assertEquals(10, documentation.getDecisions().size());

        Decision decision1 = documentation.getDecisions().stream().filter(d -> d.getId().equals("1")).findFirst().get();
        assertEquals("1", decision1.getId());
        assertEquals("Record architecture decisions", decision1.getTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss ZZZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals("12-Feb-2016 00:00:00 +0000", sdf.format(decision1.getDate()));
        assertEquals("Accepted", decision1.getStatus());
        Assertions.assertEquals(Format.Markdown, decision1.getFormat());
        assertEquals("# 1. Record architecture decisions\n" +
                        "\n" +
                        "Date: 2016-02-12\n" +
                        "\n" +
                        "## Status\n" +
                        "\n" +
                        "Accepted\n" +
                        "\n" +
                        "## Context\n" +
                        "\n" +
                        "We need to record the architectural decisions made on this project.\n" +
                        "\n" +
                        "![](images/structurizr-banner.png)\n" +
                        "\n" +
                        "## Decision\n" +
                        "\n" +
                        "We will use Architecture Decision Records, as described by Michael Nygard in this article: http://thinkrelevance.com/blog/2011/11/15/documenting-architecture-decisions\n" +
                        "\n" +
                        "## Consequences\n" +
                        "\n" +
                        "See Michael Nygard's article, linked above.\n",
                decision1.getContent());
    }

    @Test
    public void test_importDocumentation_RewritesLinksBetweenDecisions() {
        decisionImporter.importDocumentation(workspace, new File("src/test/adrs"));

        Decision decision5 = documentation.getDecisions().stream().filter(d -> d.getId().equals("5")).findFirst().get();
        assertTrue(decision5.getContent().contains("Amended by [9. Help scripts](#9)"));
    }

    @Test
    public void test_importDocumentation_SupportsTheIncorrectSpellingOfSuperseded() {
        decisionImporter.importDocumentation(workspace, new File("src/test/adrs"));

        Decision decision4 = documentation.getDecisions().stream().filter(d -> d.getId().equals("4")).findFirst().get();
        assertEquals("Superseded", decision4.getStatus());
        assertTrue(decision4.getContent().contains("Superceded by [10. AsciiDoc format](#10)"));
    }

}