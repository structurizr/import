package com.structurizr.documentation.template;

import com.structurizr.Workspace;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import com.structurizr.documentation.importer.DefaultDocumentationImporter;
import com.structurizr.documentation.template.StructurizrDocumentationTemplate;
import com.structurizr.model.SoftwareSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StructurizrDocumentationTemplateTests {

    private Workspace workspace;
    private StructurizrDocumentationTemplate template;

    @BeforeEach
    public void setUp() {
        workspace = new Workspace("Name", "Description");
        template = new StructurizrDocumentationTemplate(workspace);
    }

    @Test
    public void test_construction_ThrowsAnException_WhenANullTargetIsSpecified() {
        try {
            new StructurizrDocumentationTemplate(null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("A workspace or software system must be specified.", iae.getMessage());
        }
    }

    @Test
    public void test_addAllSectionsWithContentAsStrings() {
        Section section;

        section = template.addContextSection(Format.Markdown, "Context section");
        assertSection("Context", Format.Markdown, "Context section", 1, section);

        section = template.addFunctionalOverviewSection(Format.Markdown, "Functional overview section");
        assertSection("Functional Overview", Format.Markdown, "Functional overview section", 2, section);

        section = template.addQualityAttributesSection(Format.Markdown, "Quality attributes section");
        assertSection("Quality Attributes", Format.Markdown, "Quality attributes section", 3, section);

        section = template.addConstraintsSection(Format.Markdown, "Constraints section");
        assertSection("Constraints", Format.Markdown, "Constraints section", 4, section);

        section = template.addPrinciplesSection(Format.Markdown, "Principles section");
        assertSection("Principles", Format.Markdown, "Principles section", 5, section);

        section = template.addSoftwareArchitectureSection(Format.Markdown, "Software architecture section");
        assertSection("Software Architecture", Format.Markdown, "Software architecture section", 6, section);

        section = template.addContainersSection(Format.Markdown, "Containers section");
        assertSection("Containers", Format.Markdown, "Containers section", 7, section);

        section = template.addComponentsSection(Format.Markdown, "Components section");
        assertSection("Components", Format.Markdown, "Components section", 8, section);

        section = template.addCodeSection(Format.Markdown, "Code section");
        assertSection("Code", Format.Markdown, "Code section", 9, section);

        section = template.addDataSection(Format.Markdown, "Data section");
        assertSection("Data", Format.Markdown, "Data section", 10, section);

        section = template.addInfrastructureArchitectureSection(Format.Markdown, "Infrastructure architecture section");
        assertSection("Infrastructure Architecture", Format.Markdown, "Infrastructure architecture section", 11, section);

        section = template.addDeploymentSection(Format.Markdown, "Deployment section");
        assertSection("Deployment", Format.Markdown, "Deployment section", 12, section);

        section = template.addDevelopmentEnvironmentSection(Format.Markdown, "Development environment section");
        assertSection("Development Environment", Format.Markdown, "Development environment section", 13, section);

        section = template.addOperationAndSupportSection(Format.Markdown, "Operation and support section");
        assertSection("Operation and Support", Format.Markdown, "Operation and support section", 14, section);

        section = template.addDecisionLogSection(Format.Markdown, "Decision log section");
        assertSection("Decision Log", Format.Markdown, "Decision log section", 15, section);
    }

    @Test
    public void test_addAllSectionsWithContentFromFiles() throws IOException {
        Section section;
        File root = new File("./src/test/java/com/structurizr/documentation/template/structurizr");

        section = template.addContextSection(new File(root, "context.md"));
        assertSection("Context", Format.Markdown, "Context section", 1, section);

        section = template.addFunctionalOverviewSection(new File(root, "functional-overview.md"));
        assertSection("Functional Overview", Format.Markdown, "Functional overview section", 2, section);

        section = template.addQualityAttributesSection(new File(root, "quality-attributes.md"));
        assertSection("Quality Attributes", Format.Markdown, "Quality attributes section", 3, section);

        section = template.addConstraintsSection(new File(root, "constraints.md"));
        assertSection("Constraints", Format.Markdown, "Constraints section", 4, section);

        section = template.addPrinciplesSection(new File(root, "principles.md"));
        assertSection("Principles", Format.Markdown, "Principles section", 5, section);

        section = template.addSoftwareArchitectureSection(new File(root, "software-architecture.md"));
        assertSection("Software Architecture", Format.Markdown, "Software architecture section", 6, section);

        section = template.addContainersSection(new File(root, "containers.md"));
        assertSection("Containers", Format.Markdown, "Containers section", 7, section);

        section = template.addComponentsSection(new File(root, "components.md"));
        assertSection("Components", Format.Markdown, "Components section", 8, section);

        section = template.addCodeSection(new File(root, "code.md"));
        assertSection("Code", Format.Markdown, "Code section", 9, section);

        section = template.addDataSection(new File(root, "data.md"));
        assertSection("Data", Format.Markdown, "Data section", 10, section);

        section = template.addInfrastructureArchitectureSection(new File(root, "infrastructure-architecture.md"));
        assertSection("Infrastructure Architecture", Format.Markdown, "Infrastructure architecture section", 11, section);

        section = template.addDeploymentSection(new File(root, "deployment.md"));
        assertSection("Deployment", Format.Markdown, "Deployment section", 12, section);

        section = template.addDevelopmentEnvironmentSection(new File(root, "development-environment.md"));
        assertSection("Development Environment", Format.Markdown, "Development environment section", 13, section);

        section = template.addOperationAndSupportSection(new File(root, "operation-and-support.md"));
        assertSection("Operation and Support", Format.Markdown, "Operation and support section", 14, section);

        section = template.addDecisionLogSection(new File(root, "decision-log.md"));
        assertSection("Decision Log", Format.Markdown, "Decision log section", 15, section);
    }

    private void assertSection(String title, Format format, String content, int order, Section section) {
        assertTrue(workspace.getDocumentation().getSections().contains(section));
        assertEquals(title, section.getTitle());
        assertEquals(format, section.getFormat());
        assertEquals(content, section.getContent());
        assertEquals(order, section.getOrder());
    }

}