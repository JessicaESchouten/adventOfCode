package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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
    void solvePart1_example_is_13() {
        var lines = EXAMPLE_DIAGRAM.strip().lines().toList();
        Assertions.assertEquals(13L, Day04.solvePart1(lines));
    }

    @Test
    void solvePart2_example_is_43() {
        var lines = EXAMPLE_DIAGRAM.strip().lines().toList();
        Assertions.assertEquals(43L, Day04.solvePart2(lines));
    }

    @Test
    void solve_day04Txt_matchesPuzzleAnswers() throws IOException {
        Path input = Path.of("src/main/resources/aoc2025/day04.txt");
        Assertions.assertTrue(Files.exists(input), "Input not found: " + input.toAbsolutePath());

        var lines = Files.readAllLines(input, StandardCharsets.UTF_8)
                .stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();

        Assertions.assertEquals(1508L, Day04.solvePart1(lines));
        Assertions.assertEquals(8538L, Day04.solvePart2(lines));
    }
}
