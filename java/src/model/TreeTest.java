package model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class TreeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TreeTest
{
    @Test
    public void testGetCharacter() {
        Tree tree = new Tree();
        assertEquals('T', tree.getCharacter());
    }
    
    @Test
    public void testResourceCount() {
        Tree tree = new Tree();
        assertEquals(5, tree.getResourceCount());
    }

}
