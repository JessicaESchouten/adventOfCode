package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Dag01 {

    public static final int START_CIJFER = 50;

    int pijl = START_CIJFER;

    // Alleen eindstand per draai
    int aantalEindstandNullen = 0;

    // Alle nullen door clicks (onderweg + eindstap)
    int totaalAantalNullen = 0;

    public static void main(String[] args) throws IOException {
        Dag01 dag01 = new Dag01();

        Path pad = Path.of("src/main/resources/aoc2025/dag01.txt");
        dag01.verwerkBestand(pad);

        System.out.println("Eindstand pijl: " + dag01.pijl);
        System.out.println("Nullen als eindstand (per draai): " + dag01.aantalEindstandNullen);
        System.out.println("Totaal van alle nullen (onderweg + eindstap): " + dag01.totaalAantalNullen);
    }

    protected void verwerkBestand(Path pad) throws IOException {
        try (var regels = Files.lines(pad, StandardCharsets.UTF_8)) {
            regels.map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .forEach(this::verwerkRegel);
        }
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
        telNullenTijdensDraaiRechts(beginCijfer, rCijfer);

        pijl = Math.floorMod(beginCijfer + rCijfer, 100);
        if (pijl == 0) aantalEindstandNullen++;
    }

    protected void draaiNaarLinks(int beginCijfer, int lCijfer) {
        telNullenTijdensDraaiLinks(beginCijfer, lCijfer);

        pijl = Math.floorMod(beginCijfer - lCijfer, 100);
        if (pijl == 0) aantalEindstandNullen++;
    }

    protected void telNullenTijdensDraaiRechts(int beginCijfer, int rCijfer) {
        if (rCijfer <= 0) return;

        // clicks: begin+1 .. begin+r (inclusief eindstap)
        int min = beginCijfer + 1;
        int max = beginCijfer + rCijfer;
        totaalAantalNullen += telHonderdtallenBinnenBereik(min, max);
    }

    protected void telNullenTijdensDraaiLinks(int beginCijfer, int lCijfer) {
        if (lCijfer <= 0) return;

        // clicks: begin-1 .. begin-l (inclusief eindstap)
        int min = beginCijfer - lCijfer;
        int max = beginCijfer - 1;
        totaalAantalNullen += telHonderdtallenBinnenBereik(min, max);
    }

    private static int telHonderdtallenBinnenBereik(int min, int max) {
        if (min > max) return 0;

        // Aantal veelvouden van 100 in [min..max], werkt ook voor negatieve grenzen.
        return Math.floorDiv(max, 100) - Math.floorDiv(min - 1, 100);
    }
}