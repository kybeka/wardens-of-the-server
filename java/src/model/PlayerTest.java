package model;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest
{
    
    @Test
    public void testInitialWoodCount() {
        Player player = new Player();
        assertEquals(0, player.woodCount());
    }
    
    @Test
    public void testInitialIronCount() {
        Player player = new Player();
        assertEquals(0, player.ironCount());
    }
    
    @Test
    public void testMakeX() {
        Player player = new Player();
        player.makeX(3);
        assertEquals(3, player.getXCoord());
    }
    
    @Test
    public void testMakeY() {
        Player player = new Player();
        player.makeY(3);
        assertEquals(3, player.getYCoord());
    }
    
    @Test
    public void testCanBuildRail() {
        Map map = new Map();
        map.spawnPlayer();
        assertFalse(map.getPlayer().canBuildRail(map));
    }
    
    @Test
    public void testCanBuildRail2() {
        DigCommand dig = new DigCommand();
        PlayerMove pm = new PlayerMove();
        Player player = new Player();
        Map map = new Map(player);
        map.spawnPlayer();
        pm.move(0, map);
        pm.move(2, map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        
        pm.move(0, map);
        pm.move(0, map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        assertTrue(map.getPlayer().canBuildRail(map));
    }
    
    @Test
    public void testCanBuildBridge() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnPlayer();
        assertFalse(map.getPlayer().canBuildBridge(map));
    }
    
    @Test
    public void testCanBuildBridge2() {
        DigCommand dig = new DigCommand();
        PlayerMove pm = new PlayerMove();
        Player player = new Player();
        Map map = new Map(player);
        map.spawnPlayer();
        pm.move(0, map);
        pm.move(2, map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        
        pm.move(0, map);
        pm.move(0, map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        dig.execute(map);
        assertTrue(map.getPlayer().canBuildBridge(map));
    }
    
    @Test
    public void testGetCharacter() {
        Player player = new Player();
        assertEquals('P', player.getCharacter());
    }
}
