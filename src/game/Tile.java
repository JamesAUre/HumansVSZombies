package game;

import edu.monash.fit2099.engine.Ground;

/**
 * Ground that the actor can walk around in, but cannot perform any farming actions in.
 * 
 * @author James
 *
 */
public class Tile extends Ground {
	/**
	 * Constructor.
	 */
	public Tile() {
		super(',');
	}
}
