package com.adventofcode.aoc2025;

public class Dag01 extends Dag {

    public static final int START_CIJFER = 50;

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
            case 'L' -> draaiNaarLinks(pijl, aantal);
            case 'R' -> draaiNaarRechts(pijl, aantal);
            default -> throw new IllegalArgumentException("Onbekende regel: " + patroon);
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