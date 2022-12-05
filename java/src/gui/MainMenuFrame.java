package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The main menu class, will contain the information
 * about all other frames and call them as necessary.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * 
 * @version 28/05/2022
 */
public class MainMenuFrame extends JFrame {
    //private GameContent game = new GameContent();
    
    private final Dimension preferredSize = this.getGameDimension();
    private static final Dimension BUTTON_SIZE = new Dimension(100, 200);
    
    /**
     * Default constructor for the main menu class.
     */
    public MainMenuFrame() {
        super();
        
        setTitle("Build&Cross: Main Menu");
        setLayout(new BorderLayout());
        setSize(preferredSize);
        
        JButton startButton = new JButton();
        
        add(startButton, BorderLayout.NORTH);
        startButton.setPreferredSize(BUTTON_SIZE);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setText("start game");
        
        
        JPanel centerPanel = new JPanel();
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to Build&Cross!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        centerPanel.add(welcomeLabel);
        add(centerPanel);
        
        JButton quitButton = new JButton();
        add(quitButton, BorderLayout.SOUTH);
        quitButton.setPreferredSize(BUTTON_SIZE);
        quitButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitButton.setText("quit");
        
        pack();
        setLocationRelativeTo(null);
    
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                dispose();
                LevelFrame levelScreen = new LevelFrame();  
                levelScreen.setVisible(true);
            }
        });
        
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                    System.out.println("quit");
                    System.exit(0);
            }
        });
        
    }
    
    /**
     * Getter method for the dimension.
     * 
     * @return the dimension.
     */
    public Dimension getGameDimension() {
        GameContent game = new GameContent();
        Dimension hold = game.getPreferredSize();
        return hold;
    }
    
    /**
     * Method to set preferred dimension of the frame.
     * 
     * @return dimension the dimension of the frame.
     */
    public Dimension getPreferredSize() {
        return preferredSize;
    }
    
}