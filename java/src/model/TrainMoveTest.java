package model;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class TrainMoveTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TrainMoveTest
{
    TrainMove trainMoveTest = new TrainMove();

    @Test
    public void testMoveUp() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnTrain();
        
        
        trainMoveTest.move(3, map);
        trainMoveTest.move(1, map);
        assertEquals(map.getTrain().getXCoord(), 4);
        assertEquals(map.getTrain().getYCoord(), 0);
    }

    @Test
    public void testMoveRight() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnTrain();
        trainMoveTest.move(0, map);

        assertEquals(map.getTrain().getXCoord(), 5);
        assertEquals(map.getTrain().getYCoord(), 0);
    }

    @Test
    public void testMoveLeft() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnTrain();
        trainMoveTest.move(2, map);

        assertEquals(map.getTrain().getXCoord(), 3);
        assertEquals(map.getTrain().getYCoord(), 0);
    }

    @Test
    public void testMoveDown() {
        Player player = new Player();
        Map map = new Map(player);
        map.spawnTrain();
        trainMoveTest.move(3, map);

        assertEquals(map.getTrain().getXCoord(), 4);
        assertEquals(map.getTrain().getYCoord(), 1);
    }
}
