package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Farmers are a type of Human, but they are special in that when they are near farming
 * ground types such as dirt, unripecrop and ripecrop, they will perform certain actions on
 * them. Farmers grow food and drop them as fooditem for the player to pick up.
 * 
 * @author James
 *
 */
public class Farmer extends Human {
	
	private Behaviour[] behaviours = {
			new FarmBehaviour(),
			new WanderBehaviour()
	};
	
	/**
	 * Constructor
	 * 
	 * @param name the name of the farmer
	 */
	public Farmer(String name) {
		super(name, 'F', 50);

	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
	
	public void harvest(Location harvestlocation, GameMap map) {
		harvestlocation.addItem(new FoodItem("apple", 5));
	}

}
