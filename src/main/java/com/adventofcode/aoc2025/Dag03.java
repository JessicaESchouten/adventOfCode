package com.adventofcode.aoc2025;

public class Dag03 {

    private long somTotaalJoltage = 0;

    protected void verwerkRegel(String regel) {
        String bank = regel.trim();
        if (bank.isEmpty()) return;
        somTotaalJoltage += maximaleJoltageVoorBank(bank);
    }

    protected static int maximaleJoltageVoorBank(String bank) {
        int hoogsteJoltage = -1;

        // Probeer elk cijfer in de bank als "eerste" gekozen batterij (tiental).
        for (int eersteIndex = 0; eersteIndex < bank.length(); eersteIndex++) {
            int eersteCijfer = bank.charAt(eersteIndex) - '0';

            int hoogsteCijferRechts = -1;
            for (int tweedeIndex = eersteIndex + 1; tweedeIndex < bank.length(); tweedeIndex++) {
                int cijfer = bank.charAt(tweedeIndex) - '0';
                hoogsteCijferRechts = Math.max(hoogsteCijferRechts, cijfer);
            }

            if (hoogsteCijferRechts != -1) {
                int joltage = eersteCijfer * 10 + hoogsteCijferRechts;
                hoogsteJoltage = Math.max(hoogsteJoltage, joltage);
            }
        }

        return hoogsteJoltage;
    }

    protected long getTotaalJoltage() {
        return somTotaalJoltage;
    }
}