package com.adventofcode.aoc2025;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Day05 extends Day {

    record InclusiveRange(long startInclusive, long endInclusive) {
        boolean contains(long value) {
            return value >= startInclusive && value <= endInclusive;
        }
    }

    public Day05() {
        super("day05");
    }

    @Override
    protected Answers solve(String input) {
        return new Answers(countFreshAvailableIds(input), countDistinctFreshIdsInRanges(input));
    }

    static long countFreshAvailableIds(String input) {
        String[] sections = Inputs.splitOnBlankLine(input);
        String rangeSection = sections[0];
        String idsSection = sections[1];

        List<InclusiveRange> ranges = parseInclusiveRanges(rangeSection);
        long count = 0L;
        for (String line : idsSection.split("\\R")) {
            long id = Long.parseLong(line);
            for (InclusiveRange r : ranges) {
                if (r.contains(id)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    static long countDistinctFreshIdsInRanges(String input) {
        String rangeSection = Inputs.splitOnBlankLine(input)[0];
        List<InclusiveRange> ranges = parseInclusiveRanges(rangeSection);

        ranges.sort(
                Comparator.comparingLong(InclusiveRange::startInclusive)
                        .thenComparingLong(InclusiveRange::endInclusive));

        long distinctFreshIdCount = 0L;
        long mergedStart = ranges.getFirst().startInclusive();
        long mergedEnd = ranges.getFirst().endInclusive();

        for (int i = 1; i < ranges.size(); i++) {
            InclusiveRange r = ranges.get(i);
            long start = r.startInclusive();
            long end = r.endInclusive();

            if (start <= mergedEnd + 1) {
                if (end > mergedEnd) mergedEnd = end;
            } else {
                distinctFreshIdCount += mergedEnd - mergedStart + 1;
                mergedStart = start;
                mergedEnd = end;
            }
        }
        distinctFreshIdCount += mergedEnd - mergedStart + 1;
        return distinctFreshIdCount;
    }

    private static List<InclusiveRange> parseInclusiveRanges(String text) {
        String[] lines = text.split("\\R");
        List<InclusiveRange> ranges = new ArrayList<>(lines.length);

        for (String line : lines) {
            int dash = line.indexOf('-');

            long startInclusive = Long.parseLong(line.substring(0, dash));
            long endInclusive = Long.parseLong(line.substring(dash + 1));
            ranges.add(new InclusiveRange(startInclusive, endInclusive));
        }
        return ranges;
    }
}
