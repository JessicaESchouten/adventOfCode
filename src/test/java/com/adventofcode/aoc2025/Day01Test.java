package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static com.adventofcode.aoc2025.Day01.LEFT;
import static com.adventofcode.aoc2025.Day01.RIGHT;

class Day01Test {

    @TempDir
    Path tempDir;

    @Test
    void turn_right_updatesPointer() {
        Day01 day01 = new Day01();

        day01.turn(RIGHT, 50, 21);

        Assertions.assertEquals(71, day01.pointer);
    }

    @Test
    void turn_left_updatesPointer() {
        Day01 day01 = new Day01();

        day01.turn(LEFT, 50, 21);

        Assertions.assertEquals(29, day01.pointer);
    }

    @Test
    void turn_left_100From0_endsAt0_andCountsEndStateZero() {
        Day01 day01 = new Day01();

        day01.turn(LEFT, 0, 100);

        Assertions.assertEquals(0, day01.pointer);
        Assertions.assertEquals(1, day01.endStateZeroCount);
    }

    @Test
    void processLine_unknownDirection_throws() {
        Day01 day01 = new Day01();

        Assertions.assertThrows(IllegalArgumentException.class, () -> day01.processLine("X10"));
    }

    @Test
    void processFile_processesLines_andCountsTotalZeros() throws IOException {
        Day01 day01 = new Day01();
        Path input = tempDir.resolve("dummy-source.txt");

        Files.writeString(input, """
            R50
            R100
            R150
            """);

        AdventOfCodeApplication.processFile(input, day01::processLine);

        Assertions.assertEquals(50, day01.pointer);
        Assertions.assertEquals(2, day01.endStateZeroCount);
        Assertions.assertEquals(3, day01.totalZeroCount);
    }

    @Test
    void processFile_fullResource_countsEndStateZeros() throws IOException {
        Day01 day01 = new Day01();

        Path tmp = tempDir.resolve("day01.txt");
        try (InputStream in = Day01.class.getResourceAsStream("/aoc2025/day01.txt")) {
            Assertions.assertNotNull(in, "Resource not found: /aoc2025/day01.txt");
            Files.copy(in, tmp, StandardCopyOption.REPLACE_EXISTING);
        }

        AdventOfCodeApplication.processFile(tmp, day01::processLine);

        Assertions.assertEquals(1097, day01.endStateZeroCount);
    }
}
