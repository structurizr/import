package com.structurizr.documentation.importer;

import com.structurizr.documentation.Documentable;

import java.io.File;
import java.util.Arrays;

/**
 * This implementation extends the DefaultDocumentationImporter to recursively import documentation.
 */
public class RecursiveDefaultDocumentationImporter extends DefaultDocumentationImporter {

    /**
     * Imports Markdown/AsciiDoc files from the specified path, each in its own section.
     *
     * @param documentable      the item that documentation should be associated with
     * @param path              the path to import documentation from
     */
    @Override
    public void importDocumentation(Documentable documentable, File path) {
        try {
            if (documentable == null) {
                throw new IllegalArgumentException("A workspace or software system must be specified");
            }

            if (path == null) {
                throw new IllegalArgumentException("A path must be specified");
            } else if (!path.exists()) {
                throw new IllegalArgumentException(path.getAbsolutePath() + " does not exist");
            }

            if (path.isDirectory()) {
                File[] filesInDirectory = path.listFiles();
                if (filesInDirectory != null) {
                    Arrays.sort(filesInDirectory);

                    for (File file : filesInDirectory) {
                        if (!file.isDirectory() && !file.getName().startsWith(".")) {
                            importFile(documentable, file);
                        } else if (file.isDirectory()) {
                            importDocumentation(documentable, file);
                        }
                    }
                }
            } else {
                importFile(documentable, path);
            }
        } catch (Exception e) {
            throw new DocumentationImportException(e);
        }
    }

}