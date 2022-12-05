package model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class MapTest.
 *
 * @author  Arseni Loika, Delara Lomen
 * @version 20.05.2022
 */
public class MapTest
{
    
    @Test
    public void testConstructor() {
    }
    
    
    @Test
    public void testPlayerRanOver() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnTrain();
        map.spawnPlayer();
        assertFalse(map.playerRanOver());
    }
    
    @Test
    public void testSpawnTrain() {
        
    }
    
    @Test
    public void testGetNextTile() {
        
    }
    
    @Test
    public void testInteractingTile() {
        
    }
    
}
