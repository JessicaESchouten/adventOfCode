package com.adventofcode.aoc2025;

public class Dag02 {

    private long somOngeldigeCodes = 0;

    protected void verwerkRegel(String regel) {
        for (String patroon : regel.trim().split("\\s*,\\s*")) {
            if (patroon.isEmpty()) continue;
            verwerkPatroon(patroon);
        }
    }

    protected void verwerkPatroon(String patroon) {
        String teCheckenPatroon = patroon.trim();
        if (teCheckenPatroon.isEmpty()) return;

        // Ondersteun ranges zoals "11-22": verwerk alle codes in de range (inclusief start/eind).
        if (teCheckenPatroon.contains("-")) {
            String[] patroonDelen = teCheckenPatroon.split("\\s*-\\s*", 2);
            if (patroonDelen.length != 2) {
                throw new IllegalArgumentException("Ongeldige range: " + patroon);
            }

            long start = Long.parseLong(patroonDelen[0]);
            long einde = Long.parseLong(patroonDelen[1]);
            if (start > einde) {
                throw new IllegalArgumentException("start > einde: " + patroon);
            }

            for (long code = start; ; code++) {
                verwerkCodeItem(Long.toString(code), code);
                if (code == einde) break;
            }
            return;
        }

        long codeWaarde = Long.parseLong(teCheckenPatroon);
        verwerkCodeItem(teCheckenPatroon, codeWaarde);
    }

    private void verwerkCodeItem(String codeItem, long codeWaarde) {
        boolean geldig =
            checkPatroonOpNulAanDeStart(codeItem) &&
            checkGeldigheidHerhalendPatroon(codeItem);

        if (!geldig) {
            somOngeldigeCodes = Math.addExact(somOngeldigeCodes, codeWaarde);
        }
    }

    protected boolean checkPatroonOpNulAanDeStart(String patroon) {
        return !patroon.startsWith("0");
    }

    protected boolean checkGeldigheidHerhalendPatroon(String patroon) {
        int lengte = patroon.length();
        if (lengte < 2) return true; // te kort om een herhaling (minstens 2x) te zijn

        // False als de hele string bestaat uit een kleiner basispatroon dat >= 2x herhaald wordt.
        for (int volgendPatroonStartIndex = 1; volgendPatroonStartIndex <= lengte / 2; volgendPatroonStartIndex++) {
            if (lengte % volgendPatroonStartIndex != 0) continue; // basispatroon moet precies passen

            String basisPatroon = patroon.substring(0, volgendPatroonStartIndex); // eerste stuk ter vergelijking

            boolean volledigHerhaald = true;
            int startIndex = volgendPatroonStartIndex; // start van het 2e stuk (het 1e start op index 0)
            while (startIndex < lengte) {
                if (!patroon.startsWith(basisPatroon, startIndex)) {
                    volledigHerhaald = false;
                    break;
                }
                startIndex += volgendPatroonStartIndex; // spring naar het begin van het volgende stuk
            }

            if (volledigHerhaald) return false;
        }

        return true;
    }

    protected long getSomOngeldigeCodes() {
        return somOngeldigeCodes;
    }
}