package game;

import edu.monash.fit2099.engine.*;
import java.util.List;

/**
 * Checks for whether there is a food under the Actor, if there is pick it up.
 * 
 * @author Sak Yu Jin
 *
 */
public class PickupFoodBehaviour implements Behaviour {
	
	/**
	 * Goes through all the items under the actor and picks up a weapon if it finds one,
	 * otherwise null
	 * 
	 * @param actor the actor performing the behaviour
	 * @param map the map representing where the actor will perform the behaviour
	 * @return a PickUpItemAction instance or null if no weapon found
	 */
    @Override
    public Action getAction(Actor actor, GameMap map){
        List<Item> actorLocationItems=map.locationOf(actor).getItems();

        for (Item i: actorLocationItems){
//            if(i.getClass()==FoodItem.class){
//                return new PickUpItemAction(i);
//            }
            if (i.PickupFood(i)!=null){
                return i.PickupFood(i);
            }
        }
        return null;
    }
}
