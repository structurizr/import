package com.structurizr.importer.diagrams.plantuml;

import com.structurizr.importer.diagrams.AbstractDiagramImporter;
import com.structurizr.util.StringUtils;
import com.structurizr.view.ImageView;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class PlantUMLImporter extends AbstractDiagramImporter {

    private static final String PLANTUML_URL_PROPERTY = "plantuml.url";
    private static final String TITLE_STRING = "title ";
    private static final String NEWLINE = "\n";

    public void importDiagram(ImageView view, File file) throws Exception {
        String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        view.setTitle(file.getName());

        importDiagram(view, content);
    }

    public void importDiagram(ImageView view, String content) throws Exception {
        String plantUMLServer = getViewOrViewSetProperty(view, PLANTUML_URL_PROPERTY);
        if (StringUtils.isNullOrEmpty(plantUMLServer)) {
            throw new IllegalArgumentException("Please define a view/viewset property named " + PLANTUML_URL_PROPERTY + " to specify your PlantUML server");
        }

        String encodedPlantUML = new PlantUMLEncoder().encode(content);
        String url = String.format("%s/png/%s", plantUMLServer, encodedPlantUML);
        view.setContent(url);
        view.setContentType(CONTENT_TYPE_IMAGE_PNG);

        String[] lines = content.split(NEWLINE);
        for (String line : lines) {
            if (line.startsWith(TITLE_STRING)) {
                String title = line.substring(TITLE_STRING.length());
                view.setTitle(title);
            }
        }
    }

}