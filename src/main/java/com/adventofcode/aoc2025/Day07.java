package com.adventofcode.aoc2025;

import java.util.List;

public final class Day07 extends Day {

    public Day07() {
        super("day07");
    }

    @Override
    protected Answers solve(String input) {
        return new Answers(countSplits(input), null);
    }

    static long countSplits(String input) {
        List<String> lines = List.of(input.split("\\R"));

        int startX = -1;
        int startY = -1;
        for (int y = 0; y < lines.size(); y++) {
            int x = lines.get(y).indexOf('S');
            if (x >= 0) {
                startX = x;
                startY = y;
                break;
            }
        }

        int width = lines.getFirst().length();
        boolean[] activeColumns = new boolean[width];
        activeColumns[startX] = true;

        long splitCount = 0L;
        for (int y = startY + 1; y < lines.size(); y++) {
            String row = lines.get(y);
            boolean[] nextColumns = new boolean[width];

            for (int x = 0; x < width; x++) {
                if (!activeColumns[x]) continue;

                if (row.charAt(x) == '^') {
                    splitCount++;
                    if (x > 0) nextColumns[x - 1] = true;
                    if (x < width - 1) nextColumns[x + 1] = true;
                } else {
                    nextColumns[x] = true;
                }
            }

            activeColumns = nextColumns;
        }

        return splitCount;
    }
}

