package gui;

import model.Map;
import model.Tile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * GameContent class, scans the game map and transforms it into graphics.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * 
 * @version 28.05.2022
 */
public final class GameContent extends JComponent {
    private static Map map = new Map();
    private static final int CELL_SIZE = 100;
    private static final int WIDTH = CELL_SIZE * map.getGrid().getGridWidth();
    private static final int HEIGHT = CELL_SIZE * map.getGrid().getGridHeight();
    
    // offset to account for the height being insufficient to show entire grid
    private static final int OFFSET = 100;
    private static final Dimension PREFERRED_SIZE = new Dimension(WIDTH, HEIGHT + OFFSET);
    
    /**
     * Method to iterate through the Tile[][] matrix and paint a 
     * rectangle of a specific color based on the entity it contains.
     * 
     * @param graphix - the drawn component.
     */
    public void paintComponent(Graphics graphix) {
        
        map.spawnPlayer();
        map.spawnTrain();
        
        for (int row = 0; row < map.getGrid().getGridWidth(); row++) {
            for (int column = 0; column < map.getGrid().getGridHeight(); column++) {
                Tile currentTile = map.getGrid().getTile(column, row);
                if (currentTile.hasRail()) {
                    graphix.setColor(Color.WHITE);
                } else if (currentTile.hasPlayer()) {
                    graphix.setColor(Color.PINK);
                } else if (currentTile.hasBridge()) {
                    graphix.setColor(Color.MAGENTA);
                } else if (currentTile.hasMine()) {
                    graphix.setColor(Color.ORANGE);
                } else if (currentTile.hasTree()) {
                    graphix.setColor(Color.RED);
                } else if (currentTile.hasTrain()) {
                    graphix.setColor(Color.BLACK);
                } else if (currentTile.isEmpty()) {
                    graphix.setColor(Color.GREEN);
                } else if (currentTile.hasLake()) {
                    graphix.setColor(Color.BLUE);
                }
                graphix.fillRect(row * CELL_SIZE, column * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }       
        }
        
    }
    
    /**
     * Width getter method.
     * 
     * @return width the width.
     */
    public int getWidth() {
        return WIDTH;
    }
    
    /**
     * Height getter method.
     * 
     * @return height the height.
     */
    public int getHeight() {
        return HEIGHT;
    }
    
    /**
     * New map getter method, for use by other gui classes.
     * 
     * @return map new map.
     */
    public Map getMap() {
        return map = new Map();
    }
    
    /**
     * This map getter method.
     * 
     * @return map this map.
     */
    public Map getThisMap() {
        return map;
    }
    
    /**
     * Getter method for the preferred size.
     * 
     * @return Dimension the dimension.
     */
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
    
    /**
     * Cell size getter method, for use by other frame classes in gui.
     * 
     * @return cell size.
     */
    public int getCellSize() {
        return CELL_SIZE;
    }
}