package com.adventofcode.aoc2025;

import lombok.Getter;

@Getter
public class Dag03 {

    private long maxJoltage1 = 0;
    private long maxJoltage2 = 0;

    void verwerkRegel(String line) {
        maxJoltage1 = maxJoltage1 + maxJoltage(line.trim(), 2, 0);
        maxJoltage2 = maxJoltage2 + maxJoltage(line.trim(), 12, 0);
    }

    static long maxJoltage(String bank, int remainingBatteries, long result) {
        if (remainingBatteries == 0) {
            return result;
        } else {
            int until = bank.length() - remainingBatteries + 1;
            int value = bank.substring(0, until).chars().max().orElseThrow();
            int index = bank.indexOf(value);
            int digit = value - 48; // char '0' == int 48, char '1' == int 49, ...
            return  maxJoltage(bank.substring(index + 1), remainingBatteries - 1, result * 10 + digit);
        }
    }
}
