package model;

/**
 * The class resposible for the movement of the train.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 25.05.2022
 */
public class TrainMove {
    private int pathIndex;
    private PlayerMove playerMove;
    
    /**
     * Constructor for class TrainMove.
     */
    public TrainMove() {
        super();
        this.pathIndex = 0;
        this.playerMove = new PlayerMove();
    }
    
    /**
     * The main method for train movement that either propagates the train or derails it,
     * depending on whether the train was placed on a tile with a rail or an empty tile.
     * 
     * @param direction - The direction of the train.
     * @param map - The map that is being currently used.
     */
    public void move(int direction, Map map) {
        if (map.grid.getTile(map.getTrain().getYCoord(), map.getTrain().getXCoord()).hasNoRail()) {
            map.getTrain().derail();
        } else if (direction == 0) {
            map.getTrain().setDirection(0);
            moveRight(map);
        } else if (direction == 1) {
            map.getTrain().setDirection(1);
            moveUp(map);
        } else if (direction == 2) {
            map.getTrain().setDirection(2);
            moveLeft(map);
        } else if (direction == 3) {
            map.getTrain().setDirection(3);
            moveDown(map);
        }
    }
    
    /**
     * Getter method for the playerMove field.
     * 
     * @return the movement of the player.
     */
    public PlayerMove getPlayerMove() {
        return this.playerMove;
    }
    
    /**
     * Method for making the train move according to the train path.
     * 
     * @param map - the current map of the game.
     */
    public void givePath(Map map) {
        int[] path = map.getGrid().getTrainPath();
        if (playerMove.getStepCounter() % 6 == 0) {
            this.move(path[pathIndex + 1], map);
            //TrainMove
        }
    }
    
    /**
     * Method that moves the train down.
     * 
     * @param map - The map that is being currently used.
     */
    public void moveDown(Map map) {
        Tile tileBelowTrain = map.getAdjacent(3, map.getTrain()); //map.grid.getTile(map.getTrain().getYCoord() + 1, map.getTrain().getXCoord());
        tileBelowTrain.addTrain();
        map.getTrain().makeY(map.getTrain().getYCoord() + 1);
    }
    
    /**
     * Method that moves the train up.
     * 
     * @param map - The map that is being currently used.
     */
    public void moveUp(Map map) {
        Tile tileAboveTrain = map.getAdjacent(1, map.getTrain());
        tileAboveTrain.addTrain();
        map.getTrain().makeY(map.getTrain().getYCoord() - 1);
    }
    
    /**
     * Method that moves the train left.
     * 
     * @param map - The map that is being currently used.
     */
    public void moveLeft(Map map) {
        Tile tileLeftToTrain = map.getAdjacent(2, map.getTrain());
        tileLeftToTrain.addTrain();
        map.getTrain().makeX(map.getTrain().getXCoord() - 1);
    }
    
    /**
     * Method that moves the train right.
     * 
     * @param map - The map that is being currently used.
     */
    public void moveRight(Map map) {
        Tile tileRightToTrain = map.getAdjacent(0, map.getTrain());
        tileRightToTrain.addTrain();
        map.getTrain().makeX(map.getTrain().getXCoord() + 1);
    }
}