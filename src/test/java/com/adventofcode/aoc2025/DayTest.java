package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DayTest {

    @Test
    void framework_processLine_null_doesNothing() {
        DummyDay day = new DummyDay();

        day.processLine(null);

        Assertions.assertTrue(day.tokens.isEmpty());
    }

    @Test
    void framework_processLine_trims_andPassesOneToken_toProcessToken() {
        DummyDay day = new DummyDay();

        day.processLine("  abc  ");

        Assertions.assertEquals(List.of("abc"), day.tokens);
    }

    @Test
    void framework_processLine_emptyOrWhitespace_isIgnored() {
        DummyDay day = new DummyDay();

        day.processLine("");
        day.processLine("   \t  ");

        Assertions.assertTrue(day.tokens.isEmpty());
    }

    @Test
    void framework_processLine_usesSplitLine_andSkipsEmptyParts() {
        DummyDay day = new DummyDay() {
            @Override
            protected String[] splitLine(String line) {
                return new String[] { " a ", "   ", "", "\tb\t" };
            }
        };

        day.processLine("irrelevant");

        Assertions.assertEquals(List.of("a", "b"), day.tokens);
    }

    @Test
    void framework_processLine_defaultSplitLine_doesNotSplit() {
        DummyDay day = new DummyDay();

        day.processLine("a,b,c");

        Assertions.assertEquals(List.of("a,b,c"), day.tokens);
    }

    private static class DummyDay extends Day {
        final List<String> tokens = new ArrayList<>();

        @Override
        protected void processToken(String token) {
            tokens.add(token);
        }
    }
}

