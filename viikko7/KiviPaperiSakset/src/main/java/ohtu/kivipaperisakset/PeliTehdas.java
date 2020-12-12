/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.*;
/**
 *
 * @author ikpa
 */
public class PeliTehdas {
    private HashMap<String, KiviPaperiSakset> pelit;
    
    public PeliTehdas() {
        pelit = new HashMap<>();
        pelit.put("helppo", new KPSTekoaly());
        pelit.put("parempi", new KPSParempiTekoaly());
        pelit.put("pelaaja", new KPSPelaajaVsPelaaja());
    }
    
    public KiviPaperiSakset hae(String nimi) {
        return pelit.get(nimi);
    }
}
