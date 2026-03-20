package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Dag03Test {

    @Test
    void berekenMaxJoltage_tweeHoogsteAlsGetalInVolgorde_oplopend() {
        Assertions.assertEquals(46L, Dag03.berekenMaxJoltage("124116", 2));
    }

    @Test
    void berekenMaxJoltage_exactTweeCijfers() {
        Assertions.assertEquals(47L, Dag03.berekenMaxJoltage("47", 2));
    }

    @Test
    void berekenMaxJoltage_behoudVolgorde() {
        // hoogste cijfers: 9 en 8; 9 komt voor 8, dus resultaat 98
        Assertions.assertEquals(98L, Dag03.berekenMaxJoltage("984", 2));
    }

    @Test
    void berekenMaxJoltage_gelijkeMaxima() {
        Assertions.assertEquals(99L, Dag03.berekenMaxJoltage("9991", 2));
        Assertions.assertEquals(99L, Dag03.berekenMaxJoltage("1999", 2));
    }

    @Test
    void verwerkRegel_reeksVan12Cijfers_wordtAlsGetalOpgeteld() {
        Dag03 dag03 = new Dag03();

        dag03.verwerkRegel("000000000099");

        Assertions.assertEquals(99L, dag03.getTotaalJoltage());
    }

    @Test
    void verwerkRegel_berekenMeervoudAanBatterijen() {
        Dag03 dag03 = new Dag03();

        // Twee reeksen (regels) verwerken: de totale joltage moet optellen i.p.v. overschrijven.
        dag03.verwerkRegel("000000000099"); // 99
        dag03.verwerkRegel("000000000023"); // 23

        Assertions.assertEquals(122L, dag03.getTotaalJoltage());
    }

    @Test
    void verwerkRegel_voorbeeld_uit_opgave_totaleJoltage_met12BatterijenPerReeks() {
        Dag03 dag03 = new Dag03();

        dag03.verwerkRegel("987654321111111");
        dag03.verwerkRegel("811111111111119");
        dag03.verwerkRegel("234234234234278");
        dag03.verwerkRegel("818181911112111");

        Assertions.assertEquals(3121910778619L, dag03.getTotaalJoltage());
    }
}
