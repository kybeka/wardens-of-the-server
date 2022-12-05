package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlaceBridgeCommandTest {
    Player player = new Player();
    Map map = new Map(player);
    PlaceBridgeCommand pbc = new PlaceBridgeCommand();
    PlayerMove playerMove = new PlayerMove();
 
    @Test
    public void testPlaceBridge1() {
        map.spawnPlayer();
        playerMove.move(1, map);
        playerMove.move(3, map);
        pbc.execute(map);
        assertFalse(map.getGrid().getTile(4, 3).hasBridge());
    }
}