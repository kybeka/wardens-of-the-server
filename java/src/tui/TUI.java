package tui;

import model.DigCommand;
import model.Map;
import model.PlaceBridgeCommand;
import model.PlaceRailCommand;
import model.PlayerMove;
import model.Tile;
import model.TrainMove;

import java.util.Locale;
import java.util.Scanner;
/**
 * The TUI class.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 25.05.2022
 */

public class TUI {
    private PlaceRailCommand placeRail = new PlaceRailCommand();
    private PlaceBridgeCommand placeBridge = new PlaceBridgeCommand();
    private PlayerMove playerMove = new PlayerMove();
    private TrainMove trainMove = new TrainMove();
    private DigCommand dig = new DigCommand();
    
    private Scanner scanner;
    private int counter;

    /**
     * Constructor for the TUI class.
     */
    public TUI() {
        this.scanner = new Scanner(System.in);
        this.counter = 1;
    }

    /**
     * Driver method for the tui.
     */
    public void run() {
        Map map = new Map();
        scanner.useLocale(Locale.US);
        map.spawnTrain();

        System.out.println("Press [1] to start a new game");
        System.out.println("Press [2] to go to the guide");
        System.out.println("Press [3] to quit");
        String answer1 = scanner.next();

        if ("1".equals(answer1)) {
            this.printMap(map);
        } else if ("2".equals(answer1)) {
            System.out.println("What is Build&Cross???");
            System.out.println("The aim of the game is to build railways for the train to arrive at the station successfully.");
            System.out.println("To do so, you will need to dig out resources from mines and trees.");
            System.out.println("In order to build a railway, 3 iron and 4 trees are required.");
            System.out.println("To build a bridge over lakes you will need 2 iron and 3 trees.");
            System.out.println("If a lake is placed where a railway should be, build a bridge first, then a railway on top.");
        } else if ("3".equals(answer1)) {
            System.exit(0);            
        }

        System.out.println("Press S to spawn");
        String answer2 = scanner.next();

        if ("s".equals(answer2)) {
            map.spawnPlayer();
            printMap(map);
        }

        while (true) {
            turn(map);
        }
    }

    /**
     * Method allowing the user to perform different steps in a single round.
     * 
     * @param map - The map of the game.
     */
    public void turn(Map map) {
        execute(map);
        
        counter = counter + 1;
        if (counter % 4 == 0) {
            trainMove.givePath(map);
        }
        this.gameDone(map);
    }

    /**
     * Method that executes various commands given by the player.
     * 
     * @param map - The map of the game.
     */
    public void execute(Map map) {
        final char command = askCommand();
        if (command == 'w') {
            playerMove.move(1, map);
            this.printMap(map);
        } else if (command == 'a') {
            playerMove.move(2, map);
            this.printMap(map);
        } else if (command == 's') {
            playerMove.move(3, map);
            this.printMap(map);
        } else if (command == 'd') {
            playerMove.move(0, map);
            this.printMap(map);
        } else if (command == 'r' 
            || command == 'e' 
            || command == 'q') {
            this.resourceInteract(map, command);
        } else if (command == 'x') {
            System.exit(0);
        }
    }

    /**
     * Method for interacting with resources;
     * either digging or placing them onto the map in the form of a rail or a bridge.
     * 
     * @param map - the map of the game.
     * @param command - the given command by the user.
     */
    public void resourceInteract(Map map, char command) {
        if (map.withinBounds(map.getPlayer().getDirection())) {
            if (command == 'e' ) {
                dig.execute(map);
                this.printMap(map);
            } else if (command == 'r') {
                placeRail.execute(map);
                this.printMap(map);
            } else if (command == 'q') {
                placeBridge.execute(map);
                this.printMap(map);
            }
        } else {
            this.printMap(map);
        }
    }

    /**
     * Method that checks whether the game is finished.
     * 
     * @param map - The map of the game.
     */
    public void gameDone(Map map) {
        if (map.getLast().trainReachedStation()) {
            System.out.println("TRAIN REACHED STATION! NICE ONE");
            System.exit(0);
        } else if (map.playerRanOver()) {
            System.out.println("GAME OVER! PLAYER HIT BY TRAIN");
            System.exit(0);
        } else if (map.getTrain().derailedStatus()) {
            System.out.println("GAME OVER! TRAIN DERAILED...");
            System.exit(0);
        }
    }

    /**
     * Method showing the command options.
     * 
     * @return the character of the command that the player selected.
     */
    public char askCommand() {
        System.out.println("Choose a command:");
        System.out.println("[w] Move up");
        System.out.println("[s] Move down");
        System.out.println("[a] Move left");
        System.out.println("[d] Move right");
        System.out.println("[e] Dig");
        System.out.println("[q] Build bridge");
        System.out.println("[r] Build rail");
        System.out.println("[x] Exit game");

        final String commandString = scanner.next();
        return commandString.charAt(0);
    }

    /**
     * Method used to show the map of the game.
     * 
     * @param map - The map of the current game.
     */
    public void printMap(Map map) {
        Tile[][] grid = map.getGrid().getGrid();
        
        for (Tile[] array : grid) {
            for (Tile tileInArray : array) {
                System.out.print(tileInArray.getCharacter());
            }
            System.out.println();
        }

        System.out.println("Wood Count: " + map.getPlayer().woodCount());
        System.out.println("Iron Count: " + map.getPlayer().ironCount());
    }
}
