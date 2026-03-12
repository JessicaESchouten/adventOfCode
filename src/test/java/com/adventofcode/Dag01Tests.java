package com.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class Dag01Tests {

    @TempDir
    Path pad;

    @Test
    void berekenRCijfer_zonderWrap() {
        Dag01 dag01 = new Dag01();

        dag01.draaiNaarRechts(50, 21);

        Assertions.assertEquals(71, dag01.pijl);
    }

    @Test
    void berekenRCijfer_metWrap() {
        Dag01 dag01 = new Dag01();

        dag01.draaiNaarRechts(50, 55);

        Assertions.assertEquals(5, dag01.pijl);
    }

    @Test
    void berekenLCijfer_zonderWrap() {
        Dag01 dag01 = new Dag01();

        dag01.draaiNaarLinks(50, 21);

        Assertions.assertEquals(29, dag01.pijl);
    }

    @Test
    void berekenLCijfer_metWrap() {
        Dag01 dag01 = new Dag01();

        dag01.draaiNaarLinks(50, 55);

        Assertions.assertEquals(95, dag01.pijl);
    }

    @Test
    void berekenLCijfer_L100_vanaf_0_blijft_0_en_telt_nul() {
        Dag01 dag01 = new Dag01();

        dag01.draaiNaarLinks(0, 100);

        Assertions.assertEquals(0, dag01.pijl);
        Assertions.assertEquals(1, dag01.aantalNullen);
    }

    @Test
    void verwerkRegel_onbekendeRichting_geeftException() {
        Dag01 dag01 = new Dag01();

        Assertions.assertThrows(IllegalArgumentException.class, () -> dag01.verwerkRegel("X10"));
    }

    @Test
    void telAantalKerenTellerOpNul_verwerkt_bestand() throws IOException {
        Dag01 dag01 = new Dag01();
        Path pad = maakInputBestand("""
                R10
                L5
                R55
                """);

        int resultaat = dag01.telAantalNullen(pad);

        Assertions.assertEquals(10, resultaat);
        Assertions.assertEquals(10, dag01.pijl);
    }

    @Test
    void telAantalKerenTellerOpNul_telt_nullen_in_veldinplaats_via_return() throws IOException {
        Dag01 dag01 = new Dag01();
        Path pad = maakInputBestand("""
            R50
            R100
            """);

        int eindstand = dag01.telAantalNullen(pad);

        Assertions.assertEquals(0, eindstand);
        Assertions.assertEquals(2, dag01.aantalNullen);
    }

    @Test
    void telAantalKerenTellerOpNul_telt_nullen() throws IOException {
        Dag01 dag01 = new Dag01();
        Path pad = maakInputBestand("""
                R50
                R100
                """);

        dag01.telAantalNullen(pad);

        Assertions.assertEquals(0, dag01.pijl);
        Assertions.assertEquals(2, dag01.aantalNullen);
    }

    @Test
    void telAantalKerenTellerOpNul_hele_bron_nullentellen() throws IOException {
        Dag01 dag01 = new Dag01();

        Path pad = this.pad.resolve("dag01.txt");
        try (InputStream invoerstroom = Dag01.class.getResourceAsStream("/input2025/dag01.txt")) {
            Assertions.assertNotNull(invoerstroom, "Resource not found: /input2025/dag01.txt");
            Files.copy(invoerstroom, pad, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        dag01.telAantalNullen(pad);

        Assertions.assertEquals(1097, dag01.aantalNullen);
    }

    private Path maakInputBestand(String content) throws IOException {
        Path pad = this.pad.resolve("bron.txt");
        Files.writeString(pad, content, StandardCharsets.UTF_8);
        return pad;
    }
}
