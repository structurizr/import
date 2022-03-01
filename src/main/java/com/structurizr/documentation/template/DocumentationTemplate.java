package com.structurizr.documentation.template;

import com.structurizr.documentation.Documentable;
import com.structurizr.documentation.Documentation;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import com.structurizr.documentation.util.FormatFinder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * A superclass for documentation templates.
 */
public abstract class DocumentationTemplate {

    protected Documentation documentation;

    /**
     * Creates a new documentation template for the given workspace or software system.
     *
     * @param documentable     the Documentable object to create documentation for
     */
    public DocumentationTemplate(Documentable documentable) {
        if (documentable == null) {
            throw new IllegalArgumentException("A workspace or software system must be specified.");
        }

        this.documentation = documentable.getDocumentation();
    }

    /**
     * Adds a custom section from one or more files, that isn't related to any element in the model.
     *
     * @param title             the section title
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addSection(String title, File... files) throws IOException {
        FormattedContent content = readFiles(files);
        Section section = new Section(title, content.getFormat(), content.getContent());
        documentation.addSection(section);

        return section;
    }

    /**
     * Adds a documentation section.
     *
     * @param title             the section title
     * @param format    the {@link Format} of the documentation content
     * @param content   a String containing the documentation content
     * @return           a documentation {@link Section}
     */
    public Section addSection(String title, Format format, String content) {
        Section section = new Section(title, format, content);
        documentation.addSection(section);

        return section;
    }

    protected FormattedContent readFiles(File... files) throws IOException {
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("One or more files must be specified.");
        }

        Format format = Format.Markdown;
        StringBuilder content = new StringBuilder();
        for (File file : files) {
            if (file == null) {
                throw new IllegalArgumentException("One or more files must be specified.");
            }

            if (!file.exists()) {
                throw new IllegalArgumentException(file.getCanonicalPath() + " does not exist.");
            }

            if (content.length() > 0) {
                content.append(System.lineSeparator());
            }

            if (file.isFile()) {
                format = FormatFinder.findFormat(file);
                content.append(new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8));
            } else if (file.isDirectory()) {
                File[] filesInDirectory = file.listFiles();
                if (filesInDirectory != null) {
                    Arrays.sort(filesInDirectory);
                    content.append(readFiles(filesInDirectory).getContent());
                }
            }
        }

        return new FormattedContent(content.toString(), format);
    }

}