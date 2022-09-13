package com.structurizr.documentation.importer;

import com.structurizr.Workspace;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecursiveDefaultDocumentImporterTests {

    private Workspace workspace;
    private RecursiveDefaultDocumentationImporter documentationImporter;

    @BeforeEach
    public void setUp() {
        documentationImporter = new RecursiveDefaultDocumentationImporter();
        workspace = new Workspace("Name", "Description");
    }

    @Test
    public void test_importDocumentation_WithRecursiveSetToTrue() {
        File directory = new File("./src/test/java/com/structurizr/documentation/importer/docs");

        documentationImporter.importDocumentation(workspace, directory);
        Set<Section> sections = workspace.getDocumentation().getSections();
        assertEquals(7, sections.size());

        assertSection("Section 1", Format.Markdown, "## Section 1", 1, "01-section-1.md", sections.stream().filter(s -> s.getTitle().equals("Section 1")).findFirst().get());
        assertSection("Section 2", Format.Markdown, "## Section 2", 2, "02-section-2.markdown", sections.stream().filter(s -> s.getTitle().equals("Section 2")).findFirst().get());
        assertSection("Section 3", Format.Markdown, "## Section 3", 3, "03-section-3.text", sections.stream().filter(s -> s.getTitle().equals("Section 3")).findFirst().get());
        assertSection("Section 4", Format.AsciiDoc, "== Section 4", 4, "04-section-4.adoc", sections.stream().filter(s -> s.getTitle().equals("Section 4")).findFirst().get());
        assertSection("Section 5", Format.AsciiDoc, "== Section 5", 5, "05-section-5.asciidoc", sections.stream().filter(s -> s.getTitle().equals("Section 5")).findFirst().get());
        assertSection("Section 6", Format.AsciiDoc, "== Section 6", 6, "06-section-6.asc", sections.stream().filter(s -> s.getTitle().equals("Section 6")).findFirst().get());
        assertSection("Section 7", Format.Markdown, "## Section 7", 7, "07-subdirectory/01-section-1.md", sections.stream().filter(s -> s.getTitle().equals("Section 7")).findFirst().get());
    }

    private void assertSection(String title, Format format, String content, int order, String filename, Section section) {
        assertTrue(workspace.getDocumentation().getSections().contains(section));
        assertEquals(title, section.getTitle());
        assertEquals(format, section.getFormat());
        assertEquals(content, section.getContent());
        assertEquals(order, section.getOrder());
        assertEquals(filename, section.getFilename());
    }

}