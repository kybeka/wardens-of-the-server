package model;


/**
 * Write a description of class Tile here.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 05.05.2022
 */
public abstract class Tile
{
    public Tree tree;
    public Mine mine;
    protected int coordinateX;
    protected int coordinateY;
    
    /**
     * Getter method for the xCoord field.
     * 
     * @return the x coordinate of the player.
     */
    public int getXCoord() {
        return this.coordinateX;
    }

    /**
     * Getter method for the yCoord field.
     * 
     * @return the y coordinate of the player.
     */
    public int getYCoord() {
        return this.coordinateY;
    }
    
    /**
     * Getter method for the tile's character.
     * 
     * @return a character for the respective tile.
     */
    public abstract char getCharacter();
    
    /**
     * Getter method for the tile's mine.
     * 
     * @return the mine of this tile.
     */
    public abstract Mine getMine();
    
    /**
     * Getter method for the tile's tree.
     * 
     * @return the tree of this tile.
     */
    public abstract Tree getTree();
    
    /**
     * Getter method for the tile's rail.
     * 
     * @return the rail of this tile.
     */
    public abstract Railway getRail();
    
    /**
     * Method that assigns a rail to this tile.
     */
    public abstract void addRail();
    
    /**
     * Method that assigns a tree to this tile.
     * 
     */
    public abstract void addTree();
    
    /**
     * Method that assigns a mine to this tile.
     * 
     */
    public abstract void addMine();
    
    /**
     * Method that assigns a player to this tile.
     * 
     * @param player - the player being passed down to a new tile.
     */
    public abstract void addPlayer(Player player);
    
    /**
     * Method that assigns a train to this tile.
     */
    public abstract void addTrain();
    
    /**
     * Method that assigns a bridge to this tile.
     * 
     */
    public abstract void addBridge();
    
    /**
     * Method that sees if the tile has a mine.
     * 
     * @return true if the tile has a mine.
     */
    public abstract boolean hasMine();
    
    /**
     * Method that sees if the tile has a tree.
     * 
     * @return true if the tile has a tree.
     */
    public abstract boolean hasTree();
    
    /**
     * Method that sees if the tile has a train.
     * 
     * @return true if the tile has a train.
     */
    public abstract boolean hasTrain();
    
    /**
     * Method that sees if the tile has a rail.
     * 
     * @return true if the tile has a rail.
     */
    public abstract boolean hasRail();
    
    /**
     * Method for removing a rail.
     */
    public abstract void removeRail();
    
    /**
     * Method that sees if the tile has no rail.
     * 
     * @return true if the tile has no rail.
     */
    public abstract boolean hasNoRail();
    
    /**
     * Method that sees if the tile has a player.
     * 
     * @return true if the tile has a player.
     */
    public abstract boolean hasPlayer();
    
    /**
     * Method that checks if the tile has a lake.
     * 
     * @return true if it does.
     */
    public abstract boolean hasLake();
    
    /**
     * Method that checks if the tile has a bridge.
     * 
     * @return true if it does.
     */
    public abstract boolean hasBridge();
    
    /**
     * Method that assigns a lake entity to a given tile.
     */
    public abstract void addLake();
    
    /**
     * Method checking if this tile is empty; i.e. does not contain any Entity.
     * 
     * @return true if there is nothing on the tile.
     */
    public abstract boolean isEmpty();
    
    /**
     * Method removing the player from a tile.
     */
    public abstract void removePlayer();
    
    /**
     * Method removing a mine from a tile.
     */
    public abstract void removeMine();
    
    /**
     * Method removing a tree from a tile.
     */
    public abstract void removeTree();
    
    /**
     * Method evaluating, if the win condition was met.
     * 
     * @return true if the train reached the station.
     */
    public abstract boolean trainReachedStation();
    
    /**
     * Method evaluating, if the lose condition was met.
     * 
     * @return true if the train hit the player.
     */
    public abstract boolean playerRanOver();
}
