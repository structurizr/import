package com.structurizr.documentation.template;

import com.structurizr.documentation.Documentable;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * An implementation of the <a href="http://arc42.org">arc42 documentation template</a>,
 * consisting of the following sections:
 * </p>
 *
 * <ol>
 *     <li>Introduction and Goals</li>
 *     <li>Constraints</li>
 *     <li>Context and Scope</li>
 *     <li>Solution Strategy</li>
 *     <li>Building Block View</li>
 *     <li>Runtime View</li>
 *     <li>Deployment View</li>
 *     <li>Crosscutting Concepts</li>
 *     <li>Architectural Decisions</li>
 *     <li>Quality Requirements</li>
 *     <li>Risks and Technical Debt</li>
 *     <li>Glossary</li>
 * </ol>
 */
public class Arc42DocumentationTemplate extends DocumentationTemplate {

    public Arc42DocumentationTemplate(Documentable documentable) {
        super(documentable);
    }

    /**
     * Adds an "Introduction and Goals" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addIntroductionAndGoalsSection(File... files) throws IOException {
        return addSection("Introduction and Goals", files);
    }

    /**
     * Adds an "Introduction and Goals" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addIntroductionAndGoalsSection(Format format, String content) {
        return addSection("Introduction and Goals", format, content);
    }

    /**
     * Adds a "Constraints" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addConstraintsSection(File... files) throws IOException {
        return addSection("Constraints", files);
    }

    /**
     * Adds a "Constraints" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addConstraintsSection(Format format, String content) {
        return addSection("Constraints", format, content);
    }

    /**
     * Adds a "Context and Scope" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addContextAndScopeSection(File... files) throws IOException {
        return addSection("Context and Scope", files);
    }

    /**
     * Adds a "Context and Scope" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addContextAndScopeSection(Format format, String content) {
        return addSection("Context and Scope", format, content);
    }

    /**
     * Adds a "Solution Strategy" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addSolutionStrategySection(File... files) throws IOException {
        return addSection("Solution Strategy", files);
    }

    /**
     * Adds a "Solution Strategy" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addSolutionStrategySection(Format format, String content) {
        return addSection("Solution Strategy", format, content);
    }

    /**
     * Adds a "Building Block View" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addBuildingBlockViewSection(File... files) throws IOException {
        return addSection("Building Block View", files);
    }

    /**
     * Adds a "Building Block View" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addBuildingBlockViewSection(Format format, String content) {
        return addSection("Building Block View", format, content);
    }

    /**
     * Adds a "Runtime View" section from one or more files.
     ** @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addRuntimeViewSection(File... files) throws IOException {
        return addSection("Runtime View", files);
    }

    /**
     * Adds a "Runtime View" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addRuntimeViewSection(Format format, String content) {
        return addSection("Runtime View", format, content);
    }

    /**
     * Adds a "Deployment View" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addDeploymentViewSection(File... files) throws IOException {
        return addSection("Deployment View", files);
    }

    /**
     * Adds a "Deployment View" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addDeploymentViewSection(Format format, String content) {
        return addSection("Deployment View", format, content);
    }

    /**
     * Adds a "Crosscutting Concepts" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addCrosscuttingConceptsSection(File... files) throws IOException {
        return addSection("Crosscutting Concepts", files);
    }

    /**
     * Adds a "Crosscutting Concepts" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addCrosscuttingConceptsSection(Format format, String content) {
        return addSection("Crosscutting Concepts", format, content);
    }

    /**
     * Adds an "Architectural Decisions" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addArchitecturalDecisionsSection(File... files) throws IOException {
        return addSection("Architectural Decisions", files);
    }

    /**
     * Adds an "Architectural Decisions" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addArchitecturalDecisionsSection(Format format, String content) {
        return addSection("Architectural Decisions", format, content);
    }

    /**
     * Adds a "Quality Requirements" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addQualityRequirementsSection(File... files) throws IOException {
        return addSection("Quality Requirements", files);
    }

    /**
     * Adds a "Quality Requirements" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addQualityRequirementsSection(Format format, String content) {
        return addSection("Quality Requirements", format, content);
    }

    /**
     * Adds a "Risks and Technical Debt" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addRisksAndTechnicalDebtSection(File... files) throws IOException {
        return addSection("Risks and Technical Debt", files);
    }

    /**
     * Adds a "Risks and Technical Debt" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addRisksAndTechnicalDebtSection(Format format, String content) {
        return addSection("Risks and Technical Debt", format, content);
    }

    /**
     * Adds a "Glossary" section from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addGlossarySection(File... files) throws IOException {
        return addSection("Glossary", files);
    }

    /**
     * Adds a "Glossary" section.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addGlossarySection(Format format, String content) {
        return addSection("Glossary", format, content);
    }

}