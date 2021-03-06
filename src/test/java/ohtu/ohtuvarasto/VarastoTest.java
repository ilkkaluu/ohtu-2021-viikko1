package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tyhjaVarasto() {
        Varasto v = new Varasto(0.0);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void tyhjaVarasto2() {
        Varasto v = new Varasto(0.0, 0);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoonKelpaamatonSaldo() {
        Varasto v = new Varasto(5.0, -1.0);

        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonYlivuotoOk() {
        Varasto v = new Varasto(10.0, 11.0);

        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysToimii() {
        varasto.lisaaVarastoon(10);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastossaLiikaaSaldoa() {
        varasto.lisaaVarastoon(15);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaOttoOk() {
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(5);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVirheellisiaLukujaVarastoon() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVirheellisiaLukujaVarastosta() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(-1);

        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tyhjennysToimii() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(1);

        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
}