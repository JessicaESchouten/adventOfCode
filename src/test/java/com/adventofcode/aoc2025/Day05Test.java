package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day05Test {

    @Test
    void countsFreshIds_puzzleExample() {
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

        // Fresh ids here: 5 (3-5), 11 (10-14), 17 (16-20 and 12-18) => 3 total
        Assertions.assertEquals(3L, Day05.countFreshIds(input));
    }

    @Test
    void countsFreshIds_puzzleExample_crlfInput() {
        String input =
                "3-5\r\n" +
                "10-14\r\n" +
                "16-20\r\n" +
                "12-18\r\n" +
                "\r\n" +
                "1\r\n" +
                "5\r\n" +
                "8\r\n" +
                "11\r\n" +
                "17\r\n" +
                "32\r\n";

        Assertions.assertEquals(3L, Day05.countFreshIds(input));
    }
}
