package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Action for biting other actors, no weapon is used for this attack and has a lower chance
 * of hitting compared to a normal base attack. However inflicts more damage than a normal
 * attack with no weapon.
 * 
 * @author James
 *
 */
public class BiteAction extends Action {
	
	/**
	 * The actor that is to be attacked
	 */
	protected Actor target;
	
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	
	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public BiteAction(Actor target) {
		this.target = target;
	}
	
	/**
	 * Inflict damage towards the target, or miss inflicting no damage
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	public String execute(Actor actor, GameMap map) {
		
		Weapon weapon = new IntrinsicWeapon(10, "bites");
		
		if (rand.nextInt(3) != 0) {
			return actor + " misses " + target + ".";
		}
		
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (!target.isConscious()) {
			Item corpse = new PortableItem("dead " + target, '%');
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * Returns a description of this bite attack suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Krug bites Tom"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " bites " + target;
	}

}
