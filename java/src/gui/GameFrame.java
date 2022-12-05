package gui;


import model.DigCommand;
import model.Map;
import model.PlaceBridgeCommand;
import model.PlaceRailCommand;
import model.PlayerMove;
import model.TrainMove;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * GameFrame class,
 * contains all information about the game.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 28/05/2022
 */
public class GameFrame extends JFrame {
    private final Dimension preferredSize = this.getGameDimension();
    
    private PlayerMove playerMove = new PlayerMove();
    private PlaceBridgeCommand placeBridge = new PlaceBridgeCommand();
    private PlaceRailCommand placeRail = new PlaceRailCommand();
    private DigCommand dig = new DigCommand();
    private TrainMove trainMove = new TrainMove();
    
    private GameContent gameCont = new GameContent();

    /**
     * GameFrame constructor,
     * contains all the relevant action listeners to update the frame as the game is played.
     */
    public GameFrame() {
        super();
        String font = "Arial";
        
        final Map map = gameCont.getMap();
        setTitle("Build&Cross: Game");
        setLayout(new BorderLayout());
        setSize(preferredSize);

        JLabel controls = new JLabel();
        controls.setText("Use [wasd] to move | [e] to dig | [r] to build a rail (4 wood, 3 iron) | [q] to build a bridge (3 wood, 2 iron)");
        controls.setBounds(20, 630, 1000, 800);
        controls.setFont(new Font(font, Font.BOLD, 16));
        add(controls);
        
        setLocationRelativeTo(null);
        add(gameCont);
        
        addKeyListener(new KeyAdapter() {
                /* int counter the counter */
                int counter = 0;
               
                public void keyPressed(final KeyEvent event) {
                    //int counter = 1;
                    if (event.getKeyCode() == KeyEvent.VK_W) {
                        playerMove.move(1, map);
                        counter++;
                        repaint();
                    } else if (event.getKeyCode() == KeyEvent.VK_A) {
                        playerMove.move(2, map);
                        counter++;
                        repaint();
                    } else if (event.getKeyCode() == KeyEvent.VK_S) {
                        playerMove.move(3, map);
                        counter++;
                        repaint();
                    } else if (event.getKeyCode() == KeyEvent.VK_D) {
                        playerMove.move(0, map);
                        counter++;
                        repaint();
                    } else if (event.getKeyCode() == KeyEvent.VK_Q) {
                        placeBridge.execute(map);
                        counter++;
                        repaint();
                    } else if (event.getKeyCode() == KeyEvent.VK_R) {
                        placeRail.execute(map);
                        counter++;
                        repaint();
                    } else if (event.getKeyCode() == KeyEvent.VK_E) {
                        dig.execute(map);
                        counter++;
                        repaint();
                    }

                    if (counter % 3 == 0) {
                        trainMove.move(3, map);
                    }

                    if (map.getGrid().getTile(map.getTrain().getYCoord(), map.getTrain().getXCoord()).trainReachedStation()) {
                        dispose();
                        WinFrame win = new WinFrame();
                        win.setVisible(true);
                    }

                    if (map.playerRanOver()) {
                        dispose();
                        RanOverLoseFrame ranOver = new RanOverLoseFrame();
                        ranOver.setVisible(true);
                    } else if (map.getTrain().derailedStatus()) {
                        dispose();
                        DerailLoseFrame derailed = new DerailLoseFrame();
                        derailed.setVisible(true);
                    }

                }
            });
            
        
    }

    /**
     * Method to obtain Dimension from the GameContent class.
     * 
     * @return dimension the dimension.
     */
    public Dimension getGameDimension() {
        GameContent gameCont = new GameContent();
        Dimension hold = gameCont.getPreferredSize();
        return hold;
    }

}