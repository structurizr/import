package com.structurizr.documentation.template;

import com.structurizr.Workspace;
import com.structurizr.documentation.Documentation;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentationTemplateTests {

    private StructurizrDocumentationTemplate template;
    private Documentation documentation;

    @BeforeEach
    public void setUp() {
        Workspace workspace = new Workspace("Name", "Description");
        template = new StructurizrDocumentationTemplate(workspace);
        documentation = workspace.getDocumentation();
    }

    @Test
    public void test_addSection_ThrowsAnException_WhenThatSectionAlreadyExists() {
        template.addContextSection(Format.Markdown, "Some Markdown content");
        assertEquals(1, documentation.getSections().size());

        try {
            template.addContextSection(Format.Markdown, "Some Markdown content");
            fail();
        } catch (IllegalArgumentException iae) {
            // this is the expected exception
            assertEquals("A section with a title of Context already exists in this scope.", iae.getMessage());
            assertEquals(1, documentation.getSections().size());
        }
    }

    @Test
    public void test_readFiles_ThrowsAnException_WhenPassedAFileThatDoesNotExist() throws IOException {
        try {
            template.addContextSection(new File("./no-such-file.txt"));
            fail();
        } catch (IllegalArgumentException iae) {
            assertTrue(iae.getMessage().endsWith("no-such-file.txt does not exist."));
        }
    }

    @Test
    public void test_readFiles_ThrowsAnException_WhenPassedANullFile() throws IOException {
        try {
            template.addContextSection((File)null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("One or more files must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_readFiles_ThrowsAnException_WhenPassedAnEmptyCollection() throws IOException {
        try {
            template.addContextSection(new File[]{});
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("One or more files must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_readFiles_AddsAllFiles_WhenPassedADirectory() throws IOException {
        Section section = template.addContextSection(new File("./src/test/java/com/structurizr/documentation/template/markdown"));
        assertEquals("File 1" + System.lineSeparator() +
                "File 2", section.getContent());
    }

}