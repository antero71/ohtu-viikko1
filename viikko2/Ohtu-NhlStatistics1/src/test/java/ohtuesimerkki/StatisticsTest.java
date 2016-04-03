/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    public StatisticsTest() {

    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void testSearch() {

        Player p = stats.search("Semenko");

        assertEquals("Semenko", p.getName());

        p = stats.search("Kurri");

        assertEquals("Kurri", p.getName());

    }

    @Test
    public void testSearchEiLoydy() {

        Player p = stats.search("Hiphurraa");

        assertNull(p);

    }

    @Test
    public void testTeam() {
        List<Player> l = stats.team("EDM");
        assertEquals(l.size(), 3);

        int i = 0;
        for (Player p : l) {
            if (i == 0) {
                assertEquals("Semenko", p.getName());
            }
            if (i == 1) {
                assertEquals("Kurri", p.getName());
            }
            if (i == 2) {
                assertEquals("Gretzky", p.getName());
            }
            i++;
            if (i == 3) {
                continue;
            }
        }

    }

    @Test
    public void testTopScorers() {

        List<Player> l = stats.topScorers(0);

        for (Player p : l) {
            assertEquals("Gretzky", p.getName());
            assertEquals(124, p.getPoints());

        }

    }

    @Test
    public void testTopScorersNegatiivinen() {

        List<Player> l = stats.topScorers(-1);

        assertEquals(0, l.size());

    }

    @Test
    public void testTopScorers2() {
        List<Player> l = stats.topScorers(0);
        l = stats.topScorers(1);
        int i = 0;
        for (Player p1 : l) {
            if (i == 1) {
                assertEquals("Lemieux", p1.getName());
                assertEquals(99, p1.getPoints());
            }
            i++;
        }
    }

}
