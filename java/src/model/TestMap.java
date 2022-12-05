package model;


/**
 * An example for class Grid.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 27.05.2022
 */
public class TestMap extends Grid {
    /**
     * Constructor for the ModelMap class.
     */
    public TestMap() {
        super(10, 10);
    }
    
    /**
     * Method for populating the map with different entities.
     * 
     * @param map - The initial map of the game.
     */
    public void populate(Map map) {
        for (int i = 0; i < getGrid().length; i++) {
            for (int j = 0; j < getGrid()[i].length; j++) {
                this.getGrid()[j][i] = new Grass(j, i);
            }
        }
        
        
        this.getGrid()[0][4].addRail();
        this.getGrid()[1][4].addRail();
        this.getGrid()[2][4].addRail();
        this.getGrid()[3][4].addRail();
        this.getGrid()[4][4].addRail();
        this.getGrid()[5][4].addRail();
        //this.getGrid()[6][4].addRail();
        this.getGrid()[7][4].addRail();
        this.getGrid()[8][4].addRail();
        
        this.getGrid()[4][3].addLake();
        this.getGrid()[6][4].addLake();
        
        map.setLast(this.getGrid()[9][4]);
        map.getLast().addRail();
        map.getLast().getRail().createStation();
        
        this.getGrid()[1][6].addMine();
        this.getGrid()[2][6].addMine();
        this.getGrid()[3][6].addMine();
        this.getGrid()[4][6].addMine();
        this.getGrid()[5][6].addMine();
        
        this.getGrid()[1][2].addTree();
        this.getGrid()[2][2].addTree();
        this.getGrid()[3][2].addTree();
        this.getGrid()[4][2].addTree();
        this.getGrid()[5][2].addTree();
    }
    
    /**
     * Method for getting a Tile at a given position.
     * 
     * @param coordinateX - the x coordinate of the tile.
     * @param coordinateY - the y coordinate of the tile.
     * @return a tile at the given position.
     */
    public Tile getTile(int coordinateX, int coordinateY) {
        return this.getGrid()[coordinateX][coordinateY];
    }
}