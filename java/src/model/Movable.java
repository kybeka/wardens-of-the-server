package model;

/**
 * Movable represents all entities that can move around the map.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 27.05.2022
 */
public abstract class Movable extends Entity
{
    // 0 = EAST, 1 = NORTH, 2 = WEST, 3 = SOUTH
    protected int direction;

    /**
     * Getter method for the direction field.
     * 
     * @return the direction of the movable.
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * Mutator method for the direction field.
     * 
     * @param value - new direction of the movable.
     */
    public void setDirection(int value) {
        this.direction = value;
    }
}

