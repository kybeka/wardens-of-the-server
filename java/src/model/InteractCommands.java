package model;

/**
 * The super class for every interaction command.
 * 
 * @author arseni.loika@usi.ch
 * @author delara.lomen@usi.ch
 * @version 29.05.2022
 */
public abstract class InteractCommands {
    
    /**
     * Execute method to be implemented in each subclass.
     * 
     * @param map the map where interaction is happening.
     */
    public abstract void execute(Map map);
}