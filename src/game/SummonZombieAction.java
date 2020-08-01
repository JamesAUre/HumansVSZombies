package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This action summons Zombies! Good for a party.
 * 
 * @author James
 *
 */
public class SummonZombieAction extends Action {
	private int zombiecount;
	
	/**
	 * Constructor
	 * @param zombies the number of zombies to summon
	 */
	SummonZombieAction(int zombies){
		this.zombiecount = zombies;
	}
	
	/**
	 * When this is executed, zombies will be placed randomly on the map
	 * 
	 * @param actor the actor to perform the action
	 * @param map the map containing the actor
	 * @see Action#execute(Actor, GameMap)
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int x;
		int y;
		for (int i = 0; i < zombiecount; i++) {
			do {
				x = (int) Math.floor(Math.random() * (map.getXRange().max() - map.getXRange().min()) + map.getXRange().min());
				y = (int) Math.floor(Math.random() * (map.getYRange().max() - map.getYRange().min()) + map.getYRange().min());
			} 
			while (map.at(x, y).containsAnActor());
			map.at(x,  y).addActor(new Zombie("Zombie Disciple"));	
		}
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
		return actor + " chants and " + zombiecount + " zombies appear!";
	}

}
