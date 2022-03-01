package com.structurizr.documentation.importer;

import com.structurizr.Workspace;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultDocumentImporterTests {

    private Workspace workspace;
    private DefaultDocumentationImporter documentationImporter;

    @BeforeEach
    public void setUp() {
        documentationImporter = new DefaultDocumentationImporter();
        workspace = new Workspace("Name", "Description");
    }

    @Test
    public void test_importDocumentation_ThrowsAnException_WhenANullDocumentableIsSpecified() {
        try {
            documentationImporter.importDocumentation(null, null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("A workspace or software system must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_importDocumentation_ThrowsAnException_WhenANullPathIsSpecified() {
        try {
            documentationImporter.importDocumentation(workspace, null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("A path must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_importDocumentation_ThrowsAnException_WhenThePathDoesNotExist() {
        try {
            File directory = new File("foo");
            assertFalse(directory.exists());
            documentationImporter.importDocumentation(workspace, directory);
            fail();
        } catch (IllegalArgumentException iae) {
            assertTrue(iae.getMessage().endsWith("foo does not exist."));
        }
    }

    @Test
    public void test_importDocumentation() {
        File directory = new File("./src/test/java/com/structurizr/documentation/importer/docs");
        documentationImporter.importDocumentation(workspace, directory);
        Set<Section> sections = workspace.getDocumentation().getSections();
        assertEquals(6, sections.size());

        assertSection("Section 1", Format.Markdown, "## Section 1", 1, sections.stream().filter(s -> s.getTitle().equals("Section 1")).findFirst().get());
        assertSection("Section 2", Format.Markdown, "## Section 2", 2, sections.stream().filter(s -> s.getTitle().equals("Section 2")).findFirst().get());
        assertSection("Section 3", Format.Markdown, "## Section 3", 3, sections.stream().filter(s -> s.getTitle().equals("Section 3")).findFirst().get());
        assertSection("Section 4", Format.AsciiDoc, "== Section 4", 4, sections.stream().filter(s -> s.getTitle().equals("Section 4")).findFirst().get());
        assertSection("Section 5", Format.AsciiDoc, "== Section 5", 5, sections.stream().filter(s -> s.getTitle().equals("Section 5")).findFirst().get());
        assertSection("Section 6", Format.AsciiDoc, "== Section 6", 6, sections.stream().filter(s -> s.getTitle().equals("Section 6")).findFirst().get());
    }

    private void assertSection(String title, Format format, String content, int order, Section section) {
        assertTrue(workspace.getDocumentation().getSections().contains(section));
        assertEquals(title, section.getTitle());
        assertEquals(format, section.getFormat());
        assertEquals(content, section.getContent());
        assertEquals(order, section.getOrder());
    }

}