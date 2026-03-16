package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.file.Path;

public class adventOfCodeApplication {

    public static void main(String[] args) throws IOException {
        Dag01 dag01 = new Dag01();

        Path pad1 = Path.of("src/main/resources/aoc2025/dag01.txt");
        dag01.verwerkBestand(pad1);

        System.out.println("Eindstand pijl: " + dag01.pijl);
        System.out.println("Nullen als eindstand (per draai): " + dag01.aantalEindstandNullen);
        System.out.println("Totaal van alle nullen (onderweg + eindstap): " + dag01.totaalAantalNullen);

        Dag02 dag02 = new Dag02();

        Path pad2 = Path.of("src/main/resources/aoc2025/dag02.txt");
        dag02.verwerkBestand(pad2);

        System.out.println("Som van de ongeldige codes = " + dag02.getSomOngeldigeCodes());
    }
}
