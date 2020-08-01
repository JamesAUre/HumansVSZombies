package game;

import edu.monash.fit2099.engine.GameMap;

/**
 * RespawnableActors are Actors that have the ability to be placed back onto a WorldOfMambo 
 * GameMap after being removed.
 * 
 * @author James
 *
 */
public abstract class RespawnableActor extends ZombieActor {

	/**
	 * Constructor.
	 * 
	 * @param name the name of the Actor
	 * @param displayChar symbol to display on the GameMap
	 * @param hitPoints how much damage it can take before dying
	 */
	public RespawnableActor(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.UNDEAD);
	}
	
	/**
	 * This controls the behaviour of when it's allowed to be placed back on the map
	 * if it returns true that indicates it can be placed on the map, false means it can't
	 * 
	 * @return
	 */
	public boolean shouldSpawn() {
		return false;	
	}

	/**
	 * This allows the Actor to be placed back on the GameMap. Functionality must be overriden
	 * in a child class as this method is only a template and does not do anything.
	 * 
	 * @param map the GameMap to put the actor on
	 */
	public void Spawn(GameMap map) {
		
	}

}
