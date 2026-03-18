package com.adventofcode.aoc2025;

public class Dag03 extends Dag {

    private static final int BATTERIJEN_PER_REEKS = 12;

    private long totaalJoltage = 0;

    @Override
    protected void verwerkPatroon(String patroon) {
        totaalJoltage = Math.addExact(totaalJoltage, berekenMaxJoltage(patroon, BATTERIJEN_PER_REEKS));
    }

    static long berekenMaxJoltage(String reeks, int aantal) {
        valideerReeksEnAantal(reeks, aantal);
        return berekenMaxJoltageZonderValidatie(reeks, aantal);
    }

    private static void valideerReeksEnAantal(String reeks, int aantal) {
        if (aantal < 0) throw new IllegalArgumentException("aantalTeKiezenBatterijen < 0: " + aantal);
        if (aantal == 0) return;
        if (reeks.length() < aantal)
            throw new IllegalArgumentException("Reeks te kort: " + reeks.length() + " < " + aantal);
        for (int i = 0; i < reeks.length(); i++) {
           char teken = reeks.charAt(i);
            if (teken < '0' || teken > '9') throw new IllegalArgumentException("Ongeldige reeks (niet-cijfer): " + reeks);
        }
    }

    private static long berekenMaxJoltageZonderValidatie(String reeks, int aantal) {
        long resultaat = 0L;
        int startIndex = 0;
        int lengte = reeks.length();

        for (int positie = 0; positie < aantal; positie++) {
            int eindExclusief = lengte - (aantal - positie - 1);

            char beste = '0';
            int besteIndex = startIndex;

            for (int i = startIndex; i < eindExclusief; i++) {
                char teken = reeks.charAt(i);
                if (teken > beste) {
                    beste = teken;
                    besteIndex = i;
                    if (beste == '9') break;
                }
            }

            resultaat = Math.addExact(Math.multiplyExact(resultaat, 10L), (long) (beste - '0'));
            startIndex = besteIndex + 1;
        }
        return resultaat;
    }

    long getTotaalJoltage() {
        return totaalJoltage;
    }
}
