/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.*;
/**
 *
 * @author ikpa
 */
public class Or implements Matcher {
    private Matcher matcher1;
    private Matcher matcher2;
    
    public Or(Matcher matcher1, Matcher matcher2) {
        this.matcher1 = matcher1;
        this.matcher2 = matcher2;
    }
    
    @Override
    public boolean matches(Player p) {
        boolean m1 = matcher1.matches(p);
        boolean m2 = matcher2.matches(p);
        boolean value = m1 || m2;
        
        return value;
    }
}
