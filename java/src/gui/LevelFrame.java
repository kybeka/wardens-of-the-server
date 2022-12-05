package gui;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The level frame, will show the level difficulty selection screen.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * 
 * @version 28/05/2022
 */

public class LevelFrame extends JFrame {
    
    private GameContent gameCont = new GameContent();
    
    private final Dimension preferredSize = this.getGameDimension();
    
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 200;
    
    private static final int OFFSET_EXPLAINER = 950;
    private static final int OFFSET_BUTTON3 = 400;
    
    private static final Dimension BUTTON_SIZE = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
    
    /**
     * Type of map being selected.
     */
    public LevelFrame() {
        super();
        setTitle("Build&Cross: the Game");
        setLayout(null);
        setSize(preferredSize);
        String font = "Arial";
        int fontSize = 20;
        
        JLabel explainer = new JLabel();
        explainer.setFont(new Font(font, Font.BOLD, fontSize));
        explainer.setText("Read the rules, then press ready!");
        explainer.setBounds(gameCont.getWidth() / 4, gameCont.getHeight() - OFFSET_EXPLAINER, gameCont.getWidth() / 2, gameCont.getHeight() / 6);
        add(explainer);
        
        
        JLabel label = new JLabel();
        label.setText("<html>" + "The rules are simple:" + "<br />" + 
        "You control the PINK tile. You need to move around to gather resources-" + "<br />" +
        "wood (ORANGE) and iron (RED)." + "The train (BLACK) needs to reach the end of the railway (WHITE)" +
        "so you need to build the missing rails.  So, if train moves off rails or if it runs you over, you lose. " + "<br />" +
        "You may need to work around lakes (BLUE) by building bridges (MAGENTA) over them, and, if needed, rails over bridges." + 
        "Remember, you must memorise how many resources you have, starting at 0. And finally, you can only interact with " + 
        "the tiles you are facing. So your direction is always the one you just moved in." + "</html>");
        label.setFont(new Font(font, Font.BOLD, fontSize));
        label.setBounds(300, 100, 500, 500);
        add(label);
        
        
        JButton readyButton = new JButton();
        add(readyButton);
        readyButton.setPreferredSize(BUTTON_SIZE);
        readyButton.setFont(new Font(font, Font.BOLD, fontSize));
        readyButton.setText("Ready");
        readyButton.setBounds(gameCont.getWidth() / 3, gameCont.getHeight() - OFFSET_BUTTON3, gameCont.getWidth() / 3, gameCont.getHeight() / 6);
        
        setLocationRelativeTo(null);
        pack();
        
        readyButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                dispose();
                GameFrame gameFrame = new GameFrame();
                gameFrame.setVisible(true);
            }
        });
        
    }
    
    /**
     * Game content getter method, to obtain all the relevant data.
     * 
     * @return game content.
     */
    public GameContent getGameContent() {
        return gameCont;
    }
    
    /**
     * Dimension setter method.
     * 
     * @return dimension the dimensipn.
     */
    public Dimension getGameDimension() {
        Dimension hold = gameCont.getPreferredSize();
        return hold;
    }
    
    /**
     * Preferred size / dimension getter method.
     * 
     * @return dimension the dimension.
     */
    public Dimension getPreferredSize() {
        return preferredSize;
    }
    
}