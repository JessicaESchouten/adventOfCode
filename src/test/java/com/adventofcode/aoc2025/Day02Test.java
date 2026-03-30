package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day02Test {

    private static final String PUZZLE_EXAMPLE_INPUT =
            "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,"
                    + "1698522-1698528,446443-446449,38593856-38593862,565653-565659,"
                    + "824824821-824824827,2121212118-2121212124";

    @Test
    void puzzleExample_part1_is_1227775554() {
        Day02 day02 = new Day02();
        Day.Answers answers = day02.solve(PUZZLE_EXAMPLE_INPUT);

        Assertions.assertEquals(1227775554L, answers.part1());
    }

    @Test
    void puzzleExample_part2_is_4174379265() {
        Day02 day02 = new Day02();
        Day.Answers answers = day02.solve(PUZZLE_EXAMPLE_INPUT);

        Assertions.assertEquals(4174379265L, answers.part2());
    }
}
