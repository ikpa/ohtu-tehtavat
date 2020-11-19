package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

public class KauppaTest {
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).
                thenReturn(42).
                thenReturn(43);
        
        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juusto", 3));
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "leipä", 4));
        
        k = new Kauppa(varasto, pankki, viite); 
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {             

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);   
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"),eq(5));   
    }
    
    @Test
    public void ostoksetOnnistuvatKunKaksiEriTuotettaOstetaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pertti", "54321");
        
        verify(pankki).tilisiirto(eq("pertti"), anyInt(), eq("54321"), eq("33333-44455"),eq(8));
    }
    
    @Test
    public void ostoksetOnnistuvatKunKaksiSamaaTuotettaOstetaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("pertti", "54321");
        
        verify(pankki).tilisiirto(eq("pertti"), anyInt(), eq("54321"), eq("33333-44455"),eq(6));
    }
    
    @Test
    public void ostoksetOnnistuvatKunVarastossaEiSaldoaTuotettaOstetaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("pertti", "54321");
        
        verify(pankki).tilisiirto(eq("pertti"), anyInt(), eq("54321"), eq("33333-44455"),eq(5));
    }
    
    @Test
    public void aloitaAsiointiNollaaHinnan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pertti", "54321");
        
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(3));
    }
    
    @Test
    public void jokaTapahtumallaUusiViite() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(anyString(), eq(42), anyString(), anyString(), anyInt());
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pertti", "54321");
        
        verify(pankki).tilisiirto(anyString(), not(eq(42)), anyString(), anyString(), anyInt());
    }
    
    @Test
    public void poistaKoristaKutsuuOikeanMetodin() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        verify(varasto, times(1)).palautaVarastoon(any(Tuote.class));
    }
}
