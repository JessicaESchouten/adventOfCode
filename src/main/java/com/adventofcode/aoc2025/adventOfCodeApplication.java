package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class adventOfCodeApplication {

    static void main(String[] args) throws IOException {
        Dag01 dag01 = new Dag01();

        Path pad1 = Path.of("src/main/resources/aoc2025/dag01.txt");
        verwerkBestand(pad1, dag01::verwerkRegel);

        System.out.println("Eindstand pijl: " + dag01.pijl);
        System.out.println("Nullen als eindstand (per draai): " + dag01.aantalEindstandNullen);
        System.out.println("Totaal van alle nullen (onderweg + eindstap): " + dag01.totaalAantalNullen);

        Dag02 dag02 = new Dag02();

        Path pad2 = Path.of("src/main/resources/aoc2025/dag02.txt");
        verwerkBestand(pad2, dag02::verwerkRegel);

        System.out.println("Som van de ongeldige codes 1 = " + dag02.getAnswerPart1());
        System.out.println("Som van de ongeldige codes 2 = " + dag02.getAnswerPart2());

        Dag03 dag03 = new Dag03();

        Path pad3 = Path.of("src/main/resources/aoc2025/dag03.txt");
        verwerkBestand(pad3, dag03::verwerkRegel);

        System.out.println("Som van de batterijen 1 = " + dag03.getMaxJoltage1());
        System.out.println("Som van de batterijen 2 = " + dag03.getMaxJoltage2());
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
