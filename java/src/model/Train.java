package model;


/**
 * A Train consists of a carriage number and the rest of the train's body.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 05.05.2022
 */
public class Train extends Movable
{
    private boolean derailed;
    
    /**
     * Public constructor for objects of class Train.
     */
    public Train() {
        super();
        this.coordinateX = 4;
        this.coordinateY = 0;
        this.derailed = false;
    }
    
    /**
     * Method for the coordinateY field,
     * that mutates the coordinateX field and returns the new given x coordinate.
     * 
     * @param newX - new given x coordinate value.
     * @return the new x coordinate.
     */
    public int makeX(int newX) {
        return this.coordinateX = newX;
    }
    
    /**
     * Method for the coordinateY field,
     * that mutates the coordinateY field and returns the new given y coordinate.
     * 
     * @param newY - new given y coordinate value.
     * @return the new y coordinate.
     */
    public int makeY(int newY) {
        return this.coordinateY = newY;
    }
    
    /**
     * Mutator method for the derailed field.
     */
    public void derail() {
        this.derailed = true;
    }
    
    /**
     * Getter method for the derailed field.
     * 
     * @return true if the train is derailed.
     */
    public boolean derailedStatus() {
        return this.derailed;
    }
    
    /**
     * The method retrieves the characted for train.
     * 
     * @return character for train
     */
    public char getCharacter() {
        return 't';
    }
}
