package com.adventofcode.aoc2025;

public class Dag04 extends Dag {

    private final StringBuilder diagram = new StringBuilder();

    @Override
    protected void verwerkPatroon(String patroon) {
        if (!diagram.isEmpty()) diagram.append('\n');
        diagram.append(patroon);
    }

    int berekenAntwoord() {
        return berekenAantalToegankelijkeRollen(diagram.toString());
    }

    private static int berekenAantalToegankelijkeRollen(String diagram) {
        char[][] rooster = parseDiagramNaarRooster(diagram);

        int aantalToegankelijkeRollen = 0;
        for (int rijIndex = 0; rijIndex < rooster.length; rijIndex++) {
            for (int kolomIndex = 0; kolomIndex < rooster[rijIndex].length; kolomIndex++) {
                if (rooster[rijIndex][kolomIndex] != '@') continue;

                int aantalAangrenzendeRollen = telAangrenzendeRollen(rooster, rijIndex, kolomIndex);
                if (aantalAangrenzendeRollen < 4) {
                    aantalToegankelijkeRollen++;
                }
            }
        }
        return aantalToegankelijkeRollen;
    }

    private static int telAangrenzendeRollen(char[][] rooster, int middenRij, int middenKolom) {
        int aantalRijen = rooster.length;
        int aantalKolommen = rooster[0].length;

        int aangrenzendeRollen = 0;
        for (int rijOffset = -1; rijOffset <= 1; rijOffset++) {
            for (int kolomOffset = -1; kolomOffset <= 1; kolomOffset++) {
                if (rijOffset == 0 && kolomOffset == 0) continue;

                int buurRij = middenRij + rijOffset;
                int buurKolom = middenKolom + kolomOffset;

                if (buurRij < 0 || buurRij >= aantalRijen) continue;
                if (buurKolom < 0 || buurKolom >= aantalKolommen) continue;

                if (rooster[buurRij][buurKolom] == '@') aangrenzendeRollen++;
            }
        }
        return aangrenzendeRollen;
    }

    private static char[][] parseDiagramNaarRooster(String diagram) {
        String[] regels = diagram.strip().split("\\R+");
        int aantalRijen = regels.length;
        int aantalKolommen = regels[0].length();

        char[][] rooster = new char[aantalRijen][aantalKolommen];
        for (int rijIndex = 0; rijIndex < aantalRijen; rijIndex++) {
            if (regels[rijIndex].length() != aantalKolommen) {
                throw new IllegalArgumentException("Niet rechthoekig diagram op rij " + rijIndex);
            }
            rooster[rijIndex] = regels[rijIndex].toCharArray();
        }
        return rooster;
    }
}
