package com.structurizr.importer.diagrams;

import com.structurizr.view.View;
import com.structurizr.view.ViewSet;

public abstract class AbstractDiagramImporter {

    protected static final String CONTENT_TYPE_IMAGE_PNG = "image/png";

    protected String getViewOrViewSetProperty(View view, String name) {
        ViewSet views = view.getViewSet();

        return
                view.getProperties().getOrDefault(name,
                        views.getConfiguration().getProperties().get(name)
                );
    }

}
