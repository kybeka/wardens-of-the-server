package model;

/**
 * Class Lake represents an additional obstacle for both the player and the train.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 25.05.2022
 */
public class Lake extends Entity {
    /**
     * Method that returns the character assigned to a lake.
     * 
     * @return this entity's assigned character.
     */
    public char getCharacter() {
        return 'L';
    }
}