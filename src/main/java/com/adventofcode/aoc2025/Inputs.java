package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

final class Inputs {

    private Inputs() {}

    static String readUtf8(Path path) throws IOException {
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    static List<String> readNonBlankTrimmedLines(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8)
                .stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();
    }

    static void forEachLine(Path path, Consumer<String> consumer) throws IOException {
        try (var lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.forEach(consumer);
        }
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
