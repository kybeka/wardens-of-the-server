package model;


/**
 * Entity is the super class for any object that appears on the map (not the foundation of the map).
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 05/05/2022
 */
public abstract class Entity
{
    protected int coordinateX;
    protected int coordinateY;
    
    /**
     * Method for getting the entity's corresponding character.
     * 
     * @return the character of the entity.
     */
    public abstract char getCharacter();
    
    /**
     * Getter method for the x coordinate of the entity.
     * 
     * @return the x coordinate.
     */
    public int getXCoord() {
        return this.coordinateX;
    }
    
    /**
     * Getter method for the y coordinate of the entity.
     * 
     * @return the y coordinate.
     */
    public int getYCoord() {
        return this.coordinateY;
    }
}
