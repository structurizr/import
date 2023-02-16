package com.structurizr.importer.diagrams.mermaid;

import com.structurizr.Workspace;
import com.structurizr.view.ImageView;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MermaidImporterTests {

    @Test
    public void test_import() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        workspace.getViews().getConfiguration().addProperty("mermaid.url", "https://mermaid.ink");
        ImageView view = workspace.getViews().createImageView("key");

        new MermaidImporter().importDiagram(view, new File("./src/test/diagrams/mermaid/flowchart.mmd"));
        assertEquals("key", view.getKey());
        assertNull(view.getElement());
        assertNull(view.getElementId());
        assertEquals("flowchart.mmd", view.getTitle());
        assertEquals("https://mermaid.ink/img/eyAiY29kZSI6ImZsb3djaGFydCBURFxuICAgIFN0YXJ0IC0tPiBTdG9wIiwgIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In19", view.getContent());
    }

    @Test
    public void test_import_WhenTheMermaidUrlIsNotDefined() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        ImageView view = workspace.getViews().createImageView("key");

        try {
            new MermaidImporter().importDiagram(view, new File("./src/test/diagrams/mermaid/flowchart.mmd"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Please define a view/viewset property named mermaid.url to specify your Mermaid server", e.getMessage());
        }
    }

}