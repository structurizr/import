package com.structurizr.documentation.template;

import com.structurizr.Workspace;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import com.structurizr.documentation.importer.DefaultDocumentationImporter;
import com.structurizr.documentation.template.Arc42DocumentationTemplate;
import com.structurizr.model.SoftwareSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Arc42DocumentationTemplateTests {

    private Workspace workspace;
    private Arc42DocumentationTemplate template;

    @BeforeEach
    public void setUp() {
        workspace = new Workspace("Name", "Description");
        template = new Arc42DocumentationTemplate(workspace);
    }

    @Test
    public void test_construction_ThrowsAnException_WhenANullTargetIsSpecified() {
        try {
            new Arc42DocumentationTemplate(null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("A workspace or software system must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_addAllSectionsWithContentAsStrings() {
        Section section;

        section = template.addIntroductionAndGoalsSection(Format.Markdown, "Section 1");
        assertSection("Introduction and Goals", Format.Markdown, "Section 1", 1, section);

        section = template.addConstraintsSection(Format.Markdown, "Section 2");
        assertSection("Constraints", Format.Markdown, "Section 2", 2, section);

        section = template.addContextAndScopeSection(Format.Markdown, "Section 3");
        assertSection("Context and Scope", Format.Markdown, "Section 3", 3, section);

        section = template.addSolutionStrategySection(Format.Markdown, "Section 4");
        assertSection("Solution Strategy", Format.Markdown, "Section 4", 4, section);

        section = template.addBuildingBlockViewSection(Format.Markdown, "Section 5");
        assertSection("Building Block View", Format.Markdown, "Section 5", 5, section);

        section = template.addRuntimeViewSection(Format.Markdown, "Section 6");
        assertSection("Runtime View", Format.Markdown, "Section 6", 6, section);

        section = template.addDeploymentViewSection(Format.Markdown, "Section 7");
        assertSection("Deployment View", Format.Markdown, "Section 7", 7, section);

        section = template.addCrosscuttingConceptsSection(Format.Markdown, "Section 8");
        assertSection("Crosscutting Concepts", Format.Markdown, "Section 8", 8, section);

        section = template.addArchitecturalDecisionsSection(Format.Markdown, "Section 9");
        assertSection("Architectural Decisions", Format.Markdown, "Section 9", 9, section);

        section = template.addQualityRequirementsSection(Format.Markdown, "Section 10");
        assertSection("Quality Requirements", Format.Markdown, "Section 10", 10, section);

        section = template.addRisksAndTechnicalDebtSection(Format.Markdown, "Section 11");
        assertSection("Risks and Technical Debt", Format.Markdown, "Section 11", 11, section);

        section = template.addGlossarySection(Format.Markdown, "Section 12");
        assertSection("Glossary", Format.Markdown, "Section 12", 12, section);
    }

    @Test
    public void test_addAllSectionsWithContentFromFiles() throws IOException {
        Section section;
        File root = new File("./src/test/java/com/structurizr/documentation/template/arc42");

        section = template.addIntroductionAndGoalsSection(new File(root, "introduction-and-goals.md"));
        assertSection("Introduction and Goals", Format.Markdown, "Section 1", 1, section);

        section = template.addConstraintsSection(new File(root, "constraints.md"));
        assertSection("Constraints", Format.Markdown, "Section 2", 2, section);

        section = template.addContextAndScopeSection(new File(root, "context-and-scope.md"));
        assertSection("Context and Scope", Format.Markdown, "Section 3", 3, section);

        section = template.addSolutionStrategySection(new File(root, "solution-strategy.md"));
        assertSection("Solution Strategy", Format.Markdown, "Section 4", 4, section);

        section = template.addBuildingBlockViewSection(new File(root, "building-block-view.md"));
        assertSection("Building Block View", Format.Markdown, "Section 5", 5, section);

        section = template.addRuntimeViewSection(new File(root, "runtime-view.md"));
        assertSection("Runtime View", Format.Markdown, "Section 6", 6, section);

        section = template.addDeploymentViewSection(new File(root, "deployment-view.md"));
        assertSection("Deployment View", Format.Markdown, "Section 7", 7, section);

        section = template.addCrosscuttingConceptsSection(new File(root, "crosscutting-concepts.md"));
        assertSection("Crosscutting Concepts", Format.Markdown, "Section 8", 8, section);

        section = template.addArchitecturalDecisionsSection(new File(root, "architectural-decisions.md"));
        assertSection("Architectural Decisions", Format.Markdown, "Section 9", 9, section);

        section = template.addQualityRequirementsSection(new File(root, "quality-requirements.md"));
        assertSection("Quality Requirements", Format.Markdown, "Section 10", 10, section);

        section = template.addRisksAndTechnicalDebtSection(new File(root, "risks-and-technical-debt.md"));
        assertSection("Risks and Technical Debt", Format.Markdown, "Section 11", 11, section);

        section = template.addGlossarySection(new File(root, "glossary.md"));
        assertSection("Glossary", Format.Markdown, "Section 12", 12, section);
    }

    private void assertSection(String title, Format format, String content, int order, Section section) {
        assertTrue(workspace.getDocumentation().getSections().contains(section));
        assertEquals(title, section.getTitle());
        assertEquals(format, section.getFormat());
        assertEquals(content, section.getContent());
        assertEquals(order, section.getOrder());
    }

}