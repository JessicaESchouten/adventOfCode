package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class adventOfCodeApplication {

    static void main() throws IOException {
        Dag01 dag01 = new Dag01();
        Path pad1 = Path.of("src/main/resources/aoc2025/dag01.txt");
        verwerkBestand(pad1, dag01::verwerkRegel);
        System.out.println("Nullen als eindstand (per draai): " + dag01.aantalEindstandNullen);
        System.out.println("Totaal van alle nullen (onderweg + eindstap): " + dag01.totaalAantalNullen);

        Dag02 dag02 = new Dag02();
        Path pad2 = Path.of("src/main/resources/aoc2025/dag02.txt");
        verwerkBestand(pad2, dag02::verwerkRegel);
        System.out.println("Som deel 1 = " + dag02.berekenAntwoordEersteDeel());
        System.out.println("Som deel 2 = " + dag02.berekenAntwoordTweedeDeel());

        Dag03 dag03 = new Dag03();
        Path pad3 = Path.of("src/main/resources/aoc2025/dag03.txt");
        verwerkBestand(pad3, dag03::verwerkRegel);
        System.out.println("Som van de batterijen = " + dag03.getTotaalJoltage());

        Dag04 dag04 = new Dag04();
        Path pad4 = Path.of("src/main/resources/aoc2025/dag04.txt");
        verwerkBestand(pad4, dag04::verwerkRegel);
        System.out.println("Aantal rollen = " + dag04.berekenAntwoordEersteDeel());
        System.out.println("Totaal aantal rollen dat verwijderd kan worden: " + dag04.berekenAntwoordTweedeDeel());
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
