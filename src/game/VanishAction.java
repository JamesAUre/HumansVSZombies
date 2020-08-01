package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action that allows the actor to remove itself from the map
 * 
 * @author James
 *
 */
public class VanishAction extends Action{
	
	private Actor actortovanish;
	/**
	 * Constructor
	 * 
	 * @param actortovanish the Actor to be removed from the map
	 */
	public VanishAction(Actor actortovanish) {
		this.actortovanish = actortovanish;
	}
	
	/**
	 * Allow the actor to remove itself from the map
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actortovanish);
		return menuDescription(actortovanish);
	}

	/**
	 * Returns a description of the actor speaking to display in the menu.
	 *
	 * @see Action#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Vincent says wow that's a lot of zombies!"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " vanishes!";
	}

}
