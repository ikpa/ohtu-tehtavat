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
public abstract class Komento {
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    private Button undo;
    protected Sovelluslogiikka logic;
    protected ArrayList<Integer> values;
    

    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka logic, ArrayList<Integer> values) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.logic = logic;
        this.values = values;
    } 
    
    public abstract void suorita();
    
    public void peru() {
        int integer = values.get(values.size() - 2);
        tuloskentta.setText(Integer.toString(integer));
        logic.setTulos(integer);
        syotekentta.clear();
        tarkistaNollaus();
    }
    
    public void tarkistaNollaus() {
        if ( logic.tulos()==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }
}
