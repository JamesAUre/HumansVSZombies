package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A zombie arm, a child of CraftableWeaponItem, it can act as both a weapon and also
 * be crafted into a club which is an even more powerful weapon!
 * 
 * @author James
 *
 */
public class ZombieArm extends CraftableWeaponItem {
	
	/**
	 * Constructor.
	 */
	ZombieArm() {
		super("Zombie arm", '!', 10, "wacks");
	}
	
	/**
	 * As this can be crafted into a club, this gives an instance of Club
	 * 
	 * @return an instance of Club
	 */
	public WeaponItem getCraftedWeapon() {
		return new Club();
	}

}
