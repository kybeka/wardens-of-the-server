package model;

/**
 * The model of the map that will be populated randomly with entities.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 27.05.2022
 */
public class ModelMap extends Grid {
    /**
     * Constructor for the ModelMap class.
     */
    public ModelMap() {
        super(10, 10);
    }
    
    /**
     * Method for populating the map with different entities.
     * 
     * @param map - The initial map of the game.
     */
    public void populate(Map map) {
        this.populateWithGrass();
        this.populateWithRails(map);
        this.removeRandomRail(map);
        this.addEntities();
    }
    
    /**
     * Getter method for the tile field.
     * 
     * @param coordinateX - the x coordinate of the wanted field.
     * @param coordinateY - the y coordinate of the wanted field.
     * @return the tile at the given coordinates.
     */
    public Tile getTile(int coordinateX, int coordinateY) {
        return this.getGrid()[coordinateX][coordinateY];
    }
}