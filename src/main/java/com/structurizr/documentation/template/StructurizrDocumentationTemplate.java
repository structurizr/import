package com.structurizr.documentation.template;

import com.structurizr.documentation.Documentable;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import com.structurizr.model.Component;
import com.structurizr.model.Container;
import com.structurizr.model.SoftwareSystem;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * A simple documentation template, based upon the "software guidebook" concept in Simon Brown's
 * <a href="https://leanpub.com/visualising-software-architecture">Software Architecture for Developers</a>
 * book, with the following sections:
 * </p>
 *
 * <ul>
 *     <li>Context</li>
 *     <li>Functional Overview</li>
 *     <li>Quality Attributes</li>
 *     <li>Constraints</li>
 *     <li>Principles</li>
 *     <li>Software Architecture</li>
 *     <li>Containers</li>
 *     <li>Components</li>
 *     <li>Code</li>
 *     <li>Data</li>
 *     <li>Infrastructure Architecture</li>
 *     <li>Deployment</li>
 *     <li>Development Environment</li>
 *     <li>Operation and Support</li>
 *     <li>Decision Log</li>
 * </ul>
 */
public class StructurizrDocumentationTemplate extends DocumentationTemplate {

    public StructurizrDocumentationTemplate(Documentable documentable) {
        super(documentable);
    }

    /**
     * Adds a "Context" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addContextSection(File... files) throws IOException {
        return addSection("Context", files);
    }

    /**
     * Adds a "Context" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addContextSection(Format format, String content) {
        return addSection("Context", format, content);
    }

    /**
     * Adds a "Functional Overview" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addFunctionalOverviewSection(File... files) throws IOException {
        return addSection("Functional Overview", files);
    }

    /**
     * Adds a "Functional Overview" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addFunctionalOverviewSection(Format format, String content) {
        return addSection("Functional Overview", format, content);
    }

    /**
     * Adds a "Quality Attributes" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addQualityAttributesSection(File... files) throws IOException {
        return addSection("Quality Attributes", files);
    }

    /**
     * Adds a "Quality Attributes" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addQualityAttributesSection(Format format, String content) {
        return addSection("Quality Attributes", format, content);
    }

    /**
     * Adds a "Constraints" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addConstraintsSection(File... files) throws IOException {
        return addSection("Constraints", files);
    }

    /**
     * Adds a "Constraints" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addConstraintsSection(Format format, String content) {
        return addSection("Constraints", format, content);
    }

    /**
     * Adds a "Principles" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addPrinciplesSection(File... files) throws IOException {
        return addSection("Principles", files);
    }

    /**
     * Adds a "Principles" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addPrinciplesSection(Format format, String content) {
        return addSection("Principles", format, content);
    }

    /**
     * Adds a "Software Architecture" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addSoftwareArchitectureSection(File... files) throws IOException {
        return addSection("Software Architecture", files);
    }

    /**
     * Adds a "Software Architecture" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addSoftwareArchitectureSection(Format format, String content) {
        return addSection("Software Architecture", format, content);
    }

    /**
     * Adds a "Containers" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addContainersSection(File... files) throws IOException {
        return addSection("Containers", files);
    }

    /**
     * Adds a "Containers" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addContainersSection(Format format, String content) {
        return addSection("Containers", format, content);
    }

    /**
     * Adds a "Components" section relating to a {@link Container} from one or more files.
     *
     * @param files         one or more File objects that point to the documentation content
     * @return              a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addComponentsSection(File... files) throws IOException {
        return addSection("Components", files);
    }

    /**
     * Adds a "Components" section relating to a {@link Container}.
     *
     * @param format        the {@link Format} of the documentation content
     * @param content       a String containing the documentation content
     * @return              a documentation {@link Section}
     */
    public Section addComponentsSection(Format format, String content) {
        return addSection("Components", format, content);
    }

    /**
     * Adds a "Code" section relating to a {@link Component} from one or more files.
     *
     * @param files         one or more File objects that point to the documentation content
     * @return              a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addCodeSection(File... files) throws IOException {
        return addSection("Code", files);
    }

    /**
     * Adds a "Code" section relating to a {@link Component}.
     *
     * @param format        the {@link Format} of the documentation content
     * @param content       a String containing the documentation content
     * @return              a documentation {@link Section}
     */
    public Section addCodeSection(Format format, String content) {
        return addSection("Code", format, content);
    }

    /**
     * Adds a "Data" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addDataSection(File... files) throws IOException {
        return addSection("Data", files);
    }

    /**
     * Adds a "Data" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addDataSection(Format format, String content) {
        return addSection("Data", format, content);
    }

    /**
     * Adds an "Infrastructure Architecture" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addInfrastructureArchitectureSection(File... files) throws IOException {
        return addSection("Infrastructure Architecture", files);
    }

    /**
     * Adds an "Infrastructure Architecture" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addInfrastructureArchitectureSection(Format format, String content) {
        return addSection("Infrastructure Architecture", format, content);
    }

    /**
     * Adds a "Deployment" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addDeploymentSection(File... files) throws IOException {
        return addSection("Deployment", files);
    }

    /**
     * Adds a "Deployment" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addDeploymentSection(Format format, String content) {
        return addSection("Deployment", format, content);
    }

    /**
     * Adds a "Development Environment" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addDevelopmentEnvironmentSection(File... files) throws IOException {
        return addSection("Development Environment", files);
    }

    /**
     * Adds a "Development Environment" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addDevelopmentEnvironmentSection(Format format, String content) {
        return addSection("Development Environment", format, content);
    }

    /**
     * Adds an "Operation and Support" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addOperationAndSupportSection(File... files) throws IOException {
        return addSection("Operation and Support", files);
    }

    /**
     * Adds a "Operation and Support" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addOperationAndSupportSection(Format format, String content) {
        return addSection("Operation and Support", format, content);
    }

    /**
     * Adds a "Decision Log" section relating to a {@link SoftwareSystem} from one or more files.
     *
     * @param files             one or more File objects that point to the documentation content
     * @return                  a documentation {@link Section}
     * @throws IOException      if there is an error reading the files
     */
    public Section addDecisionLogSection(File... files) throws IOException {
        return addSection("Decision Log", files);
    }

    /**
     * Adds a "Decision Log" section relating to a {@link SoftwareSystem}.
     *
     * @param format            the {@link Format} of the documentation content
     * @param content           a String containing the documentation content
     * @return                  a documentation {@link Section}
     */
    public Section addDecisionLogSection(Format format, String content) {
        return addSection("Decision Log", format, content);
    }

}