package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * This represents food as an item, food have all properties of items but they
 * are also edible and can replenish health.
 * 
 * @author Sak Yu Jin
 *
 */
public class FoodItem extends PortableItem {
    String foodName;
    private int healthRestore;

    /**
     * Constructor.
     * 
     * @param name the name of the food item
     * @param health the amount of health the food restores
     */
    FoodItem(String name,int health){
        super("food",'f');
        foodName=name;
        healthRestore=health;
    }
    
    /**
     * An accessor to get the amount of health the food restores
     * @return the amount of health the food restores
     */
    public int getRestore() {
    	return healthRestore;
    }

    public EatingAction Eat(){
        return new EatingAction(this);
    }

    public PickUpItemAction PickupFood(Item i){
        return new PickUpItemAction(i);
    }
}
