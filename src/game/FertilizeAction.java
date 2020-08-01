package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
//import edu.monash.fit2099.engine.Ground;

/**
 * This action reduces the time it takes for a target unripe crop to ripen by 10 turns
 * 
 * @author James
 *
 */
public class FertilizeAction extends Action {

	/**
	 * The location of the target unripe crop to be fertilized
	 */
	protected Location fertilizelocation;
	
	/**
	 * Constructor.
	 * 
	 * @param location location of the target unripe crop to be fertilized
	 */
	public FertilizeAction(Location location) {
		this.fertilizelocation = location;
	}
	
	/**
	 * Increases tick counter in an unripe crop by 10
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		// check
		Crop crop = (Crop)fertilizelocation.getGround();
		crop.fertilize();
			
		return menuDescription(actor);
	}

	/**
	 * Returns a description of this fertilization suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Billy fertilizes the unripe crop"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilizes the unripe crop";
	}

}
