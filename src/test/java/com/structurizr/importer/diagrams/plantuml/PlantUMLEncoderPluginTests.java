package com.structurizr.importer.diagrams.plantuml;

import com.structurizr.Workspace;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlantUMLEncoderPluginTests {

    @Test
    public void test_run() {
        Workspace workspace = new Workspace("Name", "Description");

        Section markdown = new Section("Markdown", Format.Markdown, "## Context\n" +
                "\n" +
                "```plantuml\n" +
                "@startuml\n" +
                "Bob -> Alice : hello\n" +
                "@enduml\n" +
                "```");
        workspace.getDocumentation().addSection(markdown);

        Section asciidoc = new Section("AsciiDoc", Format.AsciiDoc, "== Context\n" +
                "\n" +
                "```plantuml\n" +
                "@startuml\n" +
                "Bob -> Alice : hello\n" +
                "@enduml\n" +
                "```");
        workspace.getDocumentation().addSection(asciidoc);

        Map<String,String> parameters = new HashMap<>();
//        StructurizrDslPluginContext context = new StructurizrDslPluginContext(workspace, parameters);
//        new PlantUMLEncoderPlugin().run(context);

//        assertEquals("## Context\n" +
//                "\n" +
//                "![](https://www.plantuml.com/plantuml/svg/SoWkIImgAStDuNBAJrBGjLDmpCbCJbMmKiX8pSd9vt98pKi1IG80)\n", markdown.getContent());
//
//        assertEquals("== Context\n" +
//                "\n" +
//                "image::https://www.plantuml.com/plantuml/svg/SoWkIImgAStDuNBAJrBGjLDmpCbCJbMmKiX8pSd9vt98pKi1IG80[]\n", asciidoc.getContent());
    }

}
