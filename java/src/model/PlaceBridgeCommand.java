package model;

/**
 * A class used to accomodate the command responsible for placing bridges on lakes.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 27.05.2022
 */
public class PlaceBridgeCommand extends InteractCommands {
    /**
     * Method for placing bridges.
     * 
     * @param map - The map of the game.
     */
    public void execute(Map map) {
        Tile interactingTile = map.getAdjacent(map.getPlayer().getDirection(), map.getPlayer());
        if (interactingTile.hasLake() && map.getPlayer().canBuildBridge(map)) {
            interactingTile.addBridge();
            map.getPlayer().woodCounter = map.getPlayer().woodCounter - 3;
            map.getPlayer().ironCounter = map.getPlayer().ironCounter - 2;
        }
    }
}
