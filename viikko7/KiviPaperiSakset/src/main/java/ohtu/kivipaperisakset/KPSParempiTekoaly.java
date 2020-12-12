package ohtu.kivipaperisakset;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KiviPaperiSakset {
    private TekoalyParannettu ai;
    
    public KPSParempiTekoaly() {
        super();
        ai = new TekoalyParannettu(20);
    }

    @Override
    protected String toisenSiirto(String ekanSiirto) {
        ai.asetaSiirto(ekanSiirto);
        String siirto = ai.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }

    
}
