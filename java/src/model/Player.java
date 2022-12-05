package model;

/**
 * A Player is the character moving through the Map.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 25.05.2022
 */
public class Player extends Movable
{
    protected int woodCounter;
    protected int ironCounter;

    /**
     * Constructor for class Player.
     */
    public Player()
    {
        super();
        this.woodCounter = 0;
        this.ironCounter = 0;
        this.coordinateX = 3;
        this.coordinateY = 3;
        this.direction = 0;
    }

    /**
     * Getter method for the woodCount field of class Player.
     * 
     * @return the amount of wood the player collected from the mines.
     */
    public int woodCount() {
        return this.woodCounter;
    }

    /**
     * Getter method for the ironCount field of class Player.
     * 
     * @return the amount of iron the player collected from the mines.
     */
    public int ironCount() {
        return this.ironCounter;
    }

    /**
     * Method used to change the x coordinate of the player.
     * 
     * @param newX - the new x coordinate of the player.
     * @return the changed x coordinate of the player.
     */
    public int makeX(int newX) {
        return this.coordinateX = newX;
    }

    /**
     * Method used to change the y coordinate of the player.
     * 
     * @param newY - new y coordinate of the player.
     * @return the changed y coordinate of the player.
     */
    public int makeY(int newY) {
        return this.coordinateY = newY;
    }

    /**
     * Method evaluating whether the player has enough resources to build a rail.
     * 
     * @param map - The current map of the game.
     * @return true if the player has enough resources to build a rail.
     */
    public boolean canBuildRail(Map map) {
        return this.woodCounter >= 4 && this.ironCounter >= 3;
    }

    /**
     * Method evaluating whether the player has enough resources to build a bridge.
     * 
     * @param map - The current map of the game.
     * @return boolean indicating if the player has enough resources.
     */
    public boolean canBuildBridge(Map map) {
        return this.woodCounter >= 3 && this.ironCounter >= 2;
    }

    /**
     * Method that returns the character of a tile containing player.
     * 
     * @return the player's character.
     */
    public char getCharacter() {
        return 'P';
    }
}
