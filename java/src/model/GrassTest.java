package model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class GrassTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GrassTest
{
    
    private int testX = 3;
    private int testY = 3;
    Grass grass = new Grass(testY, testX);
    
    @Test
    public void testGetXCoord() {
        assertEquals(grass.getXCoord(), 3);
    }
    
    @Test
    public void testGetYCoord() {
        assertEquals(grass.getYCoord(), 3);
    }
    
    @Test
    public void testAddTree() {
        grass.addTree();
        assertTrue(grass.getTree() != null);
    }
    
    @Test
    public void testHasTree() {
        grass.addTree();
        assertTrue(grass.hasTree());
    }
    
    @Test
    public void testGetTree() {
        grass.addTree();
        assertTrue(grass.getTree() instanceof Tree);
    }
    
    @Test
    public void testAddMine() {
        grass.addMine();
        assertTrue(grass.getMine() != null);
    }
    
    @Test
    public void testHasMine() {
        grass.addMine();
        assertTrue(grass.hasMine());
    }
    
    @Test
    public void testGetMine() {
        grass.addMine();
        assertTrue(grass.getMine() instanceof Mine);
    }
    
    @Test
    public void testHasLake() {
        grass.addLake();
        assertTrue(grass.hasLake());
    }
    
    @Test
    public void testAddBridge() {
        grass.addLake();
        grass.addBridge();
        assertTrue(grass.hasBridge());
    }
    
    @Test
    public void testAddBridge1() {
        grass.addBridge();
        assertFalse(grass.hasBridge());
    }    
    
    @Test
    public void testAddRail() {
        grass.addRail();
        assertTrue(grass.getRail() != null);
    }
    
    @Test
    public void testHasRail1() {
        grass.addRail();
        assertTrue(grass.hasRail());
    }
    
    @Test
    public void testHasRail2() {
        grass.addLake();
        grass.addRail();
        assertFalse(grass.hasRail());
    }
    
    @Test
    public void testGetRail() {
        grass.addRail();
        assertTrue(grass.getRail() instanceof Railway);
    }
    
    @Test
    public void testAddTrain1() {
        grass.addRail();
        grass.addTrain();
        assertTrue(grass.hasTrain());
    }
    
    @Test
    public void testAddTrain2() {
        grass.addTrain();
        assertFalse(grass.hasTrain());
    }
    
    @Test
    public void testAddPlayer() {
        Player player = new Player();
        grass.addPlayer(player);
        assertTrue(grass.hasPlayer());
    }
    
    @Test
    public void testHasPlayer() {
        Player player = new Player();
        grass.addLake();
        grass.addPlayer(player);
        assertFalse(grass.hasPlayer());
    }
    
    @Test
    public void testIsEmpty1() {
        Grass grass = new Grass(testX, testY);
        assertTrue(grass.isEmpty());
    }
    
    @Test
    public void testIsEmpty2() {
        Grass grass = new Grass(testX, testY);
        grass.addRail();
        assertFalse(grass.isEmpty());
    }
    
    @Test
    public void testIsEmpty3() {
        Grass grass = new Grass(testX, testY);
        grass.addTree();
        assertFalse(grass.isEmpty());
    }
    
    @Test
    public void testIsEmpty4() {
        Grass grass = new Grass(testX, testY);
        grass.addMine();
        assertFalse(grass.isEmpty());
    }
    
    @Test
    public void testGetCharacter1() {
        Grass grass = new Grass(testX, testY);
        assertEquals('G', grass.getCharacter());
    }
    
    @Test
    public void testGetCharacter2() {
        Grass grass = new Grass(testX, testY);
        grass.addTree();
        assertEquals('T', grass.getCharacter());
    }
    
    @Test
    public void testGetChracter3() {
        Grass grass = new Grass(testX, testY);
        grass.addTrain();
        assertEquals('G', grass.getCharacter());
    }
    
    @Test
    public void testGetCharacter5() {
        Grass grass = new Grass(testX, testY);
        grass.addRail();
        assertEquals('R', grass.getCharacter());
    }
    
    @Test
    public void testGetCharacter6() {
        Grass grass = new Grass(testX, testY);
        grass.addLake();
        grass.addBridge();
        assertEquals('B', grass.getCharacter());
    }
    
    @Test
    public void testGetCharacter7() {
        Grass grass = new Grass(testX, testY);
        grass.addLake();
        assertEquals('L', grass.getCharacter());
    }
    
    @Test
    public void testGetChracter4() {
        Grass grass = new Grass(testX, testY);
        grass.addRail();
        grass.addTrain();
        assertEquals('t', grass.getCharacter());
    }
    
    @Test
    public void testGetCharacter8() {
        Grass grass = new Grass(testX, testY);
        grass.addMine();
        assertEquals('M', grass.getCharacter());
    }
    
    @Test
    public void testGetCharacter9() {
        Player player = new Player();
        Grass grass = new Grass(testX, testY);
        grass.addPlayer(player);
        assertEquals('P', grass.getCharacter());
    }
    
    @Test
    public void testGetCharacter10() {
        Grass grass = new Grass(testX, testY);
        grass.addLake();
        grass.addBridge();
        grass.addRail();
        assertEquals('R', grass.getCharacter());
    }
    
    @Test
    public void testRemoveMine() {
        Grass grass = new Grass(testX, testY);
        grass.addMine();
        assertFalse(grass.getMine().equals(null));
    }
    
    @Test
    public void testPlayerRanOver2() {
        Player player = new Player();
        Grass grass = new Grass(testX, testY);
        grass.addPlayer(player);
        assertFalse(grass.playerRanOver());
    }
    
     @Test
    public void testPlayerRanOver3() {
        Grass grass = new Grass(testX, testY);
        grass.addTrain();
        assertFalse(grass.playerRanOver());
    }
    
    @Test
    public void testTrainReachedStation() {
        Grass grass = new Grass(testX, testY);
        grass.addRail();
        grass.getRail().createStation();
        grass.addTrain();
        assertTrue(grass.trainReachedStation());
    }
    
    @Test
    public void testRemoveTree() {
        Grass grass = new Grass(testX, testY);
        grass.addTree();
        assertFalse(grass.getTree().equals(null));
    }
}
