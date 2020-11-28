/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.*;

/**
 *
 * @author ikpa
 */
public class Nollaa extends Komento {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka logic, ArrayList<Integer> values) {
        super(tuloskentta, syotekentta, nollaa, undo, logic, values);
    }
    
    
    
    @Override
    public void suorita() {
        super.logic.nollaa();
        super.tuloskentta.setText(Integer.toString(super.logic.tulos()));
        super.syotekentta.clear();
        super.tarkistaNollaus();
        values.add(logic.tulos());
    }
    
}
