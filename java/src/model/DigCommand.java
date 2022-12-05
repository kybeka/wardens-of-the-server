package model;

/**
 * A class dedicated to the dig() command utilized by the player.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 27.05.2022
 */
public class DigCommand extends InteractCommands {
    /**
     * Method for digging resources present on the given map.
     * 
     * @param map - The map of the game.
     */
    public void execute(Map map) {
        Tile interactingTile = map.getAdjacent(map.getPlayer().getDirection(), map.getPlayer());
        if (interactingTile.hasMine()) {
            map.getPlayer().ironCounter++;
            interactingTile.getMine().resourceCount--;
            if (interactingTile.getMine().exhausted()) {
                interactingTile.removeMine();
            }
        } else if (interactingTile.hasTree()) {
            map.getPlayer().woodCounter++;
            interactingTile.getTree().resourceCount--;
            if (interactingTile.getTree().exhausted()) {
                interactingTile.removeTree();
            }
        }
    }
}