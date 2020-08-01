package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	/**
	 * Constructor.
	 */
	public Dirt() {
		super('.');
		addCapability(FarmerCapabilities.DIRT);
	}
	
	/**
	 * Gets actions allowed to be performed
	 * 
	 * @see Ground#allowableActions(Actor, Location, String)
	 * @param actor the actor performing the action on it
	 * @param location the location to target where the change will happen
	 * @param direction the direction of the actor
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		
		if(actor.hasCapability(FarmerCapabilities.FARMER)) {
			actions.add(new SowAction(location));
		}
		
		return actions;
	}
}
