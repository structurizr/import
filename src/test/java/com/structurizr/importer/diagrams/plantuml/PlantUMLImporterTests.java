package com.structurizr.importer.diagrams.plantuml;

import com.structurizr.Workspace;
import com.structurizr.view.ImageView;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class PlantUMLImporterTests {

    @Test
    public void test_import_WhenATitleIsDefined() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        workspace.getViews().getConfiguration().addProperty("plantuml.url", "https://plantuml.com/plantuml");
        ImageView view = workspace.getViews().createImageView("key");

        new PlantUMLImporter().importDiagram(view, new File("./src/test/diagrams/plantuml/with-title.puml"));
        assertEquals("key", view.getKey());
        assertNull(view.getElement());
        assertNull(view.getElementId());
        assertEquals("Sequence diagram example", view.getTitle());
        assertEquals("https://plantuml.com/plantuml/png/SoWkIImgAStDuIh9BCb9LGXEBInDpKjELKZ9J4mlIinLIAr8p2t8IULooazIqBLJSCp914fQAMIavkJaSpcavgK0zG80", view.getContent());
    }

    @Test
    public void test_import_WhenATitleIsNotDefined() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        workspace.getViews().getConfiguration().addProperty("plantuml.url", "https://plantuml.com/plantuml");
        ImageView view = workspace.getViews().createImageView("key");

        new PlantUMLImporter().importDiagram(view, new File("./src/test/diagrams/plantuml/without-title.puml"));
        assertEquals("key", view.getKey());
        assertNull(view.getElement());
        assertNull(view.getElementId());
        assertEquals("without-title.puml", view.getTitle());
        assertEquals("https://plantuml.com/plantuml/png/SoWkIImgAStDuNBAJrBGjLDmpCbCJbMmKiX8pSd9vt98pKi1IW80", view.getContent());
    }

    @Test
    public void test_import_WhenThePlantUMLURLIsNotSpecified() throws Exception {
        Workspace workspace = new Workspace("Name", "Description");
        ImageView view = workspace.getViews().createImageView("key");

        try {
        new PlantUMLImporter().importDiagram(view, new File("./src/test/diagrams/plantuml/with-title.puml"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Please define a view/viewset property named plantuml.url to specify your PlantUML server", e.getMessage());
        }
    }

}
