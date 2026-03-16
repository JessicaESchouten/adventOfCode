package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

class Dag02Test {

    @TempDir
    Path pad;

    @Test
    void checkPatroonOpNulAanDeStart() {
        Dag02 dag02 = new Dag02();

        Assertions.assertFalse(dag02.checkPatroonOpNulAanDeStart("012312"));
        Assertions.assertTrue(dag02.checkPatroonOpNulAanDeStart("123456"));
    }

    @Test
    void verwerkPatroon_teltAlleenOngeldigePatronenOp() {
        Dag02 dag02 = new Dag02();

        dag02.verwerkPatroon("123456"); // geldig
        dag02.verwerkPatroon("012345"); // ongeldig: start met 0 => parseLong == 12345
        dag02.verwerkPatroon("1212");   // ongeldig: "12" + "12" => 1212

        Assertions.assertEquals(12345 + 1212, dag02.getSomOngeldigeCodes());
    }

    @Test
    void verwerkPatroon_range_isInclusief_enTeltOngeldigeCodesOp() {
        Dag02 dag02 = new Dag02();

        // 10 (geldig), 11 (ongeldig: "1" + "1"), 12 (geldig)
        dag02.verwerkPatroon("10-12");

        Assertions.assertEquals(11, dag02.getSomOngeldigeCodes());
    }

    @Test
    void verwerkPatroon_range_accepteertSpatiesRondKoppelteken() {
        Dag02 dag02 = new Dag02();

        dag02.verwerkPatroon("  10 - 12  ");

        Assertions.assertEquals(11, dag02.getSomOngeldigeCodes());
    }

    @Test
    void verwerkPatroon_range_startGroterDanEinde_gooitIllegalArgumentException() {
        Dag02 dag02 = new Dag02();

        Assertions.assertThrows(IllegalArgumentException.class, () -> dag02.verwerkPatroon("5-3"));
    }

    @Test
    void verwerkPatroon_range_longMaxValue_looptNietVast() {
        Dag02 dag02 = new Dag02();
        String max = Long.toString(Long.MAX_VALUE);

        // Deze range is 1 element. Dit test dat de loop niet overflowt en "blijft lopen".
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> dag02.verwerkPatroon(max + "-" + max));
        Assertions.assertEquals(0, dag02.getSomOngeldigeCodes());
    }

    @Test
    void verwerkRegel_splitstOpKommas_enNegeertSpaties() {
        Dag02 dag02 = new Dag02();

        dag02.verwerkRegel(" 123456 , 012345,1212 ");

        Assertions.assertEquals(12345 + 1212, dag02.getSomOngeldigeCodes());
    }

    @Test
    void verwerkBestand_trim_enSlaatLegeRegelsOver() throws IOException {
        Dag02 dag02 = new Dag02();
        Path input = pad.resolve("dummybron.txt");

        Files.writeString(input, """

                123456,012345

                  1212
                """);

        dag02.verwerkBestand(input);

        Assertions.assertEquals(12345 + 1212, dag02.getSomOngeldigeCodes());
    }
}