package com.adventofcode.aoc2025;

public class Day02 extends Day {

    public Day02() {
        super("day02");
    }

    @Override
    protected Answers solve(String input) {
        long part1Sum = 0;
        long part2Sum = 0;

        for (String token : input.split(",|\\R")) {
            int dash = token.indexOf('-');
            long startInclusive = Long.parseLong(token.substring(0, dash));
            long endInclusive = Long.parseLong(token.substring(dash + 1));

            for (long code = startInclusive; code <= endInclusive; code++) {
                String pattern = Long.toString(code);
                if (isRepeatedExactlyTwice(pattern)) part1Sum += code;
                if (isRepeatedAtLeastTwice(pattern)) part2Sum += code;
            }
        }

        return new Answers(part1Sum, part2Sum);
    }

    private static boolean isRepeatedExactlyTwice(String pattern) {
        int length = pattern.length();
        if ((length & 1) == 1) return false;

        int half = length / 2;
        return pattern.regionMatches(0, pattern, half, half);
    }

    private static boolean isRepeatedAtLeastTwice(String pattern) {
        int length = pattern.length();
        for (int blockSize = 1; blockSize <= length / 2; blockSize++) {
            if (length % blockSize != 0) continue;
            if (isMadeOfRepeatedBlock(pattern, blockSize)) return true;
        }
        return false;
    }

    private static boolean isMadeOfRepeatedBlock(String pattern, int blockSize) {
        for (int index = blockSize; index < pattern.length(); index++) {
            if (pattern.charAt(index) != pattern.charAt(index % blockSize)) return false;
        }
        return true;
    }
}
