package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviPaperiSakset {
    private TekoalyHelppo ai;
    
    public KPSTekoaly() {
        super();
        ai = new TekoalyHelppo();
    }

    @Override
    protected String toisenSiirto(String ekanSiirto) {
        String siirto = ai.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }

    
}