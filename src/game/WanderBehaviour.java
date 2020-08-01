package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Allows an Actor to wander around at random.
 * 
 * @author ram
 *
 */
public class WanderBehaviour implements Behaviour {

	private boolean zombieWalk;
	private Random random = new Random();


	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {

		zombieWalk=false;
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

			if (actor.getLegs()==0){
				continue;
			}

			else if (actor.getLegs()==1 && !zombieWalk){
				if(actor.lastTurnWalkStatus()==false) {
					actor.setLastTurnWalk(true);
					if (destination.canActorEnter(actor)) {
						actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
					}

					zombieWalk=true;
				}
				else {
					actor.setLastTurnWalk(false);
					continue;
				}
			}

			else{
				if (destination.canActorEnter(actor)) {
					actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
				}
			}


        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

}
