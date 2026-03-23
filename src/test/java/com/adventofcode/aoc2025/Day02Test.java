package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day02Test {

    private static final String EXAMPLE_INPUT =
            "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862";

    @Test
    void solvePart1_exampleFromDescription_is_1227775554() {
        Day02 day02 = new Day02();

        day02.processLine(EXAMPLE_INPUT);

        Assertions.assertEquals(1227775554L, day02.solvePart1());
    }

    @Test
    void solvePart2_countsRepetitionWithMoreThanTwoBlocks() {
        Day02 day02 = new Day02();

        // 121212 = "12" repeated 3 times -> yes for part 2, no for part 1
        day02.processLine("121212-121212");

        Assertions.assertEquals(0L, day02.solvePart1());
        Assertions.assertEquals(121212L, day02.solvePart2());
    }
}
