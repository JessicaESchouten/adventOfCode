package com.adventofcode.aoc2025;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Dag02 {

    private long somOngeldigeCodes = 0;

    protected void verwerkBestand(Path pad) throws IOException {
        try (var regelsUitBestand = Files.lines(pad, StandardCharsets.UTF_8)) {
            regelsUitBestand
                    .map(String::trim)
                    .filter(regel -> !regel.isEmpty())
                    .forEach(this::verwerkRegel);
        }
    }

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
        if (patroon.length() % 2 != 0) {
            // oneven lengte: overslaan
            return true;
        }

        int midden = patroon.length() / 2;
        String links = patroon.substring(0, midden);
        String rechts = patroon.substring(midden);

        return !links.equals(rechts);
    }

    protected long getSomOngeldigeCodes() {
        return somOngeldigeCodes;
    }
}