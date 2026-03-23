package com.adventofcode.aoc2025;

import java.util.ArrayDeque;
import java.util.List;

public class Day04 {

    private static final char ROLL = '@';
    private static final int ACCESS_THRESHOLD = 4;
    private static final int[] NEIGHBOR_ROW_OFFSET = {
            -1, -1, -1,
            0, 0,
            1, 1, 1
    };
    private static final int[] NEIGHBOR_COL_OFFSET = {
            -1, 0, 1,
            -1, 1,
            -1, 0, 1
    };

    /**
     * Part 1: Count how many rolls are accessible by a forklift
     * (fewer than {@link #ACCESS_THRESHOLD} rolls in the 8 adjacent cells).
     */
    public static long solvePart1(List<String> lines) {
        if (lines.isEmpty()) return 0;
        int rowCount = lines.size();
        int colCount = lines.getFirst().length();

        boolean[][] present = parseRollPresent(lines, rowCount, colCount);
        long accessible = 0;

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (!present[row][col]) continue;
                if (countAdjacentRolls8(present, rowCount, colCount, row, col) < ACCESS_THRESHOLD) {
                    accessible++;
                }
            }
        }

        return accessible;
    }

    /**
     * Part 2: Repeatedly remove any currently accessible rolls.
     * Removing rolls can make more rolls accessible; this returns the total number removed.
     */
    public static long solvePart2(List<String> lines) {
        if (lines.isEmpty()) return 0;
        int rowCount = lines.size();
        int colCount = lines.getFirst().length();

        boolean[][] present = parseRollPresent(lines, rowCount, colCount);
        int[][] neighborRollCount = new int[rowCount][colCount];
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (!present[row][col]) continue;
                int adjacent = countAdjacentRolls8(present, rowCount, colCount, row, col);
                neighborRollCount[row][col] = adjacent;
                if (adjacent < ACCESS_THRESHOLD) queue.addLast(encode(row, col, colCount));
            }
        }

        long removed = 0;
        while (!queue.isEmpty()) {
            int index = queue.removeFirst();
            int row = index / colCount;
            int col = index % colCount;

            if (!present[row][col]) continue;
            if (neighborRollCount[row][col] >= ACCESS_THRESHOLD) continue;

            present[row][col] = false;
            removed++;

            for (int i = 0; i < 8; i++) {
                int neighborRow = row + NEIGHBOR_ROW_OFFSET[i];
                int neighborCol = col + NEIGHBOR_COL_OFFSET[i];
                if (neighborRow < 0 || neighborRow >= rowCount) continue;
                if (neighborCol < 0 || neighborCol >= colCount) continue;
                if (!present[neighborRow][neighborCol]) continue;

                if (--neighborRollCount[neighborRow][neighborCol] == ACCESS_THRESHOLD - 1) {
                    queue.addLast(encode(neighborRow, neighborCol, colCount));
                }
            }
        }

        return removed;
    }

    private static int countAdjacentRolls8(boolean[][] present, int rowCount, int colCount, int row, int col) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int neighborRow = row + NEIGHBOR_ROW_OFFSET[i];
            int neighborCol = col + NEIGHBOR_COL_OFFSET[i];
            if (neighborRow < 0 || neighborRow >= rowCount) continue;
            if (neighborCol < 0 || neighborCol >= colCount) continue;
            if (present[neighborRow][neighborCol]) count++;
        }
        return count;
    }

    private static boolean[][] parseRollPresent(List<String> lines, int rowCount, int colCount) {
        boolean[][] present = new boolean[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            char[] rowChars = lines.get(row).toCharArray();
            for (int col = 0; col < colCount; col++) {
                present[row][col] = rowChars[col] == ROLL;
            }
        }
        return present;
    }

    private static int encode(int row, int col, int colCount) {
        return row * colCount + col;
    }
}
