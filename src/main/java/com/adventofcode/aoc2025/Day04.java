package com.adventofcode.aoc2025;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day04 {

    private static final char ROLL = '@';
    private static final int ACCESS_THRESHOLD = 4;
    private static final int[] NEIGHBOR_ROW_OFFSET = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static final int[] NEIGHBOR_COL_OFFSET = { -1, 0, 1, -1, 1, -1, 0, 1 };

    record Pos(int x, int y) { }

    public static long solvePart1(List<String> lines) {
        Set<Pos> rolls = parseLines(lines);
        return accessible(rolls).size();
    }

    public static long solvePart2(List<String> lines) {
        Set<Pos> rolls = parseLines(lines);
        return removeAllAccessible(rolls);
    }

    private static Set<Pos> parseLines(List<String> lines) {
        Set<Pos> rolls = new HashSet<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y).trim();
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == ROLL) rolls.add(new Pos(x, y));
            }
        }
        return rolls;
    }

    private static int countAdjacentRolls8(Set<Pos> rolls, Pos pos) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nx = pos.x + NEIGHBOR_COL_OFFSET[i];
            int ny = pos.y + NEIGHBOR_ROW_OFFSET[i];
            if (rolls.contains(new Pos(nx, ny))) count++;
        }
        return count;
    }

    private static Set<Pos> accessible(Set<Pos> rolls) {
        Set<Pos> accessible = new HashSet<>();
        for (Pos roll : rolls) {
            if (countAdjacentRolls8(rolls, roll) < ACCESS_THRESHOLD) accessible.add(roll);
        }
        return accessible;
    }

    static long removeAllAccessible(Set<Pos> rolls) {
        Set<Pos> accessible = accessible(rolls);
        if (accessible.isEmpty()) return 0L;

        rolls.removeAll(accessible);
        return (long) accessible.size() + removeAllAccessible(rolls);
    }
}
