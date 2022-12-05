package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class DigCommandTest {
    PlayerMove playerMover = new PlayerMove();
    DigCommand dig = new DigCommand();
    Player player = new Player();
    Map map = new Map(player);
    
    @Test
    public void testDig1() {
        map.spawnPlayer();
        playerMover.move(0, map);
        playerMover.move(0, map);
        playerMover.move(0, map);
        
        dig.execute(map);
        assertEquals(1, player.ironCount());
        assertEquals(0, player.woodCount());
    }
    
    @Test
    public void testDig2() {
        map.spawnPlayer();
        playerMover.move(2, map);
        dig.execute(map);
        assertEquals(1, player.woodCount());
        assertEquals(0, player.ironCount());
    }
    
    @Test
    public void testDig3() {
        map.spawnPlayer();
        dig.execute(map);
        assertEquals(0, player.woodCount());
        assertEquals(0, player.ironCount());
    }
    
    @Test
    public void testDig4() {
        map.spawnPlayer();
        playerMover.move(2, map);
        playerMover.move(1, map);
        dig.execute(map);
        assertEquals(0, player.woodCount());
        assertEquals(0, player.ironCount());
    }
    
    @Test
    public void testDig5() {
        map.spawnPlayer();
        playerMover.move(0, map);
        playerMover.move(0, map);
        playerMover.move(0, map);
        dig.execute(map);
        dig.execute(map);
        assertEquals(0, player.woodCount());
        assertEquals(2, player.ironCount());
    }
    
    @Test
    public void testDig6() {
        map.spawnPlayer();
        playerMover.move(2, map);
        playerMover.move(2, map);
        dig.execute(map);
        dig.execute(map);
        assertEquals(2, player.woodCount());
        assertEquals(0, player.ironCount());
    }
}