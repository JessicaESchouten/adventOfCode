package com.adventofcode.aoc2025;

abstract class Day {

    public final void verwerkRegel(String regel) {
        if (regel == null) return;

        for (String deel : splitsRegel(regel)) {
            String patroon = deel.trim();
            if (patroon.isEmpty()) continue;
            verwerkPatroon(patroon);
        }
    }

    protected String[] splitsRegel(String regel) {
        return new String[] { regel };
    }

    protected abstract void verwerkPatroon(String patroon);
}
