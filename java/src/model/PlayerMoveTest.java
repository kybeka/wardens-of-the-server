package model;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class PlayerMoveTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerMoveTest
{
    PlayerMove playerMoveTest = new PlayerMove();    
    @Test
    public void testMoveUp() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnPlayer();
        
        playerMoveTest.move(1, map);
        assertEquals(map.getPlayer().getXCoord(), 3);
        assertEquals(map.getPlayer().getYCoord(), 2);
        
        
        playerMoveTest.move(0, map);
        playerMoveTest.move(1, map);
        assertEquals(map.getPlayer().getXCoord(), 4);
        assertEquals(map.getPlayer().getYCoord(), 1);
        
        playerMoveTest.move(1, map);
        playerMoveTest.move(1, map);
        playerMoveTest.move(1, map);
        assertEquals(map.getPlayer().getXCoord(), 4);
        assertEquals(map.getPlayer().getYCoord(), 0);
    }
    
    @Test
    public void testMoveRight() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnPlayer();
        
        playerMoveTest.move(0, map);
        assertEquals(map.getPlayer().getXCoord(), 4);
        assertEquals(map.getPlayer().getYCoord(), 3);
        assertEquals(playerMoveTest.getStepCounter(), 1);
    }
    
    @Test
    public void testMoveLeft() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnPlayer();
        
        playerMoveTest.move(2, map);
        assertEquals(map.getPlayer().getXCoord(), 3);
        assertEquals(map.getPlayer().getYCoord(), 3);
        
        playerMoveTest.move(0, map);
        playerMoveTest.move(0, map);
        playerMoveTest.move(2, map);
        assertEquals(map.getPlayer().getXCoord(), 4);
        assertEquals(map.getPlayer().getYCoord(), 3);
    }
    
    @Test
    public void testMoveDown() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnPlayer();
        
        playerMoveTest.move(3, map);
        assertEquals(map.getPlayer().getXCoord(), 3);
        assertEquals(map.getPlayer().getYCoord(), 3);
    }
}
