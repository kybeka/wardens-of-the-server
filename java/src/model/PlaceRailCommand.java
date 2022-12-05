package model;

/**
 * A class dedicated to the placing of rails on the grass tiles by the player.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 27.05.2022
 */
public class PlaceRailCommand extends InteractCommands {
    /**
     * Method for placing rails.
     * 
     * @param map - The map of the game.
     */
    public void execute(Map map) {
        Tile interactingTile = map.getAdjacent(map.getPlayer().getDirection(), map.getPlayer());
        if (map.getPlayer().canBuildRail(map) && interactingTile.isEmpty() || interactingTile.hasBridge()) {
            interactingTile.addRail();
            map.getPlayer().woodCounter = map.getPlayer().woodCounter - 4;
            map.getPlayer().ironCounter = map.getPlayer().ironCounter - 3;
        }
    }
}