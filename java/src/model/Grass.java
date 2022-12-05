package model;

/**
 * A grass tile is a tile that can contain any entity.
 *
 */
public class Grass extends Tile
{
    private static final char CHARACTER = 'G';
    private Tree tree;
    private Mine mine;
    private Railway rail;
    private Lake lake;
    private Bridge bridge;
    private Player player;
    private Train train;
    
    /**
     * Constructor for objects of class Grass.
     * 
     * @param coordinateX - the x coordinate of the grass tile.
     * @param coordinateY - the y coordinate of the grass tile.
     */
    public Grass(int coordinateX, int coordinateY)
    {
        super();
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.tree = null;
        this.mine = null;
    }

    /**
     * Getter method for the mine field.
     * 
     * @return the mine on this Grass Tile.
     */
    public Mine getMine() {
        return this.mine;
    }

    /**
     * Getter method for the tree field.
     * 
     * @return the tree on this Grass Tile.
     */
    public Tree getTree() {
        return this.tree;
    }
 
    /**
     * Getter method for the rail field.
     * 
     * @return the rail on this Grass Tile.
     */ 
    public Railway getRail() {
        return this.rail;
    }
 
    /**
     * A method for seeing if the grass tile already contains a rail.
     * 
     * @return a boolean stating if the grass tile already contains a rail.
     */
    public boolean hasRail() {
        return this.rail != null && this.train == null && this.player == null;
    }

    

    /** 
     * Method checking whether the grass tile has no rail.
     * 
     * @return true if there is no rail.
     */
    public boolean hasNoRail() {
        return this.rail == null;
    }

    /**
     * A method for seeing if the grass tile already contains a mine.
     * 
     * @return a boolean stating if the grass tile already contains a mine.
     */
    public boolean hasMine() {
        return this.mine != null;
    }

    /**
     * A method for seeing if the grass tile already contains a tree.
     * 
     * @return a boolean stating if the grass tile already contains a tree.
     */
    public boolean hasTree() {
        return this.tree != null;
    }

    /**
     * Method that checks whether the tile contains a player.
     * 
     * @return boolean indicating if it contains a player entity.
     */
    public boolean hasPlayer() {
        return this.player != null;
    }

    /**
     * Mehtod that checks if the tile contains a train.
     * 
     * @return boolean indicating if it contains a train entity.
     */
    public boolean hasTrain() {
        return this.train != null;
    }
    
    /**
     * Method that checks if the tile contains a lake.
     * 
     * @return boolean indicating if it contains a lake entity.
     */
    public boolean hasLake() {
        return this.lake != null;
    }
    
    /**
     * Method that checks if the tile contains a lake.
     * 
     * @return true if it does.
     */
    public boolean hasBridge() {
        return this.bridge != null;
    }
    
    /**
     * Mehtod checking if the tile is empty;
     * i.e. does not contain a mine, a tree, the player or the train entities.
     * 
     * @return boolean indicating if the grass tile is empty
     */
    public boolean isEmpty() {
        return ! this.hasTree()
            && ! this.hasMine()
            && ! this.hasPlayer()
            && ! this.hasTrain()
            && ! this.hasRail()
            && ! this.hasLake()
            && ! this.hasBridge();
    }

    /**
     * Link a new player to the grass tile, if the current tile is empty.
     *  
     * @param player - the player being passed to the new grass tile.
     */
    public void addPlayer(Player player) {
        if (this.isEmpty() || this.hasRail() || this.hasBridge()) {
            this.player = new Player();
        }
    }

    /**
     * Link a new train to the tile, if it has a Rail.
     * 
     */
    public void addTrain() {
        if (this.hasRail()) {
            this.train = new Train();
        }
    }
    
    /**
     * Method that assigns a mine to the grass tile, if it is empty.
     * 
     */
    public void addMine() {
        if (this.isEmpty()) {
            this.mine = new Mine();
        }
    }

    /**
     * Method that assigns a rail to the grass tile, if it is empty or has a bridge.
     * 
     */
    public void addRail() {
        if (this.isEmpty() || this.hasLake() && this.hasBridge()) {
            this.rail = new Railway();
        }
    }
    
    /**
     * Method that assigns a lake to the grass tile, if it is empty.
     */
    public void addLake() {
        if (this.isEmpty()) {
            this.lake = new Lake();
        }
    }
    
    /**
     * Method that assigns a bride entity to the grass tile strictly if it already has a lake.
     */
    public void addBridge() {
        if (this.hasLake()) {
            this.bridge = new Bridge();
        }
    }
    
    /**
     * Method that removes the player from the tile, used in the PlayerMove class.
     */
    public void removePlayer() {
        this.player = null;
    }

    /**
     * Method that removes the mine from the tile,
     * necessary for limiting available resources to the player.
     */
    public void removeMine() {
        this.mine = null;
    }

    /**
     * Method that removes the tree from the tile,
     * necessary for limiting available resources to the player.
     */
    public void removeTree() {
        this.tree = null;
    }
    
    /**
     * Method that removes the rail from the tile.
     */
    public void removeRail() {
        this.rail = null;
    }

    /**
     * Method that assigns a tree to the grass tile, if it is empty.
     * 
     */
    public void addTree() {
        if (this.isEmpty()) {
            this.tree = new Tree();
        }
    }

    /**
     * Method for evaluating a win. Used in the TUI class of the tui package.
     * 
     * @return true if the train reached the station.
     */
    public boolean trainReachedStation() {
        return this.rail.isStation() && this.hasTrain();
    }

    /**
     * Possible method for evaluating a loss. May be used in GUI and TUI.
     * 
     * @return true if the player was ran over by the train.
     */
    public boolean playerRanOver() {
        return this.hasTrain() && this.hasPlayer();
    }

    /**
     * Method to retrieve the character of the entity.
     * It is assigned to the grass tile,
     * or it is the grass tile itself, if empty.
     * 
     * @return characters of the entities or the grass tile
     */
    public char getCharacter() {
        boolean playerOnTile = this.hasPlayer() || this.hasPlayer() && this.hasRail() || this.hasPlayer() && this.hasBridge() && this.hasLake();
        boolean trainOnTile = this.hasTrain() || this.hasTrain() && this.hasRail();
        if (this.hasTree()) {
            return this.tree.getCharacter();
        } else if (this.hasMine()) {
            return this.mine.getCharacter();
        } else if (playerOnTile) {
            return this.player.getCharacter();
        } else if (trainOnTile) {
            return this.train.getCharacter();    
        } else if (this.hasRail() && ! this.hasTrain() || this.hasRail() && this.hasBridge()) {
            return this.rail.getCharacter();
        } else if (this.hasBridge()) {
            return this.bridge.getCharacter(); 
        } else if (this.hasLake()) {
            return this.lake.getCharacter(); 
        } else { 
            return CHARACTER;
        }
    }
}
