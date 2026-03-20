package com.adventofcode.aoc2025;

import java.util.function.BinaryOperator;

public class Dag01 extends Dag {

    public static final int START_CIJFER = 50;

    static final BinaryOperator<Integer> rechts = Integer::sum;
    static final BinaryOperator<Integer> links = (a, b) -> a - b;

    int pijl = START_CIJFER;

    // Alleen eindstand per draai
    int aantalEindstandNullen = 0;

    // Alle nullen door clicks (onderweg + eindstap)
    int totaalAantalNullen = 0;

    @Override
    protected void verwerkPatroon(String patroon) {
        char richting = patroon.charAt(0);
        int aantal = Integer.parseInt(patroon.substring(1).trim());

        switch (richting) {
            case 'L' -> draaiNaar(links, pijl, aantal);
            case 'R' -> draaiNaar(rechts, pijl, aantal);
            default -> throw new IllegalArgumentException("Onbekende regel: " + patroon);
        }
    }

    protected void draaiNaar(BinaryOperator<Integer> richting, int beginCijfer, int aantal) {
        telNullenTijdensDraaiNaar(richting, beginCijfer, aantal);

        pijl = Math.floorMod(richting.apply(beginCijfer, aantal), 100);
        if (pijl == 0) aantalEindstandNullen++;
    }

    protected void telNullenTijdensDraaiNaar(BinaryOperator<Integer> richting, int beginCijfer, int aantal) {
        if (aantal <= 0) return;

        // clicks: 1 stap t/m N stappen (inclusief eindstap), ongeacht richting
        int stap1 = richting.apply(beginCijfer, 1);
        int stapN = richting.apply(beginCijfer, aantal);
        int min = Math.min(stap1, stapN);
        int max = Math.max(stap1, stapN);
        totaalAantalNullen += telHonderdtallenBinnenBereik(min, max);
    }

    private static int telHonderdtallenBinnenBereik(int min, int max) {
        if (min > max) return 0;

        // Aantal veelvouden van 100 in [min..max], werkt ook voor negatieve grenzen.
        return Math.floorDiv(max, 100) - Math.floorDiv(min - 1, 100);
    }
}