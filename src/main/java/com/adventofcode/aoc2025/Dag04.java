package com.adventofcode.aoc2025;

import java.util.ArrayDeque;

public class Dag04 extends Dag {

    private static final int TOEGANKELIJKHEID_DREMPEL = 4;
    private static final int[] RIJ_VERSCHUIVING = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] KOLOM_VERSCHUIVING = {-1, 0, 1, -1, 1, -1, 0, 1};

    private final StringBuilder diagram = new StringBuilder();

    @Override
    protected void verwerkPatroon(String patroon) {
        if (!diagram.isEmpty()) diagram.append('\n');
        diagram.append(patroon);
    }

    int berekenAntwoordEersteDeel() {
        return berekenAantalToegankelijkeRollen(diagram.toString());
    }

    int berekenAntwoordTweedeDeel() {
        return berekenTotaalVerwijderbareRollen(diagram.toString());
    }

    private static int berekenAantalToegankelijkeRollen(String diagram) {
        if (diagram == null || diagram.isBlank()) return 0;

        boolean[][] rolAanwezig = parseDiagramNaarRolAanwezigheid(diagram);
        int aantal = 0;

        for (int rijIndex = 0; rijIndex < rolAanwezig.length; rijIndex++) {
            for (int kolomIndex = 0; kolomIndex < rolAanwezig[rijIndex].length; kolomIndex++) {
                if (!rolAanwezig[rijIndex][kolomIndex]) continue;
                if (telAangrenzendeRollen(rolAanwezig, rijIndex, kolomIndex) < TOEGANKELIJKHEID_DREMPEL) aantal++;
            }
        }
        return aantal;
    }

    private static int berekenTotaalVerwijderbareRollen(String diagram) {
        if (diagram == null || diagram.isBlank()) return 0;

        boolean[][] rolAanwezig = parseDiagramNaarRolAanwezigheid(diagram);
        int aantalRijen = rolAanwezig.length;
        int aantalKolommen = rolAanwezig[0].length;

        int[][] aangrenzendeRollen = new int[aantalRijen][aantalKolommen];
        ArrayDeque<Integer> teVerwijderen = new ArrayDeque<>();

        // Init: bereken buur-aantallen en enqueue alles dat direct toegankelijk is.
        for (int rijIndex = 0; rijIndex < aantalRijen; rijIndex++) {
            for (int kolomIndex = 0; kolomIndex < aantalKolommen; kolomIndex++) {
                if (!rolAanwezig[rijIndex][kolomIndex]) continue;
                int buren = telAangrenzendeRollen(rolAanwezig, rijIndex, kolomIndex);
                aangrenzendeRollen[rijIndex][kolomIndex] = buren;
                if (buren < TOEGANKELIJKHEID_DREMPEL) {
                    teVerwijderen.addLast(encodePositie(rijIndex, kolomIndex, aantalKolommen));
                }
            }
        }

        int totaalVerwijderd = 0;
        while (!teVerwijderen.isEmpty()) {
            int positie = teVerwijderen.removeFirst();
            int rijIndex = positie / aantalKolommen;
            int kolomIndex = positie % aantalKolommen;

            if (!rolAanwezig[rijIndex][kolomIndex]) continue;
            if (aangrenzendeRollen[rijIndex][kolomIndex] >= TOEGANKELIJKHEID_DREMPEL) continue;

            rolAanwezig[rijIndex][kolomIndex] = false;
            totaalVerwijderd++;

            for (int i = 0; i < 8; i++) {
                int buurRij = rijIndex + RIJ_VERSCHUIVING[i];
                int buurKolom = kolomIndex + KOLOM_VERSCHUIVING[i];

                if (buurRij < 0 || buurRij >= aantalRijen) continue;
                if (buurKolom < 0 || buurKolom >= aantalKolommen) continue;
                if (!rolAanwezig[buurRij][buurKolom]) continue;

                if (--aangrenzendeRollen[buurRij][buurKolom] == TOEGANKELIJKHEID_DREMPEL - 1) {
                    teVerwijderen.addLast(encodePositie(buurRij, buurKolom, aantalKolommen));
                }
            }
        }

        return totaalVerwijderd;
    }

    private static boolean[][] parseDiagramNaarRolAanwezigheid(String diagram) {
        String[] regels = diagram.strip().split("\\R+");
        int aantalRijen = regels.length;
        int aantalKolommen = regels[0].length();

        boolean[][] rolAanwezig = new boolean[aantalRijen][aantalKolommen];
        for (int rijIndex = 0; rijIndex < aantalRijen; rijIndex++) {
            if (regels[rijIndex].length() != aantalKolommen) {
                throw new IllegalArgumentException("Niet rechthoekig diagram op rij " + rijIndex);
            }
            for (int kolomIndex = 0; kolomIndex < aantalKolommen; kolomIndex++) {
                char teken = regels[rijIndex].charAt(kolomIndex);
                if (teken == '@') rolAanwezig[rijIndex][kolomIndex] = true;
                else if (teken != '.') throw new IllegalArgumentException("Ongeldig teken in diagram: '" + teken + "'");
            }
        }
        return rolAanwezig;
    }

    private static int telAangrenzendeRollen(boolean[][] rolAanwezig, int middenRij, int middenKolom) {
        int aantalRijen = rolAanwezig.length;
        int aantalKolommen = rolAanwezig[0].length;

        int aangrenzendeRollen = 0;
        for (int i = 0; i < 8; i++) {
            int buurRij = middenRij + RIJ_VERSCHUIVING[i];
            int buurKolom = middenKolom + KOLOM_VERSCHUIVING[i];

            if (buurRij < 0 || buurRij >= aantalRijen) continue;
            if (buurKolom < 0 || buurKolom >= aantalKolommen) continue;
            if (rolAanwezig[buurRij][buurKolom]) aangrenzendeRollen++;
        }
        return aangrenzendeRollen;
    }

    private static int encodePositie(int rijIndex, int kolomIndex, int aantalKolommen) {
        return rijIndex * aantalKolommen + kolomIndex;
    }
}
