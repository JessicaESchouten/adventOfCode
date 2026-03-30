package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

final class Inputs {

    private Inputs() {}

    static String readUtf8(Path path) throws IOException {
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    static String[] splitOnBlankLine(String text) {
        int separator = text.indexOf("\r\n\r\n");
        int separatorLength = 4;
        if (separator < 0) {
            separator = text.indexOf("\n\n");
            separatorLength = 2;
        }

        return new String[] {
                text.substring(0, separator),
                text.substring(separator + separatorLength)
        };
    }
}
