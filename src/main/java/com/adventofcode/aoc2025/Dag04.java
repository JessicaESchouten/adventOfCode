package com.adventofcode.aoc2025;

public class Dag04 {
    // bereikbaarheid met vorkheftruck minder dan 4 rollen
    // rij van 8 aaneengesloten

    static int berekenAantalToegankelijkeRollen(String diagram) {
        char[][] raster = parseDiagram(diagram);

        int count = 0;
        for (int aantalRollen = 0; aantalRollen < raster.length; aantalRollen++) {
            for (int aantal = 0; aantal < raster[aantalRollen].length; aantal++) {
                if (raster[aantalRollen][aantal] != '@') continue;
                if (telAangrenzendeRollen(raster, aantalRollen, aantal) < 4) count++;
            }
        }
        return count;
    }

    private static int telAangrenzendeRollen(char[][] raster, int r, int c) {
        int rows = raster.length;
        int cols = raster[0].length;

        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int rr = r + dr;
                int cc = c + dc;
                if (rr < 0 || rr >= rows || cc < 0 || cc >= cols) continue;
                if (raster[rr][cc] == '@') count++;
            }
        }
        return count;
    }

    private static char[][] parseDiagram(String diagram) {
        String[] lines = diagram.strip().split("\\R+");
        int rows = lines.length;
        int cols = lines[0].length();

        char[][] raster = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            if (lines[r].length() != cols) {
                throw new IllegalArgumentException("Niet rechthoekig diagram op rij " + r);
            }
            raster[r] = lines[r].toCharArray();
        }
        return raster;

    }
}
