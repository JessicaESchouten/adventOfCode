package com.adventofcode.aoc2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

class Dag01Test {

    @TempDir
    Path pad;

    @Test
    void draaiNaarRechts_berekent_pijl() {
        Dag01 dag01 = new Dag01();

        dag01.draaiNaarRechts(50, 21);

        Assertions.assertEquals(71, dag01.pijl);
    }

    @Test
    void draaiNaarLinks_berekent_pijl() {
        Dag01 dag01 = new Dag01();

        dag01.draaiNaarLinks(50, 21);

        Assertions.assertEquals(29, dag01.pijl);
    }

    @Test
    void draaiNaarLinks_L100_vanaf_0_eindigt_op_0_en_telt_eindstand() {
        Dag01 dag01 = new Dag01();

        dag01.draaiNaarLinks(0, 100);

        Assertions.assertEquals(0, dag01.pijl);
        Assertions.assertEquals(1, dag01.aantalEindstandNullen);
    }

    @Test
    void verwerkRegel_onbekendeRichting_geeftException() {
        Dag01 dag01 = new Dag01();

        Assertions.assertThrows(IllegalArgumentException.class, () -> dag01.verwerkRegel("X10"));
    }

    @Test
    void verwerkBestand_verwerkt_regels_en_telt_totaalNullen() throws IOException {
        Dag01 dag01 = new Dag01();
        Path input = pad.resolve("dummybron.txt");

        Files.writeString(input, """
            R50
            R100
            R150
            """);

        dag01.verwerkBestand(input);

        Assertions.assertEquals(50, dag01.pijl);
        Assertions.assertEquals(2, dag01.aantalEindstandNullen);
        Assertions.assertEquals(3, dag01.totaalAantalNullen);
    }

    @Test
    void verwerkBestand_hele_bron_telt_eindstandNullen() throws IOException {
        Dag01 dag01 = new Dag01();

        Path tmp = pad.resolve("dag01.txt");
        try (InputStream in = Dag01.class.getResourceAsStream("/aoc2025/dag01.txt")) {
            Assertions.assertNotNull(in, "Resource not found: /aoc2025/dag01.txt");
            Files.copy(in, tmp, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        dag01.verwerkBestand(tmp);

        Assertions.assertEquals(1097, dag01.aantalEindstandNullen);
    }
}