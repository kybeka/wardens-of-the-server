package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class for making a particular grid for the map;
 * used in order to create different variants of the map,
 * in order to avoid same entities spawning in the exact same place. 
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 29.05.2022
 */
public abstract class Grid {
    private Tile[][] grid;
    private int numberOfMines;
    private int numberOfTrees;
    private int numberOfLakes;
    private final int gridHeight;
    private final int gridWidth;
    private int[] trainPath = {3, 3, 3, 3, 3, 3, 3, 3, 3};
    protected static final int MAX_TREES = 10;
    protected static final int MAX_MINES = 10;
    protected static final int MAX_LAKES = 3;
    private ArrayList<Tile> railTiles;

    /**
     * Constructor for the Grid class.
     * 
     * @param height - the given height of the grid.
     * @param width - the given width of the grid.
     */
    public Grid(int height, int width) {
        this.railTiles = new ArrayList<>();
        this.numberOfMines = 0;
        this.numberOfTrees = 0;
        this.gridHeight = height;
        this.gridWidth = width;
        this.grid = new Tile[height][width];
    }

    /**
     * Getter method for the trainPath field.
     * 
     * @return the train's path.
     */
    public int[] getTrainPath() {
        int [] path = this.trainPath;
        return path;
    }
    
    /**
     * Getter method for the railTiles field.
     * 
     * @return the arraylist of rail tiles. 
     */
    public ArrayList<Tile> getRailTiles() {
        return this.railTiles;
    }
    
    /**
     * Method for populating the grid with rails.
     * 
     * @param map - the map of the game.
     */
    public void populateWithRails(Map map) {
        int coordinateX = map.getTrain().getXCoord();
        int coordinateY = map.getTrain().getYCoord();
        this.getGrid()[coordinateY][coordinateX].addRail();
        for (int direction : getTrainPath()) {
            if (direction == 0) {
                this.getGrid()[coordinateY][coordinateX + 1].addRail();
                railTiles.add(this.getGrid()[coordinateY][coordinateX + 1]);
                coordinateX++;
            } else if (direction == 1) {
                this.getGrid()[coordinateY - 1][coordinateX].addRail();
                railTiles.add(this.getGrid()[coordinateY - 1][coordinateX]);
                coordinateY--;
            } else if (direction == 2) {
                this.getGrid()[coordinateY][coordinateX - 1].addRail();
                railTiles.add(this.getGrid()[coordinateY][coordinateX - 1]);
                coordinateX--;
            } else if (direction == 3) {
                this.getGrid()[coordinateY + 1][coordinateX].addRail();
                railTiles.add(this.getGrid()[coordinateY + 1][coordinateX]);
                coordinateY++;
            }
        }
        map.setLast(this.getGrid()[gridHeight - 1][coordinateX]);
        map.getLast().addRail();
        map.getLast().getRail().createStation();
    }
    
    /**
     * Method that removes a random rail from the generated ones.
     * 
     * @param map - The initial map of the game.
     */
    public void removeRandomRail(Map map) {
        Random random = new Random();
        boolean notRemoved = true;
        while (notRemoved) {
            Tile toBeRemoved = this.railTiles.get(random.nextInt(gridHeight));
            if (! toBeRemoved.hasTrain() && ! toBeRemoved.getRail().isStation()) {
                toBeRemoved.removeRail();
                notRemoved = false;
            }
        }
    }
    
    /**
     * Getter method for the grid field.
     * 
     * @return the matrix of tiles that make up the map.
     */
    public Tile[][] getGrid() {
        Tile[][] holder = this.grid;
        return holder;
    }

    /**
     * Getter method for the gridWidth field.
     * 
     * @return the grid width.
     */
    public int getGridWidth() {
        return this.gridWidth;
    }

    /**
     * Getter method for the gridHeight field.
     * 
     * @return the grid height.
     */
    public int getGridHeight() {
        return this.gridHeight;
    }

    /**
     * Method for picking the grid.
     * 
     * @return the chosen grid.
     */
    public Grid pickGrid() {
        System.out.println("Pick the level of difficulty:");
        System.out.println("[1] Easy - small map, lots of resources, a few missing rails, train moves slowly");
        System.out.println("[2] Medium - bigger map, less resources, more missing rails, train moves quicker");
        System.out.println("[3] Hard - giant map, just enough resources, many rails are missing");

        return null;
    }

    /**
     * Getter method for the tile field.
     * 
     * @param coordinateX - the x coordinate of the tile.
     * @param coordinateY - the y coordinate of the tile.
     * @return the tile at the given coordinates.
     */
    public abstract Tile getTile(int coordinateX, int coordinateY);
    
    /**
     * Method for populating the grid with grass.
     */
    public void populateWithGrass() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.grid[j][i] = new Grass(j, i);
            }
        }
    }
    
    /**
     * Method for populating the grid with trees, mines, and lakes.
     */
    public void addEntities() {
        Random random = new Random();
        while (numberOfTrees < MAX_TREES) {
            int coordinateY = random.nextInt(this.gridHeight);
            int coordinateX = random.nextInt(this.gridWidth);
            if (getTile(coordinateX, coordinateY).hasNoRail()) {
                this.grid[coordinateY][coordinateX].addTree();
                numberOfTrees++;
            }
        }
        while (numberOfMines < MAX_MINES) {
            int coordinateY = random.nextInt(this.gridHeight);
            int coordinateX = random.nextInt(this.gridWidth);
            if (getTile(coordinateX, coordinateY).hasNoRail()) {
                this.grid[coordinateY][coordinateX].addMine();
                numberOfMines++;
            }
        }
        while (numberOfLakes < MAX_LAKES) {
            int coordinateY = random.nextInt(this.gridHeight);
            int coordinateX = random.nextInt(this.gridWidth);
            if (getTile(coordinateX, coordinateY).hasNoRail()) {
                this.grid[coordinateY][coordinateX].addLake();
                numberOfLakes++;
            }
        }
    }
    
    /**
     * Method used to populate the given map.
     * 
     * @param map - The initial map of the game.
     */
    public abstract void populate(Map map);
}