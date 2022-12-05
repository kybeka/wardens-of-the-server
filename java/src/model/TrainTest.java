package model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class TrainTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TrainTest
{   
    @Test
    public void testMakeX() {
        Train train = new Train();
        train.makeX(3);
        assertEquals(3, train.getXCoord());
    }
    
    @Test
    public void testMakeY() {
        Train train = new Train();
        train.makeY(3);
        assertEquals(3, train.getYCoord());
    }
    
    @Test
    public void testDerail() {
        Train train = new Train();
        train.derail();
        assertTrue(train.derailedStatus());
    }
    
    @Test
    public void testDerailStatus() {
        Train train = new Train();
        assertFalse(train.derailedStatus());
    }
    
    @Test
    public void testGetCharacter() {
        Train train = new Train();
        assertEquals('t', train.getCharacter());
    }
}
