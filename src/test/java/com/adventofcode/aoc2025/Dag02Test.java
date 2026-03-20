package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Dag02Test {

    private static final String VOORBEELD = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862";

    @Test
    void voorbeeld_uit_uitleg_som_van_ongeldige_ids_is_1227775554() {
        Dag02 dag02 = new Dag02();

        dag02.verwerkRegel(VOORBEELD);

        Assertions.assertEquals(1227775554L, dag02.berekenAntwoordEersteDeel());
    }

    @Test
    void verwerkRegel_tweede_deel_telt_ook_herhaling_met_meer_dan_twee_blokken() {
        Dag02 dag02 = new Dag02();

        // 121212 = "12" 3x => wel deel 2, niet deel 1
        dag02.verwerkRegel("121212-121212");

        Assertions.assertEquals(0L, dag02.berekenAntwoordEersteDeel());
        Assertions.assertEquals(121212L, dag02.berekenAntwoordTweedeDeel());
    }
}
