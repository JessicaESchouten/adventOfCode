package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DayTest {

    @Test
    void verwerkRegel_null_doetNiets() {
        DummyDag dag = new DummyDag();

        dag.verwerkRegel(null);

        Assertions.assertTrue(dag.patronen.isEmpty());
    }

    @Test
    void verwerkRegel_trimt_en_levert_1_token_aan_verwerkPatroon() {
        DummyDag dag = new DummyDag();

        dag.verwerkRegel("  abc  ");

        Assertions.assertEquals(List.of("abc"), dag.patronen);
    }

    @Test
    void verwerkRegel_lege_of_whitespace_regel_wordt_genegeerd() {
        DummyDag dag = new DummyDag();

        dag.verwerkRegel("");
        dag.verwerkRegel("   \t  ");

        Assertions.assertTrue(dag.patronen.isEmpty());
    }

    @Test
    void verwerkRegel_gebruikt_splitsRegel_en_slaat_lege_delen_over() {
        DummyDag dag = new DummyDag() {
            @Override
            protected String[] splitsRegel(String regel) {
                return new String[] { " a ", "   ", "", "\tb\t" };
            }
        };

        dag.verwerkRegel("irrelevant");

        Assertions.assertEquals(List.of("a", "b"), dag.patronen);
    }

    @Test
    void verwerkRegel_default_splitsRegel_splitst_niet() {
        DummyDag dag = new DummyDag();

        dag.verwerkRegel("a,b,c");

        Assertions.assertEquals(List.of("a,b,c"), dag.patronen);
    }

    private static class DummyDag extends Day {
        final List<String> patronen = new ArrayList<>();

        @Override
        protected void verwerkPatroon(String patroon) {
            patronen.add(patroon);
        }
    }
}
