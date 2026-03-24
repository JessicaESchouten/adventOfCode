package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Day05 {

    private Day05() {}

    record Range(long startInclusive, long endInclusive) {
        boolean contains(long value) {
            return value >= startInclusive && value <= endInclusive;
        }
    }

    public static long countFreshIds(Path inputPath) throws IOException {
        return countFreshIds(Files.readString(inputPath, StandardCharsets.UTF_8));
    }

    static long countFreshIds(String input) {
        String text = input.trim();

        int seperatorIndex = -1;
        int seperator = text.indexOf("\r\n\r\n");
        if (seperator >= 0) {
            seperatorIndex = 4;
        } else {
            seperator = text.indexOf("\n\n");
            if (seperator >= 0) seperatorIndex = 2;
        }

        String rangesText = text.substring(0, seperator);
        String idsText = text.substring(seperator + seperatorIndex);

        List<Range> ranges = parseRanges(rangesText);
        long count = 0L;
        for (String line : idsText.trim().split("\\R")) {
            long id = Long.parseLong(line.trim());
            for (Range r : ranges) {
                if (r.contains(id)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    private static List<Range> parseRanges(String text) {
        String[] lines = text.trim().split("\\R");
        List<Range> ranges = new ArrayList<>(lines.length);

        for (String line : lines) {
            String token = line.trim();
            if (token.isEmpty()) continue;

            int dash = token.indexOf('-');

            long startInclusive = Long.parseLong(token.substring(0, dash).trim());
            long endInclusive = Long.parseLong(token.substring(dash + 1).trim());
            ranges.add(new Range(startInclusive, endInclusive));
        }
        return ranges;
    }
}
