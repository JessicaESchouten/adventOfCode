package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.file.Path;

public abstract class Day {

    public record Answers(Object part1, Object part2) {}

    private final String inputName;

    protected Day(String inputName) {
        this.inputName = inputName;
    }

    protected abstract Answers solve(String input);

    protected Path defaultInputPath() {
        return Path.of("src/main/resources/aoc2025/%s.txt".formatted(inputName));
    }

    public final void run() throws IOException {
        String input = Inputs.readUtf8(defaultInputPath());
        Answers answers = solve(input);
        System.out.println(answers.part1());
        System.out.println(answers.part2());
    }
}
