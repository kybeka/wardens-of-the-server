package model;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class TestMapTest.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 27.05.2022
 */
public class TestMapTest
{
    Player player = new Player();
    Map map = new Map(player);
    
    @Test
    public void testGetter1() {
        assertTrue(map.getGrid().getTile(1, 2).hasTree());
    }
    
    @Test
    public void testGetter2() {
        assertTrue(map.getGrid().getTile(2, 2).hasTree());
    }
    
    @Test
    public void testGetter3() {
        assertTrue(map.getGrid().getTile(3, 2).hasTree());
    }
    
    @Test
    public void testGetter4() {
        assertTrue(map.getGrid().getTile(4, 2).hasTree());
    }
    
    @Test
    public void testGetter5() {
        assertTrue(map.getGrid().getTile(5, 2).hasTree());
    }
    
    @Test
    public void testGetter6() {
        assertTrue(map.getGrid().getTile(1, 6).hasMine());
    }
    
    @Test
    public void testGetter7() {
        assertTrue(map.getGrid().getTile(2, 6).hasMine());
    }
    
    @Test
    public void testGetter8() {
        assertTrue(map.getGrid().getTile(3, 6).hasMine());
    }
    
    @Test
    public void testGetter9() {
        assertTrue(map.getGrid().getTile(4, 6).hasMine());
    }
    
    @Test
    public void testGetter10() {
        assertTrue(map.getGrid().getTile(5, 6).hasMine());
    }
    
    @Test
    public void testGetter11() {
        assertTrue(map.getGrid().getTile(4, 3).hasLake());
    }
    
    @Test
    public void testGetterRails() {
        assertTrue(map.getGrid().getTile(0, 4).hasRail());
        assertTrue(map.getGrid().getTile(1, 4).hasRail());
        assertTrue(map.getGrid().getTile(2, 4).hasRail());
        assertTrue(map.getGrid().getTile(4, 4).hasRail());
        assertTrue(map.getGrid().getTile(5, 4).hasRail());
        assertTrue(map.getGrid().getTile(7, 4).hasRail());
        assertTrue(map.getGrid().getTile(8, 4).hasRail());
        assertTrue(map.getGrid().getTile(9, 4).hasRail());
    }
    
    @Test
    public void testSpawnPlayer() {
        map.spawnPlayer();
        assertTrue(map.getGrid().getTile(3, 3). hasPlayer());
    }
}
