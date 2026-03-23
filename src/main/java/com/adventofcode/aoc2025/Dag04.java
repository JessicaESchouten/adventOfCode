package com.adventofcode.aoc2025;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Dag04 {

    private int answer1 = 0;
    private int answer2 = 0;

    public record Pos(int x, int y) {
        public Pos plus(Pos that) {
            return of(this.x + that.x, this.y + that.y);
        }

        public Set<Pos> adjoint8() {
            return offset8.stream().map(this::plus).collect(Collectors.toSet());
        }

        public static Pos of(int x, int y) {
            return new Pos(x, y);
        }

        public final static Set<Pos> offset8 = Set.of(
            of(-1, -1), of(-1, 0), of(-1, 1), of(0, -1), of(0, 1), of(1, -1), of(1, 0), of(1, 1)
        );
    }

    private static Set<Pos> parseLines(List<String> lines) {
        Set<Pos> rolls = new HashSet<>();
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).trim().length(); x++) {
                if (lines.get(y).trim().charAt(x) == '@') rolls.add(new Pos(x, y));
            }
        }
        return rolls;
    }

    private static Set<Pos> accessible(Set<Pos> rolls) {
        return rolls.stream().filter(roll -> {
            Set<Pos> accessible = roll.adjoint8();
            accessible.retainAll(rolls);
            return accessible.size() < 4;
        }).collect(Collectors.toSet());
    }

    private static Set<Pos> clearAll(Set<Pos> rolls) {
        Set<Pos> accessible = accessible(rolls);
        if (accessible.isEmpty()) {
            return rolls;
        } else {
            Set<Pos> next = new HashSet<>(rolls);
            next.removeAll(accessible);
            return clearAll(next);
        }
    }

    public void solve(List<String> diagram) {
        Set<Pos> rolls = parseLines(diagram);
        answer1 = accessible(rolls).size();

        Set<Pos> cleared = clearAll(rolls);
        rolls.removeAll(cleared);
        answer2 = rolls.size();
    }
}
