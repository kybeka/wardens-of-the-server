package gui;


import javax.swing.JFrame;

/**
 * The main GUI class.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 25.05.2022
*/
public class Main {
    /**
     * Constructor for Main.
     */
    private Main() {
        
    }
    
    /**
     * The main method of the gui.
     * 
     * @param args - the standard argument for the main method.
     */
    public static void main(String[] args) {
        JFrame frame = new MainMenuFrame();
        frame.setVisible(true);
    }   
}
