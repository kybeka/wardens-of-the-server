package model;


/**
 * A Tree is a source for wood.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 05.05.2022
 */
public class Tree extends Resources
{
    /**
     * Constructor for objects of class Tree.
     */
    public Tree()
    {
        super();
        resourceCount = 5;
    }
    
    /**
     * Method for getting the character of tiles containing trees.
     * 
     * @return the 'T' character of tiles with a tree.
     */
    public char getCharacter() {
        return 'T';
    }
}
