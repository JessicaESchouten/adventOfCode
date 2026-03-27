package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.file.Path;

public class AdventOfCodeApplication {

    static void main(String[] args) throws IOException {
        Day01 day01 = new Day01();
        Path day01Path = Path.of("src/main/resources/aoc2025/day01.txt");
        Inputs.forEachLine(day01Path, day01::processLine);
        System.out.println("End state zeros (per turn): " + day01.endStateZeroCount);
        System.out.println("Total zeros (during + end step): " + day01.totalZeroCount);

        Day02 day02 = new Day02();
        Path day02Path = Path.of("src/main/resources/aoc2025/day02.txt");
        Inputs.forEachLine(day02Path, day02::processLine);
        System.out.println("Sum part 1 = " + day02.solvePart1());
        System.out.println("Sum part 2 = " + day02.solvePart2());

        Day03 day03 = new Day03();
        Path day03Path = Path.of("src/main/resources/aoc2025/day03.txt");
        Inputs.forEachLine(day03Path, day03::processLine);
        System.out.println("Battery sum part 1 = " + day03.solvePart1());
        System.out.println("Battery sum part 2 = " + day03.solvePart2());

        Path day04Path = Path.of("src/main/resources/aoc2025/day04.txt");
        var day04Lines = Inputs.readNonBlankTrimmedLines(day04Path);
        System.out.println("Accessible rolls (part 1) = " + Day04.solvePart1(day04Lines));
        System.out.println("Total removable rolls (part 2) = " + Day04.solvePart2(day04Lines));

        Path day05Path = Path.of("src/main/resources/aoc2025/day05.txt");
        String day05Input = Inputs.readUtf8(day05Path);
        System.out.println("Fresh ingredient IDs in available list (part 1) = "
            + Day05.countFreshAvailableIds(day05Input));
        System.out.println("Distinct fresh ingredient IDs in ranges (part 2) = "
            + Day05.countDistinctFreshIdsInRanges(day05Input));
    }
}
