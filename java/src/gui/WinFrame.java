package gui;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The frame displayed when the player wins.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 28/05/2022
 */
public class WinFrame extends JFrame {
    
    private GameContent gameCont = new GameContent();
    
    private final Dimension preferredSize = this.getGameDimension();
    private static final int OFFSET_EXPLAINER = 850;
    private static final int OFFSET_BUTTON1 = 700;
    private static final int OFFSET_BUTTON2 = 500;
    
    /**
     * The frame displayed when the player wins.
     */
    public WinFrame() {
        super();
        setTitle("Build&Cross: Win!");
        setLayout(null);
        setSize(preferredSize);
        setLocationRelativeTo(null);
        
        JLabel winText = new JLabel();
        winText.setFont(new Font("Arial", Font.BOLD, 20));
        winText.setText("Train reached station. Congrats!");
        winText.setBounds(gameCont.getWidth() / 3, gameCont.getHeight() - OFFSET_EXPLAINER, gameCont.getWidth() / 2, gameCont.getHeight() / 6);
        add(winText);
        
        JButton menuButton = new JButton();
        menuButton.setText("Back to Main Menu");
        menuButton.setFont(new Font("Arial", Font.BOLD, 20));
        menuButton.setBounds(gameCont.getWidth() / 3, gameCont.getHeight() - OFFSET_BUTTON1, gameCont.getWidth() / 3, gameCont.getHeight() / 6);
        add(menuButton);
        
        JButton restartButton = new JButton();
        restartButton.setText("Play a new game");
        restartButton.setFont(new Font("Arial", Font.BOLD, 20));
        restartButton.setBounds(gameCont.getWidth() / 3, gameCont.getHeight() - OFFSET_BUTTON2, gameCont.getWidth()  /  3, gameCont.getHeight() / 6);
        add(restartButton);
        
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                dispose();
                MainMenuFrame menu = new MainMenuFrame();
                menu.setVisible(true);
            }
        });
        
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                dispose();
                GameFrame newGame = new GameFrame();
                newGame.setVisible(true);
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
