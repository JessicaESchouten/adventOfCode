package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class Dag02Test {

    @Test
    void verwerkBestand_enTestAntwoorden() throws IOException {

        Dag02 dag02 = new Dag02();
        Path input = Path.of("./src/main/resources/aoc2025/dag02.txt");

        adventOfCodeApplication.verwerkBestand(input, dag02::verwerkRegel);

        Assertions.assertEquals(12586854255L, dag02.getAnswerPart1());
        Assertions.assertEquals(17298174201L, dag02.getAnswerPart2());
    }
}