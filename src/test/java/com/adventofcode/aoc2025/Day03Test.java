package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03Test {

    @Test
    void puzzleExample_part1_and_part2() {
        Day03 day03 = new Day03();

        day03.processLine("987654321111111");
        day03.processLine("811111111111119");
        day03.processLine("234234234234278");
        day03.processLine("818181911112111");

        Assertions.assertEquals(357L, day03.solvePart1());
        Assertions.assertEquals(3121910778619L, day03.solvePart2());
    }
}
