package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day04Test {

    private static final String PUZZLE_EXAMPLE_DIAGRAM = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
            """;

    @Test
    void puzzleExample_part1_is_13() {
        Day04 day04 = new Day04();
        Day.Answers answers = day04.solve(PUZZLE_EXAMPLE_DIAGRAM);
        Assertions.assertEquals(13L, answers.part1());
    }

    @Test
    void puzzleExample_part2_is_43() {
        Day04 day04 = new Day04();
        Day.Answers answers = day04.solve(PUZZLE_EXAMPLE_DIAGRAM);
        Assertions.assertEquals(43L, answers.part2());
    }
}
