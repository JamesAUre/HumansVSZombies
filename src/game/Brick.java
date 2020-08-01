package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents a brick
 * @author James
 *
 */
public class Brick extends Ground {
	/**
	 * Constructor.
	 */
	public Brick() {
		super('=');
	}
	
	/**
	 * 
	 * This is a wall, so actors can't pass this
	 * 
	 * @param actor the actor to try enter this ground
	 * @see Ground#canActorEnter(Actor)
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
}
