package model;

/**
 * The class responsible for the player's movement.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 25.05.2022
 */
public class PlayerMove {
    private int stepCounter;
    
    /**
     * The method that is mainly responsible for player movement.
     * 
     * @param direction - The direction of the player.
     * @param map - The current map of the game.
     */
    public void move(int direction, Map map) {
        if (map.withinBounds(direction)) {
            incrementStepCounter();
            if (direction == 0) {
                map.getPlayer().setDirection(0);
                moveRight(map);
            } else if (direction == 1) {
                map.getPlayer().setDirection(1);
                moveUp(map);
            } else if (direction == 2) {
                map.getPlayer().setDirection(2);
                moveLeft(map);
            } else if (direction == 3) {
                map.getPlayer().setDirection(3);
                moveDown(map);
            }
        }
    }
    
    /**
     * Getter method for the counter that controls the train movement.
     * 
     * @return int the counter number.
     */
    public int getStepCounter() {
        return this.stepCounter;
    }
    
    /**
     * Increment method for the counter.
     * 
     * @return in the new counter.
     */
    public int incrementStepCounter() {
        return this.stepCounter++;
    }
    
    /**
     * Method that moves the player to the right.
     * 
     * @param map - The current map of the game.
     */
    public static void moveRight(Map map) {
        Tile currentPosition = map.grid.getTile(map.getPlayer().getYCoord(), map.getPlayer().getXCoord());
        Tile nextPosition = map.getAdjacent(0, map.getPlayer()); 
        if (nextPosition.isEmpty() || nextPosition.hasRail() || nextPosition.hasBridge()) {
            currentPosition.removePlayer();
            nextPosition.addPlayer(map.getPlayer());
            map.getPlayer().makeX(map.getPlayer().getXCoord() + 1);
        }
    }

    /**
     * Method that moves the player up.
     * 
     * @param map - The current map of the game.
     */
    public static void moveUp(Map map) {
        Tile currentPosition = map.grid.getTile(map.getPlayer().getYCoord(), map.getPlayer().getXCoord());
        Tile nextPosition = map.getAdjacent(1, map.getPlayer()); 
        if (nextPosition.isEmpty() || nextPosition.hasRail() || nextPosition.hasBridge()) {
            currentPosition.removePlayer();
            nextPosition.addPlayer(map.getPlayer());
            map.getPlayer().makeY(map.getPlayer().getYCoord() - 1);
        }
    }

    /**
     * Method that moves the player to the left.
     * 
     * @param map - The current map of the game.
     */
    public static void moveLeft(Map map) {
        Tile currentPosition = map.grid.getTile(map.getPlayer().getYCoord(), map.getPlayer().getXCoord());
        Tile nextPosition = map.getAdjacent(2, map.getPlayer());
        if (nextPosition.isEmpty() || nextPosition.hasRail() || nextPosition.hasBridge()) {
            currentPosition.removePlayer();
            nextPosition.addPlayer(map.getPlayer());
            map.getPlayer().makeX(map.getPlayer().getXCoord() - 1);
        }
    }

    /**
     * Method that moves the player down.
     * 
     * @param map - The current map of the game.
     */
    public static void moveDown(Map map) {
        Tile currentPosition = map.grid.getTile(map.getPlayer().getYCoord(), map.getPlayer().getXCoord());
        Tile nextPosition = map.getAdjacent(3, map.getPlayer());
        if (nextPosition.isEmpty() || nextPosition.hasRail() || nextPosition.hasBridge()) {
            currentPosition.removePlayer();
            nextPosition.addPlayer(map.getPlayer());
            map.getPlayer().makeY(map.getPlayer().getYCoord() + 1);
        }
    }
}