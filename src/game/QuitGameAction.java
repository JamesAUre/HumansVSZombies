package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


/**
 * Action for player to force quit the game
 * @author Sak Yu Jin
 */
public class QuitGameAction extends Action {

    /**
     * Constructor
     */
    QuitGameAction(){
    }

    /**
     * Shows the description of action in the menu
     * @param actor The actor performing the action.
     * @return the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Quit Game";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.endGame();
        return "Player quit the game.";
    }
}
