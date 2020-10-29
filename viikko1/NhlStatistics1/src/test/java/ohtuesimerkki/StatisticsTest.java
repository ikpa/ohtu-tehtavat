/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 *
 * @author ikpa
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void searchToimiiKunOikeaNimi() {
        Player test = stats.search("Kurri");
        assertEquals(test.getGoals(), 37);
    }
    
    @Test
    public void searchToimiiKunVaaraNimi() {
        Player test = stats.search("väärä");
        assertNull(test);
    }
    
    @Test
    public void teamToimii() {
        List<Player> players = stats.team("EDM");
        assertEquals(players.get(0).getGoals(), 4);
        assertEquals(players.get(1).getGoals(), 37);
        assertEquals(players.get(2).getGoals(), 35);
    }
    
    @Test
    public void topScorersToimii() {
        List<Player> players = stats.topScorers(1);
        assertEquals(players.get(0).getGoals(), 35);
    }
}
