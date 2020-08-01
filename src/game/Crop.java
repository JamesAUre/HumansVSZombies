package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing a RipeCrop, a terrain type ready to be harvested for food
 * 
 * @author James
 *
 */
public class Crop extends Ground {
	private int ripenCounter;
	private boolean isRipe;
	
	/**
	 * Constructor.
	 */
	public Crop() {
		super('*');
		addCapability(FarmerCapabilities.UNRIPE);
		ripenCounter = 0;
	}
	
	/**
	 * As time goes on, it will ripen. For each turn, increase its counter and
	 * when it reaches 20 it'll be ready to be a RipeCrop!
	 * 
	 * @see Ground#tick(Location)
	 * @param the location of the unripecrop
	 */
	@Override
	public void tick(Location location) {
		if(hasCapability(FarmerCapabilities.UNRIPE)) {
			ripenCounter++;
			
			if (ripenCounter >= 20) {
				ripen();
			}
		}
	}
	
	private void ripen() {
		isRipe = true;
		addCapability(FarmerCapabilities.RIPE);
		removeCapability(FarmerCapabilities.UNRIPE);
	}
	
	/**
	 * Checks to see if the crop is ripe. 
	 * 
	 * @return True if it's ripe, false otherwise.
	 */
	public boolean hasRipened() {
		return isRipe;
	}
	
	/**
	 * Fertilize the crop if it's unripe, reducing its time to ripen by 10 turns.
	 */
	public void fertilize() {
		if(hasCapability(FarmerCapabilities.UNRIPE)) {
			ripenCounter = ripenCounter + 10;
			if (ripenCounter >= 20) {
				ripen();
			}
		}
	}
	
	/**
	 * Gets actions allowed to be performed depending on its state (ripe or unripe)
	 * 
	 * @see Ground#allowableActions(Actor, Location, String)
	 * @param actor the actor performing the action on it
	 * @param location the location to target where the change will happen
	 * @param direction the direction of the actor
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if(hasCapability(FarmerCapabilities.UNRIPE)) {
			actions.add(new FertilizeAction(location));
		}
		else if(hasCapability(FarmerCapabilities.RIPE)) {
			actions.add(new HarvestAction(location));
		}
		
		return actions;
	}
	
}

