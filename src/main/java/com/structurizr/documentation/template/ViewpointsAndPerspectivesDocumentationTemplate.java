package com.structurizr.documentation.template;

import com.structurizr.documentation.Documentable;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import com.structurizr.model.SoftwareSystem;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * An implementation of the <a href="http://www.viewpoints-and-perspectives.info">"Viewpoints and Perspectives" documentation template</a>,
 * from the "Software Systems Architecture" book by Nick Rozanski and Eoin Woods, consisting of the following sections:
 * </p>
 *
 * <ol>
 *     <li>Introduction</li>
 *     <li>Glossary</li>
 *     <li>System Stakeholders and Requirements</li>
 *     <li>Architectural Forces</li>
 *     <li>Architectural Views</li>
 *     <li>System Qualities</li>
 *     <li>Appendices</li>
 * </ol>
 */
public class ViewpointsAndPerspectivesDocumentationTemplate extends DocumentationTemplate {

    public ViewpointsAndPerspectivesDocumentationTemplate(Documentable documentable) {
        super(documentable);
    }

    /**
     * Adds a "Introduction" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addIntroductionSection(File... files) throws IOException {
        return addSection("Introduction", files);
    }

    /**
     * Adds a "Introduction" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addIntroductionSection(Format format, String content) {
        return addSection("Introduction", format, content);
    }

    /**
     * Adds a "Glossary" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addGlossarySection(File... files) throws IOException {
        return addSection("Glossary", files);
    }

    /**
     * Adds a "Glossary" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addGlossarySection(Format format, String content) {
        return addSection("Glossary", format, content);
    }

    /**
     * Adds a "System Stakeholders and Requirements" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addSystemStakeholdersAndRequirementsSection(File... files) throws IOException {
        return addSection("System Stakeholders and Requirements", files);
    }

    /**
     * Adds a "System Stakeholders and Requirements" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addSystemStakeholdersAndRequirementsSection(Format format, String content) {
        return addSection("System Stakeholders and Requirements", format, content);
    }

    /**
     * Adds an "Architectural Forces" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addArchitecturalForcesSection(File... files) throws IOException {
        return addSection("Architectural Forces", files);
    }

    /**
     * Adds an "Architectural Forces" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addArchitecturalForcesSection(Format format, String content) {
        return addSection("Architectural Forces", format, content);
    }

    /**
     * Adds an "Architectural Views" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addArchitecturalViewsSection(File... files) throws IOException {
        return addSection("Architectural Views", files);
    }

    /**
     * Adds an "Architectural Views" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addArchitecturalViewsSection(Format format, String content) {
        return addSection("Architectural Views", format, content);
    }

    /**
     * Adds a "System Qualities" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addSystemQualitiesSection(File... files) throws IOException {
        return addSection("System Qualities", files);
    }

    /**
     * Adds a "System Qualities" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addSystemQualitiesSection(Format format, String content) {
        return addSection("System Qualities", format, content);
    }

    /**
     * Adds an "Appendices" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addAppendicesSection(File... files) throws IOException {
        return addSection("Appendices", files);
    }

    /**
     * Adds an "Appendices" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addAppendicesSection(Format format, String content) {
        return addSection("Appendices", format, content);
    }

}