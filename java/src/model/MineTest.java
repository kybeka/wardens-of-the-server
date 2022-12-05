package model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class MineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MineTest
{
    @Test
    public void testGetCharacter() {
        Mine mine = new Mine();
        assertEquals('M', mine.getCharacter());
    }
    
    @Test
    public void testResourceCount() {
        Mine mine = new Mine();
        assertEquals(4, mine.getResourceCount());
    }
    
}
