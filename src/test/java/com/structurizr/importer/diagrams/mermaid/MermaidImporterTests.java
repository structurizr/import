package com.structurizr.importer.diagrams.mermaid;

import com.structurizr.Workspace;
import com.structurizr.view.ImageView;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MermaidImporterTests {

    @Test
    public void importDiagram() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        workspace.getViews().getConfiguration().addProperty("mermaid.url", "https://mermaid.ink");
        ImageView view = workspace.getViews().createImageView("key");

        new MermaidImporter().importDiagram(view, new File("./src/test/diagrams/mermaid/flowchart.mmd"));
        assertEquals("key", view.getKey());
        assertNull(view.getElement());
        assertNull(view.getElementId());
        assertEquals("flowchart.mmd", view.getTitle());
        assertEquals("https://mermaid.ink/img/Zmxvd2NoYXJ0IFRECiAgICBBW0NocmlzdG1hc10gLS0-fEdldCBtb25leXwgQihHbyBzaG9wcGluZykKICAgIEIgLS0-IEN7TGV0IG1lIHRoaW5rfQogICAgQyAtLT58T25lfCBEW0xhcHRvcF0KICAgIEMgLS0-fFR3b3wgRVtpUGhvbmVdCiAgICBDIC0tPnxUaHJlZXwgRltmYTpmYS1jYXIgQ2FyXQ==?type=png", view.getContent());
        assertEquals("image/png", view.getContentType());
    }

    @Test
    public void importDiagram_AsPNG() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        workspace.getViews().getConfiguration().addProperty("mermaid.url", "https://mermaid.ink");
        workspace.getViews().getConfiguration().addProperty("mermaid.format", "png");
        ImageView view = workspace.getViews().createImageView("key");

        new MermaidImporter().importDiagram(view, new File("./src/test/diagrams/mermaid/flowchart.mmd"));
        assertEquals("key", view.getKey());
        assertNull(view.getElement());
        assertNull(view.getElementId());
        assertEquals("flowchart.mmd", view.getTitle());
        assertEquals("https://mermaid.ink/img/Zmxvd2NoYXJ0IFRECiAgICBBW0NocmlzdG1hc10gLS0-fEdldCBtb25leXwgQihHbyBzaG9wcGluZykKICAgIEIgLS0-IEN7TGV0IG1lIHRoaW5rfQogICAgQyAtLT58T25lfCBEW0xhcHRvcF0KICAgIEMgLS0-fFR3b3wgRVtpUGhvbmVdCiAgICBDIC0tPnxUaHJlZXwgRltmYTpmYS1jYXIgQ2FyXQ==?type=png", view.getContent());
        assertEquals("image/png", view.getContentType());
    }

    @Test
    public void importDiagram_AsSVG() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        workspace.getViews().getConfiguration().addProperty("mermaid.url", "https://mermaid.ink");
        workspace.getViews().getConfiguration().addProperty("mermaid.format", "svg");
        ImageView view = workspace.getViews().createImageView("key");

        new MermaidImporter().importDiagram(view, new File("./src/test/diagrams/mermaid/flowchart.mmd"));
        assertEquals("key", view.getKey());
        assertNull(view.getElement());
        assertNull(view.getElementId());
        assertEquals("flowchart.mmd", view.getTitle());
        assertEquals("https://mermaid.ink/svg/Zmxvd2NoYXJ0IFRECiAgICBBW0NocmlzdG1hc10gLS0-fEdldCBtb25leXwgQihHbyBzaG9wcGluZykKICAgIEIgLS0-IEN7TGV0IG1lIHRoaW5rfQogICAgQyAtLT58T25lfCBEW0xhcHRvcF0KICAgIEMgLS0-fFR3b3wgRVtpUGhvbmVdCiAgICBDIC0tPnxUaHJlZXwgRltmYTpmYS1jYXIgQ2FyXQ==", view.getContent());
        assertEquals("image/svg+xml", view.getContentType());
    }

    @Test
    public void importDiagram_WhenTheMermaidUrlIsNotDefined() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        ImageView view = workspace.getViews().createImageView("key");

        try {
            new MermaidImporter().importDiagram(view, new File("./src/test/diagrams/mermaid/flowchart.mmd"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Please define a view/viewset property named mermaid.url to specify your Mermaid server", e.getMessage());
        }
    }

    @Test
    public void importDiagram_WhenAnInvalidFormatIsSpecified() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        workspace.getViews().getConfiguration().addProperty("mermaid.url", "https://mermaid.ink");
        workspace.getViews().getConfiguration().addProperty("mermaid.format", "jpg");
        ImageView view = workspace.getViews().createImageView("key");

        try {
            new MermaidImporter().importDiagram(view, "...");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected a format of png or svg", e.getMessage());
        }
    }

}