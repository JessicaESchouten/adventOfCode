package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03Test {

    @Test
    void computeMaxJoltage_twoHighestDigits_inIncreasingSequence() {
        Assertions.assertEquals(46L, Day03.computeMaxJoltage("124116", 2));
    }

    @Test
    void computeMaxJoltage_exactlyTwoDigits() {
        Assertions.assertEquals(47L, Day03.computeMaxJoltage("47", 2));
    }

    @Test
    void computeMaxJoltage_preservesOrder() {
        // Highest digits are 9 and 8; 9 appears before 8, so result is 98.
        Assertions.assertEquals(98L, Day03.computeMaxJoltage("984", 2));
    }

    @Test
    void computeMaxJoltage_equalMaxima() {
        Assertions.assertEquals(99L, Day03.computeMaxJoltage("9991", 2));
        Assertions.assertEquals(99L, Day03.computeMaxJoltage("1999", 2));
    }

    @Test
    void processLine_12Digits_isAddedAsNumber() {
        Day03 day03 = new Day03();

        day03.processLine("000000000099");

        Assertions.assertEquals(99L, day03.getTotalJoltage());
    }

    @Test
    void processLine_multipleLines_accumulates() {
        Day03 day03 = new Day03();

        // Two sequences: total joltage must add up rather than overwrite.
        day03.processLine("000000000099"); // 99
        day03.processLine("000000000023"); // 23

        Assertions.assertEquals(122L, day03.getTotalJoltage());
    }

    @Test
    void processLine_exampleFromAssignment_totalJoltage_with12BatteriesPerSequence() {
        Day03 day03 = new Day03();

        day03.processLine("987654321111111");
        day03.processLine("811111111111119");
        day03.processLine("234234234234278");
        day03.processLine("818181911112111");

        Assertions.assertEquals(3121910778619L, day03.getTotalJoltage());
    }
}
