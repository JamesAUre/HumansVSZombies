package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * 
 * This action sows dirt which will replace it with an unripe crop
 * 
 * @author James
 *
 */
public class SowAction extends Action {
	
	/**
	 * The location of the target dirt to be sown
	 */
	protected Location sowlocation;
	
	/**
	 * Constructor.
	 * 
	 * @param location the location of the target dirt to be sown
	 */
	//in behaviour, assume location can be any tile the actor is next to, when it has
	//found a dirt tile, pass the location of it into sowaction 
	public SowAction(Location location) {
		this.sowlocation = location;
	}
	
	/**
	 * Replace the Dirt with an unripe crop at the target location
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		sowlocation.setGround(new Crop());
		return menuDescription(actor);
	}

	/**
	 * Returns a description of this sow suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Billy sows the dirt"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sows dirt";
	}

}
