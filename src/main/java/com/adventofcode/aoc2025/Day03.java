package com.adventofcode.aoc2025;

public class Day03 extends Day {

    private static final int PART1_BATTERIES_PER_SEQUENCE = 2;
    private static final int PART2_BATTERIES_PER_SEQUENCE = 12;

    public Day03() {
        super("day03");
    }

    @Override
    protected Answers solve(String input) {
        long part1TotalJoltage = 0;
        long part2TotalJoltage = 0;

        for (String token : input.split("\\R")) {
            part1TotalJoltage = Math.addExact(part1TotalJoltage, maxJoltage(token, PART1_BATTERIES_PER_SEQUENCE));
            part2TotalJoltage = Math.addExact(part2TotalJoltage, maxJoltage(token, PART2_BATTERIES_PER_SEQUENCE));
        }

        return new Answers(part1TotalJoltage, part2TotalJoltage);
    }

    static long maxJoltage(String bank, int remainingBatteries) {
        return maxJoltage(bank, remainingBatteries, 0L);
    }

    static long maxJoltage(String bank, int remainingBatteries, long result) {
        if (remainingBatteries == 0) return result;

        int until = bank.length() - remainingBatteries + 1;
        String prefix = bank.substring(0, until);

        int value = prefix.chars().max().orElseThrow();
        int index = prefix.indexOf(value);
        int digit = value - '0';

        return maxJoltage(bank.substring(index + 1), remainingBatteries - 1, result * 10 + digit);
    }


}
