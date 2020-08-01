package game;

import edu.monash.fit2099.engine.*;

import java.util.List;
import java.util.Random;

/**
 * This behaviour is used by farmers to decide what types of farming related actions
 * they can perform (sowing, fertilizing and harvesting). It will check what types of
 * ground surround the farmer.
 * 
 * @author Sak Yu Jin
 *
 */
public class FarmBehaviour implements Behaviour {
	
	/**
	 * Returns either SowAction, FertilizeAction or HarvestAction depending on
	 * the type of ground surrounding the actor.
	 * 
	 * @param actor the actor performing the behaviour
	 * @param map the map representing where the actor will perform the behaviour
	 * @return Either a SowAction, FertilizeAction, HarvestAction instance or null
	 */
    @Override
    public Action getAction(Actor actor, GameMap map) {
    	if (actor.hasCapability(FarmerCapabilities.FARMER)) {
	        Crop currentCrop;
	        Location locationOfActor=map.locationOf(actor);
	
	        Random r = new Random();
	        int sowChance= r.nextInt(99);
	        int sowPosition=r.nextInt(4);
	        int x=locationOfActor.x();
	        int y=locationOfActor.y();
	
	        List<Exit> allExits=locationOfActor.getExits();
	
	        Actions actions = locationOfActor.getGround().allowableActions(actor, locationOfActor, "test");
	        
	        //return actions.get(0);
	        if (getGroundChar(locationOfActor)=='*' ){
	            currentCrop=(Crop)locationOfActor.getGround();
	            if (currentCrop.hasRipened()){
	                return new HarvestAction(locationOfActor);
	            }
	        }
	
	        for (Exit e: allExits){
	            if (getGroundChar(e.getDestination())=='*' ){
	                currentCrop=(Crop)e.getDestination().getGround();
	                if (currentCrop.hasRipened()){
	                    return new HarvestAction(e.getDestination());
	                }
	                else{
	                    return new FertilizeAction(e.getDestination());
	                }
	            }
	            else if (sowChance<33 && e.getDestination().getGround().getDisplayChar()=='.'){
	                return new SowAction(e.getDestination());
	            }
	        }
	        
    	}

        return null;
    }

    /**
     * Gets the ground at a target location.
     * 
     * @param location the target location to check
     * @return the character representing the ground at the location.
     */
    public char getGroundChar(Location location){
        return location.getGround().getDisplayChar();
    }

}
