package com.adventofcode.aoc2025;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;
import static java.util.stream.Stream.iterate;

public class Dag02 {

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

    private long answerPart1 = 0;
    private long answerPart2 = 0;

    protected List<Range> parseInput(String input) {
        List<Range> ranges = new ArrayList<>();
        for (String range : input.trim().split("\\s*,\\s*")) {
            String[] boundary = range.trim().split("\\s*-\\s*");
            ranges.add(new Range(parseLong(boundary[0]), parseLong(boundary[1])));
        }
        return ranges;
    }

    static protected boolean isRepeatedTwice(Long number) {
        String test = number.toString();
        String links = test.substring(0, test.length() / 2);
        String rechts = test.substring(test.length() / 2);
        return test.length() % 2 == 0 && links.equals(rechts);
    }

    static protected boolean isRepeatedAtLeastTwice(Long number) {
        String test = number.toString();
        String extended = test + test;
        return extended.substring(1, extended.length() - 1).contains(test);
    }

    protected void verwerkRegel(String line) {
        List<Range> ranges = parseInput(line);
        answerPart1 = ranges.stream().flatMap(Range::stream).filter(Dag02::isRepeatedTwice).mapToLong(Long::longValue).sum();
        answerPart2 = ranges.stream().flatMap(Range::stream).filter(Dag02::isRepeatedAtLeastTwice).mapToLong(Long::longValue).sum();
    }

    protected long getAnswerPart1() {
        return answerPart1;
    }

    protected long getAnswerPart2() {
        return answerPart2;
    }
}