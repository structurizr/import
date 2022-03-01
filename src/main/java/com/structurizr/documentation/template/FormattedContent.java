package com.structurizr.documentation.template;

import com.structurizr.documentation.Format;

class FormattedContent {

    private final String content;
    private final Format format;

    FormattedContent(String content, Format format) {
        this.content = content;
        this.format = format;
    }

    String getContent() {
        return content;
    }

    Format getFormat() {
        return format;
    }

}