package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

    @Test
    void puzzleExample_part1_and_part2_lf() {
        String input = """
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
                """;

        Day06 day06 = new Day06();
        Day.Answers answers = day06.solve(input);
        Assertions.assertEquals(4277556L, answers.part1());
        Assertions.assertEquals(3263827L, answers.part2());
    }

    @Test
    void puzzleExample_part1_and_part2_crlf() {
        String input = """
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
                """.replace("\n", "\r\n");

        Day06 day06 = new Day06();
        Day.Answers answers = day06.solve(input);
        Assertions.assertEquals(4277556L, answers.part1());
        Assertions.assertEquals(3263827L, answers.part2());
    }
}
