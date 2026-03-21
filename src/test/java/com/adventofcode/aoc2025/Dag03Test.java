package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

class Dag03Test {

    @Test
    void berekenMaxJoltageZonderValidatie_met12BatterijenPerReeks() throws Exception {
        Dag03 dag03 = new Dag03();
        Path input = Path.of("./src/main/resources/aoc2025/dag03.txt");

        adventOfCodeApplication.verwerkBestand(input, dag03::verwerkRegel);

        Assertions.assertEquals(17031L, dag03.getMaxJoltage1());
        Assertions.assertEquals(168575096286051L, dag03.getMaxJoltage2());
    }
}
