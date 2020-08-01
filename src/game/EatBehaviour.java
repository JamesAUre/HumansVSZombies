package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.util.List;

/**
 *  This behaviour is used by Humans to eat which will allow them to regenerate health if their health is less than 50
 * @author Sak Yu Jin
 */
public class EatBehaviour implements Behaviour {
    @Override
    /**
     * Returns an eating action if the actor has less than 50 health
     * @param actor the actor performing the behaviour
     * @param map the map representing where the actor will perform the behaviour
     * @return an EatingAction() or null
     */
    public Action getAction(Actor actor, GameMap map){
        List<Item> groundItem= actor.getInventory();

        for (Item i: groundItem){
//            if (i.getClass()==FoodItem.class){
//                return new EatingAction((FoodItem)i);
//            }

            if (i.Eat()!=null){
                return i.Eat();
            }
        }

        return  null;
    }
}
