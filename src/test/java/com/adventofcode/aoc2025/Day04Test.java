package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day04Test {

    private static final String EXAMPLE_DIAGRAM = """
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
    void example_accessibleRolls_is_13() {
        var lines = EXAMPLE_DIAGRAM.strip().lines().toList();
        Assertions.assertEquals(13L, Day04.solvePart1(lines));
    }

    @Test
    void example_totalRemovableRolls_is_43() {
        var lines = EXAMPLE_DIAGRAM.strip().lines().toList();
        Assertions.assertEquals(43L, Day04.solvePart2(lines));
    }
}
