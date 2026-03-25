package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Day03Test {

    @Test
    void exampleFromAssignment_part1AndPart2() {
        Day03 day03 = new Day03();

        day03.processLine("987654321111111");
        day03.processLine("811111111111119");
        day03.processLine("234234234234278");
        day03.processLine("818181911112111");

        Assertions.assertEquals(357L, day03.solvePart1());
        Assertions.assertEquals(3121910778619L, day03.solvePart2());
    }

    @Test
    void processFile_day03Txt_matchesPuzzleAnswers() throws IOException {
        Day03 day03 = new Day03();

        Path input = Path.of("src/main/resources/aoc2025/day03.txt");
        Assertions.assertTrue(Files.exists(input), "Input not found: " + input.toAbsolutePath());

        AdventOfCodeApplication.processFile(input, day03::processLine);

        Assertions.assertEquals(17031L, day03.solvePart1());
        Assertions.assertEquals(168575096286051L, day03.solvePart2());
    }
}
