package tui;

/**
 * The main TUI class.
 *
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
*/
public class Main {
    /** 
     * Constructor for Main.
     */
    private Main() {
        
    }
    
    /**
     * The driver method for TUI.
     * 
     * @param args - standard argument for the main.
     */
    public static void main(String[] args) {
        System.out.println("tui.Main");
        TUI tui = new TUI();
        tui.run();
    }
}