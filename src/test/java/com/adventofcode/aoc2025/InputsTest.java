package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputsTest {

    private static final String PUZZLE_EXAMPLE_INPUT = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
            """;

    @Test
    void puzzleExample_splitOnBlankLine_lf() {
        String[] sections = Inputs.splitOnBlankLine(PUZZLE_EXAMPLE_INPUT.trim());

        Assertions.assertEquals(2, sections.length);
        Assertions.assertEquals(4, sections[0].trim().split("\\R").length);
        Assertions.assertEquals(6, sections[1].trim().split("\\R").length);
        Assertions.assertEquals("3-5", sections[0].trim().split("\\R")[0]);
        Assertions.assertEquals("1", sections[1].trim().split("\\R")[0]);
    }

    @Test
    void puzzleExample_splitOnBlankLine_crlf() {
        String crlf = PUZZLE_EXAMPLE_INPUT.replace("\n", "\r\n");
        String[] sections = Inputs.splitOnBlankLine(crlf.trim());

        Assertions.assertEquals(2, sections.length);
        Assertions.assertEquals(4, sections[0].trim().split("\\R").length);
        Assertions.assertEquals(6, sections[1].trim().split("\\R").length);
        Assertions.assertEquals("3-5", sections[0].trim().split("\\R")[0]);
        Assertions.assertEquals("1", sections[1].trim().split("\\R")[0]);
    }
}

