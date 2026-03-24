package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class AdventOfCodeApplication {

    public static void main(String[] args) throws IOException {
        Day01 day01 = new Day01();
        Path day01Path = Path.of("src/main/resources/aoc2025/day01.txt");
        processFile(day01Path, day01::processLine);
        System.out.println("End state zeros (per turn): " + day01.endStateZeroCount);
        System.out.println("Total zeros (during + end step): " + day01.totalZeroCount);

        Day02 day02 = new Day02();
        Path day02Path = Path.of("src/main/resources/aoc2025/day02.txt");
        processFile(day02Path, day02::processLine);
        System.out.println("Sum part 1 = " + day02.solvePart1());
        System.out.println("Sum part 2 = " + day02.solvePart2());

        Day03 day03 = new Day03();
        Path day03Path = Path.of("src/main/resources/aoc2025/day03.txt");
        processFile(day03Path, day03::processLine);
        System.out.println("Battery sum = " + day03.getTotalJoltage());

        Path day04Path = Path.of("src/main/resources/aoc2025/day04.txt");
        var day04Lines = Files.readAllLines(day04Path, StandardCharsets.UTF_8)
                .stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();
        System.out.println("Accessible rolls (part 1) = " + Day04.solvePart1(day04Lines));
        System.out.println("Total removable rolls (part 2) = " + Day04.solvePart2(day04Lines));

        Path day05Path = Path.of("src/main/resources/aoc2025/day05.txt");
        System.out.println("Amount of fresh ingredient id's = " + Day05.countFreshIds(day05Path));
    }

    static void processFile(Path path, Consumer<String> processLine) throws IOException {
        try (var lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .forEach(processLine);
        }
    }
}
