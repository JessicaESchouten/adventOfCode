package com.adventofcode.aoc2025;

import java.util.function.BinaryOperator;

public class Day01 extends Day {

    public static final int START_VALUE = 50;

    static final BinaryOperator<Integer> RIGHT = Integer::sum;
    static final BinaryOperator<Integer> LEFT = (a, b) -> a - b;

    int pointer = START_VALUE;

    // End state per turn
    int endStateZeroCount = 0;

    // All zeros hit during the turn (intermediate clicks + final click)
    int totalZeroCount = 0;

    @Override
    protected void processToken(String token) {
        char direction = token.charAt(0);
        int steps = Integer.parseInt(token.substring(1).trim());

        switch (direction) {
            case 'L' -> turn(LEFT, pointer, steps);
            case 'R' -> turn(RIGHT, pointer, steps);
            default -> throw new IllegalArgumentException("Unknown direction: " + token);
        }
    }

    protected void turn(BinaryOperator<Integer> direction, int startValue, int steps) {
        countZerosDuringTurn(direction, startValue, steps);

        pointer = Math.floorMod(direction.apply(startValue, steps), 100);
        if (pointer == 0) endStateZeroCount++;
    }

    protected void countZerosDuringTurn(BinaryOperator<Integer> direction, int startValue, int steps) {
        if (steps <= 0) return;

        // Clicks: 1 step through N steps (including final step), regardless of direction.
        int step1 = direction.apply(startValue, 1);
        int stepN = direction.apply(startValue, steps);
        int min = Math.min(step1, stepN);
        int max = Math.max(step1, stepN);
        totalZeroCount += countMultiplesOfHundredInRange(min, max);
    }

    private static int countMultiplesOfHundredInRange(int min, int max) {
        if (min > max) return 0;

        // Number of multiples of 100 in [min..max], works for negative bounds as well.
        return Math.floorDiv(max, 100) - Math.floorDiv(min - 1, 100);
    }
}
