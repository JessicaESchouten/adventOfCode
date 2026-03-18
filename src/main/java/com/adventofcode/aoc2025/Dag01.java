package com.adventofcode.aoc2025;

import java.util.function.BinaryOperator;

public class Dag01 {

    public static final int START_CIJFER = 50;

    int pijl = START_CIJFER;

    // Alleen eindstand per draai
    int aantalEindstandNullen = 0;

    // Alle nullen door clicks (onderweg + eindstap)
    int totaalAantalNullen = 0;

    static BinaryOperator<Integer> rechts = (Integer a, Integer b) -> a + b;
    static BinaryOperator<Integer> links  = (Integer a, Integer b) -> a - b;

    void verwerkRegel(String regel) {
        char richting = regel.charAt(0);
        int aantal = Integer.parseInt(regel.substring(1).trim());

        switch (richting) {
            case 'L' -> draaiNaar(links, pijl, aantal);
            case 'R' -> draaiNaar(rechts, pijl, aantal);
            default -> throw new IllegalArgumentException("Onbekende regel: " + regel);
        }
    }

    protected void draaiNaar(BinaryOperator<Integer> richting, int beginCijfer, int rCijfer) {
        telNullenTijdensDraaiNaar(richting, beginCijfer, rCijfer);

        pijl = Math.floorMod(richting.apply(beginCijfer, rCijfer), 100);
        if (pijl == 0) aantalEindstandNullen++;
    }

    protected void telNullenTijdensDraaiNaar(BinaryOperator<Integer> richting, int beginCijfer, int rCijfer) {
        if (rCijfer <= 0) return;

        // clicks: begin+1 .. begin+r (inclusief eindstap)
        int min = richting.apply(beginCijfer, 1);
        int max = richting.apply(beginCijfer, rCijfer);
        totaalAantalNullen += telHonderdtallenBinnenBereik(min, max);
    }

    private static int telHonderdtallenBinnenBereik(int min, int max) {
        if (min > max) return 0;

        // Aantal veelvouden van 100 in [min..max], werkt ook voor negatieve grenzen.
        return Math.floorDiv(max, 100) - Math.floorDiv(min - 1, 100);
    }
}