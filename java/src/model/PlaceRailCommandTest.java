package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlaceRailCommandTest {
    Player player = new Player();
    Map map = new Map(player);
    PlaceRailCommand prc = new PlaceRailCommand();
    PlayerMove playerMove = new PlayerMove();

    @Test
    public void testPlaceRail1() {
        map.spawnPlayer();
        playerMove.move(0, map);
        prc.execute(map);
        assertFalse(map.getGrid().getTile(4, 3).hasRail());
    }
}