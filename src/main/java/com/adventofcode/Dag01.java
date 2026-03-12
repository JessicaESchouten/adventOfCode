package com.adventofcode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Dag01 {

    public static final int START_CIJFER = 50;

    int pijl = START_CIJFER;
    int aantalNullen = 0;

    public static void main(String[] args) throws IOException {
        Dag01 dag01 = new Dag01();

        Path pad = Path.of("src/main/resources/input2025/dag01.txt");
        dag01.telAantalNullen(pad);

        System.out.println("Eindstand pijl: " + dag01.pijl);
        System.out.println("Nullen geteld: " + dag01.aantalNullen);
    }

    protected int telAantalNullen(Path pad) throws IOException {
        try (var regels = Files.lines(pad, StandardCharsets.UTF_8)) {
            regels.map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .forEach(this::verwerkRegel);
        }
        return pijl;
    }

    void verwerkRegel(String regel) {
        char richting = regel.charAt(0);
        int aantal = Integer.parseInt(regel.substring(1).trim());

        switch (richting) {
            case 'L' -> draaiNaarLinks(pijl, aantal);
            case 'R' -> draaiNaarRechts(pijl, aantal);
            default -> throw new IllegalArgumentException("Onbekende regel: " + regel);
        }
    }

    protected void draaiNaarRechts(int beginCijfer, int rCijfer) {
        // Altijd 0-99
        pijl = Math.floorMod(beginCijfer + rCijfer, 100);
        if (pijl == 0) aantalNullen++;
    }

    protected void draaiNaarLinks(int beginCijfer, int lCijfer) {
        // Altijd 0-99
        pijl = Math.floorMod(beginCijfer - lCijfer, 100);
        if (pijl == 0) aantalNullen++;
    }
}
