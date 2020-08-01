package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Allows you to summon a various number of zombies
 * 
 * @author James
 *
 */
public class SummonZombieBehaviour implements Behaviour {
	private int zombies;
	
	/**
	 * Constructor.
	 * 
	 * @param zombies the number of zombies to summon
	 */
	SummonZombieBehaviour(int zombies){
		this.zombies = zombies;
	}
	/**
	 * Returns a SummonZombieAction which will summon a various number of zombies depending
	 * on input
	 * 
	 * @see Behaviour#getAction(Actor, GameMap)
	 * @param actor the actor performing the action
	 * @map the map the actor is on
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		return new SummonZombieAction(this.zombies);

	}

}
