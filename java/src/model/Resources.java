package model;


/**
 * Resources are what the Player needs to create a rail and make the Train pass.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 05.05.2022
 */
public abstract class Resources extends Entity
{
    protected int resourceCount;
    
    /**
     * Method for getting the character of the resource that is placed on a particular tile.
     * 
     * @return the character of the resource.
     */
    public abstract char getCharacter();
    
    /**
     * Getter method for the resource count.
     * 
     * @return the resourceCount.
     */
    public int getResourceCount() {
        return this.resourceCount;
    }
    
    /**
     * Method evauating if the resourceCount is at 0.
     * 
     * @return true if the resource count has zero resources.
     */
    public boolean exhausted() {
        return this.resourceCount == 0;
    }
}
