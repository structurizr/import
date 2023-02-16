package com.structurizr.importer.diagrams.kroki;

import com.structurizr.Workspace;
import com.structurizr.view.ImageView;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class KrokiImporterTests {

    @Test
    public void test_import() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        workspace.getViews().getConfiguration().addProperty("kroki.url", "https://kroki.io");
        ImageView view = workspace.getViews().createImageView("key");

        new KrokiImporter().importDiagram(view, "graphviz", new File("./src/test/diagrams/kroki/diagram.dot"));
        assertEquals("key", view.getKey());
        assertNull(view.getElement());
        assertNull(view.getElementId());
        assertEquals("diagram.dot", view.getTitle());
        assertEquals("https://kroki.io/graphviz/png/eNpLyUwvSizIUHBXqPZIzcnJ17ULzy_KSanlAgB1EAjQ", view.getContent());
    }

    @Test
    public void test_import_WhenTheKrokiUrlIsNotDefined() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        ImageView view = workspace.getViews().createImageView("key");

        try {
            new KrokiImporter().importDiagram(view, "graphviz", new File("./src/test/diagrams/kroki/diagram.dot"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Please define a view/viewset property named kroki.url to specify your Kroki server", e.getMessage());
        }
    }

}