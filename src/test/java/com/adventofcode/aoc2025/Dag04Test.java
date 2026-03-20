package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Dag04Test {

    private static final String VOORBEELD_DIAGRAM = """
        ..@@.@@@@.
        @@@.@.@.@@
        @@@@@.@.@@
        @.@@@@..@.
        @@.@@@@.@@
        .@@@@@@@.@
        .@.@.@.@@@
        @.@@@.@@@@
        .@@@@@@@@.
        @.@.@@@.@.
        """;

    @Test
    void voorbeeldUitOpgave_aantalToegankelijkeRollen_is_13() {
        Dag04 dag04 = new Dag04();

        for (String regel : VOORBEELD_DIAGRAM.strip().split("\\R+")) {
            dag04.verwerkRegel(regel);
        }

        Assertions.assertEquals(13, dag04.berekenAntwoordEersteDeel());
    }

    @Test
    void voorbeeldUitOpgave_totaalVerwijderbareRollen_is_43() {
        Dag04 dag04 = new Dag04();

        for (String regel : VOORBEELD_DIAGRAM.strip().split("\\R+")) {
            dag04.verwerkRegel(regel);
        }

        Assertions.assertEquals(43, dag04.berekenAntwoordTweedeDeel());
    }
}
