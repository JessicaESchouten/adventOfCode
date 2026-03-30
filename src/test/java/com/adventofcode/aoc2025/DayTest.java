package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class DayTest {

    @Test
    void defaultInputPath_usesInputName() {
        Day day = new Day("day04") {
            @Override
            protected Answers solve(String input) {
                return new Answers(null, null);
            }
        };

        Assertions.assertEquals(Path.of("src/main/resources/aoc2025/day04.txt"), day.defaultInputPath());
    }

    @Test
    void run_readsFile_andPrintsTwoLines() throws Exception {
        Path tmp = Files.createTempFile("aoc-day", ".txt");
        Files.writeString(tmp, "abc", StandardCharsets.UTF_8);

        Day day = new Day("unused") {
            @Override
            protected Answers solve(String input) {
                Assertions.assertEquals("abc", input);
                return new Answers("p1", "p2");
            }

            @Override
            protected Path defaultInputPath() {
                return tmp;
            }
        };

        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
            day.run();
        } finally {
            System.setOut(originalOut);
        }

        Assertions.assertEquals("p1" + System.lineSeparator() + "p2" + System.lineSeparator(),
                out.toString(StandardCharsets.UTF_8));
    }

    @Test
    void main_instantiatesDayClassByNumber() throws Exception {
        Path tmp = Files.createTempFile("aoc-day-main", ".txt");
        Files.writeString(tmp, "ignored", StandardCharsets.UTF_8);
        Day99.inputPath = tmp;
        Day99.answers = new Day.Answers("p1", "p2");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
            AdventOfCode.main(new String[] { "99" });
        } finally {
            System.setOut(originalOut);
        }

        Assertions.assertEquals("p1" + System.lineSeparator() + "p2" + System.lineSeparator(),
                out.toString(StandardCharsets.UTF_8));
    }
}

final class Day99 extends Day {

    static Path inputPath;
    static Answers answers;

    Day99() {
        super("unused");
    }

    @Override
    protected Answers solve(String input) {
        return answers;
    }

    @Override
    protected Path defaultInputPath() {
        return inputPath;
    }
}
