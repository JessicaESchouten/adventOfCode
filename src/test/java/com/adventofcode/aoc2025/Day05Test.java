package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day05Test {

    @Test
    void puzzleExample_part1_and_part2_lf() {
        String input = """
                3-5
                10-14
                16-20
                12-18

                1
                5
                8
                11
                17
                32
                """;

        Day05 day05 = new Day05();
        Day.Answers answers = day05.solve(input);
        Assertions.assertEquals(3L, answers.part1());
        Assertions.assertEquals(14L, answers.part2());
    }

    @Test
    void puzzleExample_part1_and_part2_crlf() {
        String input = """
                3-5
                10-14
                16-20
                12-18

                1
                5
                8
                11
                17
                32
                """.replace("\n", "\r\n");

        Day05 day05 = new Day05();
        Day.Answers answers = day05.solve(input);
        Assertions.assertEquals(3L, answers.part1());
        Assertions.assertEquals(14L, answers.part2());
    }
}
