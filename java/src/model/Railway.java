package model;

/**
 * Railway is an entity on a Tile that enables the passing of the train.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 05/05/2022
 */
public class Railway extends Entity
{
    private boolean station;
    
    /**
     * Constructor for the Railway class.
     */
    public Railway() {
        super();
        this.station = false;
    }

    /**
     * Method checking if this railway is considered a station.
     * 
     * @return true if this railway is "next to" a station.
     */
    public boolean isStation() {
        return this.station;
    }
    
    /**
     * Mutator method that makes this railway have a station.
     */
    public void createStation() {
        this.station = true;
    }
    
    /**
     * Method that gives the character of the current rail.
     * 
     * @return characters: 'R' if it is a railway, or 'S' if it is a station.
     */
    public char getCharacter() {
        if (this.station) {
            return 'S';
        }
        return 'R';
    }
}