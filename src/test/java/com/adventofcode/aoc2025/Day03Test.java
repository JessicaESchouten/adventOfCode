package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03Test {

    @Test
    void puzzleExample_part1_and_part2() {
        Day03 day03 = new Day03();

        String input = """
987654321111111
811111111111119
234234234234278
818181911112111
""";

        Day.Answers answers = day03.solve(input);
        Assertions.assertEquals(357L, answers.part1());
        Assertions.assertEquals(3121910778619L, answers.part2());
    }
}
