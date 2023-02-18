package com.structurizr.importer.diagrams.mermaid;

import com.structurizr.importer.diagrams.AbstractDiagramImporter;
import com.structurizr.util.StringUtils;
import com.structurizr.view.ImageView;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MermaidImporter extends AbstractDiagramImporter {

    private static final String MERMAID_URL_PROPERTY = "mermaid.url";

    public void importDiagram(ImageView view, File file) throws Exception {
        String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        view.setTitle(file.getName());

        importDiagram(view, content);
    }

    public void importDiagram(ImageView view, String content) {
        String mermaidServer = getViewOrViewSetProperty(view, MERMAID_URL_PROPERTY);
        if (StringUtils.isNullOrEmpty(mermaidServer)) {
            throw new IllegalArgumentException("Please define a view/viewset property named " + MERMAID_URL_PROPERTY + " to specify your Mermaid server");
        }

        String encodedMermaid = new MermaidEncoder().encode(content);
        String url = String.format("%s/img/%s?type=png", mermaidServer, encodedMermaid);
        view.setContent(url);
        view.setContentType(CONTENT_TYPE_IMAGE_PNG);
    }

}