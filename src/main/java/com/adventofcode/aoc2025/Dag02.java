package com.adventofcode.aoc2025;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;
import static java.util.stream.Stream.iterate;

@Getter
public class Dag02 {

    private long answerPart1 = 0;
    private long answerPart2 = 0;

    public static class Range
    {
        private final long start;
        private final long end;

        public Range(long start, long end){
            this.start = start;
            this.end = end;
        }

        public Stream<Long> stream(){
            return iterate(start, l -> l <= end, begin -> begin + 1);
        }
    }

    private List<Range> parseInput(String input) {
        List<Range> ranges = new ArrayList<>();
        for (String range : input.trim().split("\\s*,\\s*")) {
            String[] boundary = range.trim().split("\\s*-\\s*");
            ranges.add(new Range(parseLong(boundary[0]), parseLong(boundary[1])));
        }
        return ranges;
    }

    static private boolean isRepeatedTwice(Long number) {
        String test = number.toString();
        String left = test.substring(0, test.length() / 2);
        String right = test.substring(test.length() / 2);
        return test.length() % 2 == 0 && left.equals(right);
    }

    static private boolean isRepeatedAtLeastTwice(Long number) {
        String test = number.toString();
        String extended = test + test;
        return extended.substring(1, extended.length() - 1).contains(test);
    }

    public void verwerkRegel(String line) {
        List<Range> ranges = parseInput(line);
        answerPart1 = ranges.stream().flatMap(Range::stream).filter(Dag02::isRepeatedTwice).mapToLong(Long::longValue).sum();
        answerPart2 = ranges.stream().flatMap(Range::stream).filter(Dag02::isRepeatedAtLeastTwice).mapToLong(Long::longValue).sum();
    }
}