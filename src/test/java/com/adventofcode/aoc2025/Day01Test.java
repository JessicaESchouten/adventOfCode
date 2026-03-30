package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01Test {

    private static final String PUZZLE_EXAMPLE_INPUT = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """;

    @Test
    void puzzleExample_part1_and_part2() {
        Day01 day01 = new Day01();
        Day.Answers answers = day01.solve(PUZZLE_EXAMPLE_INPUT);

        Assertions.assertEquals(3, answers.part1());
        Assertions.assertEquals(6, answers.part2());
    }
}

