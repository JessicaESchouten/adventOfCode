package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Dag03Test {

    @Test
    void tweeHoogsteAlsGetalInVolgorde_oplopend() {
        Assertions.assertEquals(46, Dag03.maximaleJoltageVoorBank("124116"));
    }

    @Test
    void maximaleJoltageVoorBank_exactTweeCijfers() {
        Assertions.assertEquals(47, Dag03.maximaleJoltageVoorBank("47"));
    }

    @Test
    void tweeHoogsteAlsGetalInVolgorde_behoudVolgorde() {
        // hoogste cijfers: 9 en 8; 9 komt voor 8, dus resultaat 98
        Assertions.assertEquals(98, Dag03.maximaleJoltageVoorBank("984"));
    }

    @Test
    void tweeHoogsteAlsGetalInVolgorde_gelijkeMaxima() {
        Assertions.assertEquals(99, Dag03.maximaleJoltageVoorBank("9991"));
        Assertions.assertEquals(99, Dag03.maximaleJoltageVoorBank("1999"));
    }

    @Test
    void verwerkRegel_vindtHoogsteCijfersInRechterhelft() {
        Dag03 dag03 = new Dag03();

        dag03.verwerkRegel("00100099");

        Assertions.assertEquals(99, dag03.getTotaalJoltage());
    }

    @Test
    void verwerkRegel_legeOfWhitespaceRegel_wordtGenegeerd_en_trimWordtToegepast() {
        Dag03 dag03 = new Dag03();

        dag03.verwerkRegel("   ");
        Assertions.assertEquals(0, dag03.getTotaalJoltage());

        dag03.verwerkRegel(" 00100099 ");
        Assertions.assertEquals(99, dag03.getTotaalJoltage());
    }

    @Test
    void verwerkRegel_berekenMeervoudAanBatterijen() {
        Dag03 dag03 = new Dag03();

        // Twee banken (regels) verwerken: de totale joltage moet optellen i.p.v. overschrijven.
        dag03.verwerkRegel("00100099"); // maximale joltage = 99
        dag03.verwerkRegel("123");      // maximale joltage = 23

        Assertions.assertEquals(122, dag03.getTotaalJoltage());
    }
}
