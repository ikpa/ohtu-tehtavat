package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PeliTehdas pelit = new PeliTehdas();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                pelaa("pelaaja");
            } else if (vastaus.endsWith("b")) {
                pelaa("helppo");
            } else if (vastaus.endsWith("c")) {
                pelaa("parempi");
            } else {
                break;
            }

        }

    }
    
    public static void pelaa(String peli) {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        pelit.hae(peli).pelaa();
    }
}
