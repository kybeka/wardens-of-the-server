package model;

/**
 * Class bridge allows us to create bridges over Lakes,
 * in order to make either the player pass the lake or, 
 * if a rail is built on top of it, allow the train to pass over the lake.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 25.05.2022
 */
public class Bridge extends Entity {
    
    /**
     * Method that returns the character assigned to a bridge.
     * 
     * @return this entity's assigned character.
     */
    public char getCharacter() {
        return 'B';
    }
}