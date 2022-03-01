package com.structurizr.documentation.template;

import com.structurizr.Workspace;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import com.structurizr.documentation.importer.DefaultDocumentationImporter;
import com.structurizr.documentation.template.ViewpointsAndPerspectivesDocumentationTemplate;
import com.structurizr.model.SoftwareSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ViewpointsAndPerspectivesDocumentationTemplateTests {

    private Workspace workspace;
    private ViewpointsAndPerspectivesDocumentationTemplate template;

    @BeforeEach
    public void setUp() {
        workspace = new Workspace("Name", "Description");
        template = new ViewpointsAndPerspectivesDocumentationTemplate(workspace);
    }

    @Test
    public void test_construction_ThrowsAnException_WhenANullTargetIsSpecified() {
        try {
            new ViewpointsAndPerspectivesDocumentationTemplate(null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("A workspace or software system must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_addAllSectionsWithContentAsStrings() {
        Section section;

        section = template.addIntroductionSection(Format.Markdown, "Section 1");
        assertSection("Introduction", Format.Markdown, "Section 1", 1, section);

        section = template.addGlossarySection(Format.Markdown, "Section 2");
        assertSection("Glossary", Format.Markdown, "Section 2", 2, section);

        section = template.addSystemStakeholdersAndRequirementsSection(Format.Markdown, "Section 3");
        assertSection("System Stakeholders and Requirements", Format.Markdown, "Section 3", 3, section);

        section = template.addArchitecturalForcesSection(Format.Markdown, "Section 4");
        assertSection("Architectural Forces", Format.Markdown, "Section 4", 4, section);

        section = template.addArchitecturalViewsSection(Format.Markdown, "Section 5");
        assertSection("Architectural Views", Format.Markdown, "Section 5", 5, section);

        section = template.addSystemQualitiesSection(Format.Markdown, "Section 6");
        assertSection("System Qualities", Format.Markdown, "Section 6", 6, section);

        section = template.addAppendicesSection(Format.Markdown, "Section 7");
        assertSection("Appendices", Format.Markdown, "Section 7", 7, section);
    }

    @Test
    public void test_addAllSectionsWithContentFromFiles() throws IOException {
        Section section;
        File root = new File("./src/test/java/com/structurizr/documentation/template/viewpointsandperspectives");

        section = template.addIntroductionSection(new File(root, "01-introduction.md"));
        assertSection("Introduction", Format.Markdown, "Section 1", 1, section);

        section = template.addGlossarySection(new File(root, "02-glossary.md"));
        assertSection("Glossary", Format.Markdown, "Section 2", 2, section);

        section = template.addSystemStakeholdersAndRequirementsSection(new File(root, "03-system-stakeholders-and-requirements.md"));
        assertSection("System Stakeholders and Requirements", Format.Markdown, "Section 3", 3, section);

        section = template.addArchitecturalForcesSection(new File(root, "04-architectural-forces.md"));
        assertSection("Architectural Forces", Format.Markdown, "Section 4", 4, section);

        section = template.addArchitecturalViewsSection(new File(root, "05-architectural-views.md"));
        assertSection("Architectural Views", Format.Markdown, "Section 5", 5, section);

        section = template.addSystemQualitiesSection(new File(root, "06-system-qualities.md"));
        assertSection("System Qualities", Format.Markdown, "Section 6", 6, section);

        section = template.addAppendicesSection(new File(root, "07-appendices.adoc"));
        assertSection("Appendices", Format.AsciiDoc, "Section 7", 7, section);
    }

    private void assertSection(String title, Format format, String content, int order, Section section) {
        assertTrue(workspace.getDocumentation().getSections().contains(section));
        assertEquals(title, section.getTitle());
        assertEquals(format, section.getFormat());
        assertEquals(content, section.getContent());
        assertEquals(order, section.getOrder());
    }

}