package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action for eating food, if the actor has a FoodItem in their inventory, they may
 * eat it to replenish health.
 * 
 * @author James
 *
 */
public class EatingAction extends Action {
	
	/**
	 * The food the actor is eating
	 */
	private FoodItem food;
	
	/**
	 * Constructor.
	 * 
	 * @param food the FoodItem the actor has chosen to eat
	 */
	public EatingAction(FoodItem food) {
		this.food = food;
	}

	/**
	 * Once this is called, the food will be eaten and the players actors life will be
	 * replenished
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(food);
		actor.heal(food.getRestore());
		return menuDescription(actor);
	}

	/**
	 * Returns a description of eating the food suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Mary eats apple for 5 health!"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats " + food.toString() + " for " + food.getRestore() + " health!";
	}

}
