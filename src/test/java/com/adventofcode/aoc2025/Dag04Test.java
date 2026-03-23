package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Dag04Test {

    @Test
    void testDag04() throws Exception {
        Path path = Path.of("src/main/resources/aoc2025/dag04.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        Dag04 puzzle = new Dag04();
        puzzle.solve(lines);

        Assertions.assertEquals(1508, puzzle.getAnswer1());
        Assertions.assertEquals(8538, puzzle.getAnswer2());
    }
}

