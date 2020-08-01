package game;

import edu.monash.fit2099.engine.*;

/**
 * Action for being able to turn craftable items into their corresponding crafted items
 * 
 * @author James
 *
 */
public class CraftingAction extends Action {
	
	/**
	 * The item that the actor will craft
	 */
	protected CraftableWeaponItem item;
	protected WeaponItem craftedItem;
	
	/**
	 * Constructor.
	 * 
	 * @param item the Item to craft
	 */
	public CraftingAction(CraftableWeaponItem item) {
		this.item = item;
		craftedItem = item.getCraftedWeapon();
	}

	public String test(){
		return item.getCraftedWeapon().toString();
	}
	/**
	 * Replace an item with its crafted version in the players inventory
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(item);
		craftedItem = item.getCraftedWeapon();
		actor.addItemToInventory(craftedItem);
		return menuDescription(actor);
	}

	/**
	 * Returns a description of this crafting suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Tom crafts zombie arm into zombie club"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " crafts "+ item.toString() + " into "+craftedItem.toString();
		//

	}
}
