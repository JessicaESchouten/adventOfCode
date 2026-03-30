package com.adventofcode.aoc2025;

import java.util.function.BinaryOperator;

public class Day01 extends Day {

    public static final int START_VALUE = 50;

    static final BinaryOperator<Integer> RIGHT = Integer::sum;
    static final BinaryOperator<Integer> LEFT = (a, b) -> a - b;

    public Day01() {
        super("day01");
    }

    @Override
    protected Answers solve(String input) {
        int pointer = START_VALUE;
        int endStateZeroCount = 0;
        int totalZeroCount = 0;

        for (String token : input.split("\\R")) {
            char direction = token.charAt(0);
            int steps = Integer.parseInt(token.substring(1));

            BinaryOperator<Integer> move = switch (direction) {
                case 'L' -> LEFT;
                case 'R' -> RIGHT;
                default -> throw new IllegalArgumentException("Unknown direction: " + token);
            };

            if (steps > 0) {
                int step1 = move.apply(pointer, 1);
                int stepN = move.apply(pointer, steps);
                int min = Math.min(step1, stepN);
                int max = Math.max(step1, stepN);
                totalZeroCount += countMultiplesOfHundredInRange(min, max);
            }

            pointer = Math.floorMod(move.apply(pointer, steps), 100);
            if (pointer == 0) endStateZeroCount++;
        }

        return new Answers(endStateZeroCount, totalZeroCount);
    }

    private static int countMultiplesOfHundredInRange(int min, int max) {
        if (min > max) return 0;

        // Number of multiples of 100 in [min..max], works for negative bounds as well.
        return Math.floorDiv(max, 100) - Math.floorDiv(min - 1, 100);
    }
}
