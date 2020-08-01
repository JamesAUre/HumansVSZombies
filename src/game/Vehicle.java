package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A ground to allow an Actor nearby to perform a DriveAction, useful if you want to get anywhere
 * in the world in 1 turn.
 * 
 * @author James
 *
 */
public class Vehicle extends Ground {
	private Location driveto;
	
	/**
	 * Constructor
	 * 
	 * @param driveto the location this vehicle will use DriveAction to move the player to
	 */
	public Vehicle(Location driveto) {
		super('>');
		this.driveto = driveto;
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
		actions.add(new DriveAction(driveto));
		
		return actions;
	}

}
