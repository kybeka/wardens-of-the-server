package model;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class RailwayTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RailwayTest
{
    @Test
    public void testIsStationFalse() {
        Railway rail = new Railway();
        assertFalse(rail.isStation());
    }
    
    @Test
    public void testIsStationTrue() {
        Railway rail = new Railway();
        rail.createStation();
        assertTrue(rail.isStation());
    }
    
    @Test
    public void testGetXCoord() {
        //NEED TEST MAP
    }
    
    @Test
    public void testGetyCoord() {
        //NEED TEST MAP
    }
    
    @Test
    public void testGetCharacter() {
        Railway rail = new Railway();
        assertEquals('R', rail.getCharacter());
    }
    
    @Test
    public void testGetCharacterStation() {
        Railway rail = new Railway();
        rail.createStation();
        assertEquals('S', rail.getCharacter());
    }
}
