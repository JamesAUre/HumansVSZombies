package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * Allows an actor to move to any location in one turn by
 * performing an action
 * 
 * @author James
 *
 */
public class DriveAction extends Action {
	
	private Location moveToLocation;
	private String direction;
	private Location newloc;
	
	/**
	 * Constructor.
	 * 
	 * @param newloc the location the actor will be placed on next turn
	 */
	public DriveAction(Location newloc) {
		this.newloc = newloc;
	}
	
	/**
	 * When this is executed, actor will be moved to the location passed
	 * in the constructor. 
	 * 
	 * @param actor the actor to perform the action
	 * @param map the map containing the actor
	 * @see Action#execute(Actor, GameMap)
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		//map.moveActor(actor, moveToLocation);
		map.moveActor(actor, newloc);
		return menuDescription(actor);
	}

	/**
	 * Returns a description of the action being performed
	 * 
	 * @param actor the actor performing the action
	 * @see Action#menuDescription(Actor)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " drives away!";
	}

}
