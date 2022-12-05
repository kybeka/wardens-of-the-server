package model;


/**
 * A mine stores iron, utilized to build bridges.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 04.05.2022
 */
public class Mine extends Resources
{   
    /**
     * Constructor for objects of class Mine.
     */
    public Mine()
    {
        super();
        resourceCount = 4;
    }
    
    /**
     * Method that gets the character of tiles containing mines.
     * 
     * @return the mine 'm' character.
     */
    public char getCharacter() {
        return 'M';
    }
}
