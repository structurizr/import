package com.structurizr.importer.documentation;

import com.structurizr.Workspace;
import com.structurizr.documentation.Decision;
import com.structurizr.documentation.Documentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MadrToolsDecisionImporterTest {

    private MadrToolsDecisionImporter importerUnderTest;
    private Workspace workspace;
    private Documentation documentation;

    @BeforeEach
    public void setUp() {
        importerUnderTest = new MadrToolsDecisionImporter();
        workspace = new Workspace("Name", "Description");
        documentation = workspace.getDocumentation();
    }

    @Test
    void test_importDecisions() {
        importerUnderTest.importDocumentation(workspace, new File("./src/test/adrs"));

        assertEquals(1, documentation.getDecisions().size());

        Decision decision = documentation.getDecisions().stream().findFirst().get();

        assertEquals("Use Markdown Architectural Decision Records", decision.getTitle());
        assertEquals("Accepted",decision.getStatus());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss ZZZ", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals("30-May-2023 00:00:00 +0000", sdf.format(decision.getDate()));
    }
}