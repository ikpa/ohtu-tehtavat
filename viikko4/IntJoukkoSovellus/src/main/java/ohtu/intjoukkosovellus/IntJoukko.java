
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    
    
    public IntJoukko() {
        taulukko = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Ei-validi kapasiteetti");
        }
        taulukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Ei-validi kapasiteetti");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Ei-validi kasvatuskoko");//heitin vaan jotain :D
        }
        taulukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        
        if (!kuuluu(luku)) {
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            
            if (alkioidenLkm == taulukko.length) {
                kasvata();
            }
            return true;
        }
        
        return false;
    }
    
    private void kasvata() {
        int[] taulukkoOld = taulukko;
        taulukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, taulukko);
    }

    public boolean kuuluu(int luku) {
        for (int i : taulukko) {
            if (i == luku) {
                return true;
            }
        }
        
        return false;
    }
    
    private int luvunIndeksi(int luku) {
        int index = 0;
        for (int i : taulukko) {
            if (i == luku) {
                return index;
            }
            
            index++;
        }
        
        return -1;
    }

    public boolean poista(int luku) {
        int kohta = luvunIndeksi(luku);
        
        if (kohta != -1) {
            taulukko[kohta] = 0;
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                taulukko[j] = taulukko[j + 1];
            }
            alkioidenLkm--;
            return true;
        }


        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int alkioidenLkm() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String result = "{";
        
        for (int i : taulukko) {
            if (i == 0) break;
            
            if (!result.equals("{")) {
                result += ", ";
            }
            
            result += i;
        }
        
        result += "}";
        
        return result;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }
    
    private void lisaaJoukko(IntJoukko joukko) {
        int[] taulu = joukko.toIntArray();
        for (int i : taulu) {
            lisaa(i);
        }
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        joukko.lisaaJoukko(a);
        joukko.lisaaJoukko(b);
        return joukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i : aTaulu) {
            if (b.kuuluu(i)) {
                joukko.lisaa(i);
            }
        }
        return joukko;

    }
    
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i : aTaulu) {
            if (b.kuuluu(i)) continue;
            
            joukko.lisaa(i);
        }
        
 
        return joukko;
    }
        
}
