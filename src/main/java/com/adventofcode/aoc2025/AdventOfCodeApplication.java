package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class AdventOfCodeApplication {

    public static void main(String[] args) throws IOException {
        Day01 dag01 = new Day01();
        Path pad1 = Path.of("src/main/resources/aoc2025/day01.txt");
        verwerkBestand(pad1, dag01::verwerkRegel);
        System.out.println("Nullen als eindstand (per draai): " + dag01.aantalEindstandNullen);
        System.out.println("Totaal van alle nullen (onderweg + eindstap): " + dag01.totaalAantalNullen);

        Day02 dag02 = new Day02();
        Path pad2 = Path.of("src/main/resources/aoc2025/day02.txt");
        verwerkBestand(pad2, dag02::verwerkRegel);
        System.out.println("Som deel 1 = " + dag02.berekenAntwoordEersteDeel());
        System.out.println("Som deel 2 = " + dag02.berekenAntwoordTweedeDeel());

        Day03 dag03 = new Day03();
        Path pad3 = Path.of("src/main/resources/aoc2025/day03.txt");
        verwerkBestand(pad3, dag03::verwerkRegel);
        System.out.println("Som van de batterijen = " + dag03.getTotaalJoltage());

        Path pad4 = Path.of("src/main/resources/aoc2025/day04.txt");
        var dag04Regels = Files.readAllLines(pad4, StandardCharsets.UTF_8)
                .stream()
                .map(String::trim)
                .filter(regel -> !regel.isEmpty())
                .toList();

        System.out.println("Aantal rollen = " + Day04.solvePart1(dag04Regels));
        System.out.println("Totaal aantal rollen dat verwijderd kan worden: " + Day04.solvePart2(dag04Regels));
    }

    protected static void verwerkBestand(Path pad, Consumer<String> verwerkRegel) throws IOException {
        try (var regelsUitBestand = Files.lines(pad, StandardCharsets.UTF_8)) {
            regelsUitBestand
                    .map(String::trim)
                    .filter(regel -> !regel.isEmpty())
                    .forEach(verwerkRegel);
        }
    }
}
