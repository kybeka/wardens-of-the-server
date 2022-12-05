package model;


/**
 * Map is made up of objects of class Tile.
 * Each tile contains information about entities that it has.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 27.05.2022
 */
public class Map
{
    private Player player;
    private Train train;
    private Tile last;
    protected Grid grid;
    protected int[] mapPicker;
    
    /**
     * Constructor for objects of class Map.
     */
    public Map()
    {
        this.train = new Train();
        this.player = new Player();
        this.grid = new ModelMap();
        this.grid.populate(this);
    }
    
    /**
     * Test constructor for use in unit testing.
     * 
     * @param player the test player.
     */
    public Map(Player player) {
        this.train = new Train();
        this.player = player;
        this.grid = new TestMap();
        this.grid.populate(this);
    }
    
    /**
     * Method for evaluating if the player can move in the given direction.
     * 
     * @param direction - the direction of the player.
     * @return a boolean indicating if the player's potential move would be within bounds;
     *      used in the move() method of class PlayerMove.
     */
    public boolean withinBounds(int direction) {
        boolean canMoveRight = -1 < (this.getPlayer().getXCoord() + 1) && (this.getPlayer().getXCoord() + 1) < this.getGrid().getGridWidth();
        boolean canMoveUp = -1 < (this.getPlayer().getYCoord() - 1) && (this.getPlayer().getYCoord() - 1) < this.getGrid().getGridHeight();
        boolean canMoveLeft = -1 < (this.getPlayer().getXCoord() - 1) && (this.getPlayer().getXCoord() - 1) < this.getGrid().getGridWidth();
        boolean canMoveDown = -1 < (this.getPlayer().getYCoord() + 1) && (this.getPlayer().getYCoord() + 1) < this.getGrid().getGridHeight();
        if (direction == 0) {
            return canMoveRight;
        } else if (direction == 1) {
            return canMoveUp;
        } else if (direction == 2) {
            return canMoveLeft;
        } else if (direction == 3) {
            return canMoveDown;
        }
        return false;
    }
    
    /**
     * Getter method for the last field.
     * 
     * @return the last tile of the railway.
     */
    public Tile getLast() {
        return this.last;
    }
    
    /**
     * Method for setting the last tile that contains a rail.
     * 
     * @param tile - the tile to be set as the last tile of the railway.
     */
    public void setLast(Tile tile) {
        this.last = tile;
    }
    
    /**
     * Getter method for the train field.
     * 
     * @return the train that is currently on the map.
     */
    public Train getTrain() {
        return this.train;
    }
    
    /**
     * Method used for placing the train on the map for the first time.
     */
    public void spawnTrain() {
        this.grid.getTile(this.train.getYCoord(), this.train.getXCoord()).addTrain();
    }   
    
    /**
     * Method used for placing the player on the map for the first time.
     */
    public void spawnPlayer() {
        this.grid.getTile(this.player.getYCoord(), this.player.getXCoord()).addPlayer(player);
    }
    
    /**
     * Method used for checking whether the player is on the same tile as the train;
     * describes a losing condition causing the game to be over.
     * 
     * @return true if the player got ran over by the train.
     */
    public boolean playerRanOver() {
        return this.grid.getTile(this.getTrain().getYCoord(), this.getTrain().getXCoord()).hasPlayer();
    }
    
    /**
     * Getter method for field player.
     * 
     * @return the player.
     */
    public Player getPlayer() {
        return this.player;
    }
    
    /**
     * Getter method for field grid.
     * 
     * @return the grid of the map.
     */
    public Grid getGrid() {
        return this.grid;
    }
    
    /**
     * Method used in the TrainMove and PlayerMove classes;
     * used to extract the adjacent tile in the given direction.
     * 
     * @param direction - the direction of the movable object.
     * @param movable - the movable that is being moved around.
     * @return the wanted adjacent tile.
     */
    public Tile getAdjacent(int direction, Movable movable) {
        if (direction == 0) {
            if (movable == this.player) {
                return this.grid.getTile(this.player.getYCoord(), this.player.getXCoord() + 1);
            }
            return this.grid.getTile(this.train.getYCoord(), this.train.getXCoord() + 1);
        } else if (direction == 1) {
            if (movable == this.player) {
                return this.grid.getTile(this.player.getYCoord() - 1, this.player.getXCoord());
            }
            return this.grid.getTile(this.train.getYCoord() - 1, this.train.getXCoord());
        } else if (direction == 2) {
            if (movable == this.player) {
                return this.grid.getTile(this.player.getYCoord(), this.player.getXCoord() - 1);
            }
            return this.grid.getTile(this.train.getYCoord(), this.train.getXCoord() - 1);
        } else if (direction == 3) {
            if (movable == this.player) {
                return this.grid.getTile(this.player.getYCoord() + 1, this.player.getXCoord());
            }
            return this.grid.getTile(this.train.getYCoord() + 1, this.train.getXCoord());
        }
        return null;
    }
}