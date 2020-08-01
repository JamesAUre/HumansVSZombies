package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * 
 * This action harvests a ripe crop, essentially replacing it with Dirt and spawning
 * food as an item at the location of the crop
 * 
 * @author James
 *
 */
public class HarvestAction extends Action {
	
	/**
	 * The location of the target RipeCrop to be harvested
	 */
	protected Location harvestlocation;
	
	/**
	 * Constructor.
	 * 
	 * @param location location of the target RipeCrop to be harvested
	 */
	public HarvestAction(Location location) {
		this.harvestlocation = location;
	}
	
	/**
	 * Replace the ripe crop with dirt and drop the food at the target location
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		harvestlocation.setGround(new Dirt());

		actor.harvest(harvestlocation, map);
		return menuDescription(actor);
	}

	/**
	 * Returns a description of this harvest suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Billy harvests the crop!"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvests the crop!";
	}

}
