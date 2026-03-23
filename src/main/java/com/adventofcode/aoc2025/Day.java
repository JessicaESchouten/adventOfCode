package com.adventofcode.aoc2025;

abstract class Day {

    public final void processLine(String line) {
        if (line == null) return;

        for (String part : splitLine(line)) {
            String token = part.trim();
            if (token.isEmpty()) continue;
            processToken(token);
        }
    }

    protected String[] splitLine(String line) {
        return new String[] { line };
    }

    protected abstract void processToken(String token);
}
