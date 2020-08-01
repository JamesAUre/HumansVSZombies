package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.List;
import java.util.Random;

/**
 * This is a portable item that represents a dead human body, after 8 turns this
 * will turn into a zombie
 * 
 * @author Sak Yu Jin
 *
 */
public class CorpseItem extends PortableItem {
    int reviveTimer;


    /**
     * Constructor.
     * 
     * @param name the name of the item
     * @param characterDisplay the icon to display the item on the map
     */
    CorpseItem(String name,char characterDisplay){
        super(name,characterDisplay);
        Random r = new Random();
        reviveTimer=r.nextInt((10 - 5) + 1) + 5 ;
    }
    
    /**
     * This needs to be called after each turn, and will act as a timer. After this is called
     * 8 times, this will turn the item into a zombie.
     * 
     * @see Item#tick(Location)
     * @param location the location of the corpse
     * 
     */
    @Override
    public void tick(Location location){
        List<Item> allItems=location.getItems();
        if (reviveTimer==0){
            for (int i = 0; i < allItems.size(); i++){
                if (allItems.get(i).getClass()==CorpseItem.class && location.getActor()==null){
                    location.removeItem(allItems.get(i));
                    location.addActor(new Zombie("zombie"));
                }
            }
        }

        else {
            reviveTimer-=1;
        }
    }
}

