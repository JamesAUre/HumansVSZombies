package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Allows actors to speak, they can say anything you'd like! This will count as using
 * their turn.
 * 
 * @author James
 *
 */
public class SayAction extends Action {
	
	private String speach;
	
	/**
	 * Constructor.
	 * 
	 * @param tosay the string which will contain what the actor will say
	 */
	public SayAction(String tosay) {
		speach = tosay;
	}
	
	/**
	 * Allow the target actor to say something
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * Returns a description of the actor speaking to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Vincent says wow that's a lot of zombies!"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " says " + speach;
	}

}
