package com.adventofcode.aoc2025;

public class Day03 extends Day {

    private static final int BATTERIES_PER_SEQUENCE = 12;

    private long totalJoltage = 0;

    @Override
    protected void processToken(String token) {
        totalJoltage = Math.addExact(totalJoltage, computeMaxJoltage(token, BATTERIES_PER_SEQUENCE));
    }

    static long computeMaxJoltage(String sequence, int count) {
        validateSequenceAndCount(sequence, count);
        return computeMaxJoltageUnchecked(sequence, count);
    }

    private static void validateSequenceAndCount(String sequence, int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0: " + count);
        if (count == 0) return;
        if (sequence.length() < count) throw new IllegalArgumentException("Sequence too short: " + sequence.length() + " < " + count);
        for (int i = 0; i < sequence.length(); i++) {
            char ch = sequence.charAt(i);
            if (ch < '0' || ch > '9') throw new IllegalArgumentException("Invalid sequence (non-digit): " + sequence);
        }
    }

    private static long computeMaxJoltageUnchecked(String sequence, int count) {
        long result = 0L;
        int startIndex = 0;
        int length = sequence.length();

        for (int position = 0; position < count; position++) {
            int endExclusive = length - (count - position - 1);

            char bestDigit = '0';
            int bestIndex = startIndex;

            for (int i = startIndex; i < endExclusive; i++) {
                char ch = sequence.charAt(i);
                if (ch > bestDigit) {
                    bestDigit = ch;
                    bestIndex = i;
                    if (bestDigit == '9') break;
                }
            }

            result = Math.addExact(Math.multiplyExact(result, 10L), (long) (bestDigit - '0'));
            startIndex = bestIndex + 1;
        }
        return result;
    }

    long getTotalJoltage() {
        return totalJoltage;
    }
}
