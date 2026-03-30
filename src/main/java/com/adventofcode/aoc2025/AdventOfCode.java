package com.adventofcode.aoc2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class AdventOfCode {

    private AdventOfCode() {}

    public static void main(String[] args) throws Exception {
        int day;
        if (args.length >= 1) {
            day = Integer.parseInt(args[0]);
        } else {
            System.out.print("Day: ");
            System.out.flush();
            String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
            day = Integer.parseInt(line);
        }

        String className = AdventOfCode.class.getPackageName() + ".Day%02d".formatted(day);
        Day solver = (Day) Class.forName(className).getDeclaredConstructor().newInstance();

        solver.run();
    }
}
