package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * The class for the actor to continue aiming which will cost 1 turn]
 * @author Sak Yu Jin
 */
public class ContinueAim extends Action {

    /**
     * constructor
     */
    ContinueAim(){

    }

    /**
     * Menu description for action
     * @param actor The actor performing the action.
     * @return menu description that will be displayed
     */
    @Override
    public String menuDescription(Actor actor) {

        return "Aim for another turn";
    }

    /**
     * Increases the aim counter of player by 1
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the string that will be displayed in the log after action is performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.setAimCount(actor.getAimCount()+1);

        String returnString="Player aims for another turn";
        return returnString;
    }

    /**
     * To determine if the player has aimed the last turn
     * @return true
     */
    @Override
    public boolean getAimLastTurn(){
        return true;
    }
}
