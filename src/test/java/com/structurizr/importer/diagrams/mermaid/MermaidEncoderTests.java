package com.structurizr.importer.diagrams.mermaid;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MermaidEncoderTests {

    @Test
    public void encode_flowchart() throws Exception {
        File file = new File("./src/test/diagrams/mermaid/flowchart.mmd");
        String mermaid = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        assertEquals("eyAiY29kZSI6ImZsb3djaGFydCBURFxuICAgIFN0YXJ0IC0tPiBTdG9wIiwgIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In19", new MermaidEncoder().encode(mermaid));
    }

    @Test
    public void encode_class() throws Exception {
        File file = new File("./src/test/diagrams/mermaid/class.mmd");
        String mermaid = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        assertEquals("eyAiY29kZSI6ImNsYXNzRGlhZ3JhbVxuICAgIEFuaW1hbCA8fC0tIER1Y2tcbiAgICBBbmltYWwgPHwtLSBGaXNoXG4gICAgQW5pbWFsIDx8LS0gWmVicmFcbiAgICBBbmltYWwgOiAraW50IGFnZVxuICAgIEFuaW1hbCA6ICtTdHJpbmcgZ2VuZGVyXG4gICAgQW5pbWFsOiAraXNNYW1tYWwoKVxuICAgIEFuaW1hbDogK21hdGUoKVxuICAgIGNsYXNzIER1Y2t7XG4gICAgICArU3RyaW5nIGJlYWtDb2xvclxuICAgICAgK3N3aW0oKVxuICAgICAgK3F1YWNrKClcbiAgICB9XG4gICAgY2xhc3MgRmlzaHtcbiAgICAgIC1pbnQgc2l6ZUluRmVldFxuICAgICAgLWNhbkVhdCgpXG4gICAgfVxuICAgIGNsYXNzIFplYnJhe1xuICAgICAgK2Jvb2wgaXNfd2lsZFxuICAgICAgK3J1bigpXG4gICAgfVxuIiwgIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In19", new MermaidEncoder().encode(mermaid));

    }

}