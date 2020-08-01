package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A zombie leg, a child of CraftableWeaponItem, it can act as both a weapon and also
 * be crafted into a mace which is an even more powerful weapon!
 * 
 * @author James
 *
 */
public class ZombieLeg extends CraftableWeaponItem {
	
	/**
	 * Constructor.
	 */
	ZombieLeg() {
		super("Zombie leg", 'l', 10, "wacks");
	}
	
	/**
	 * As this can be crafted into a mace, this gives an instance of Mace
	 * 
	 * @return an instance of Mace
	 */
	public WeaponItem getCraftedWeapon() {
		 return new Mace();
	}

}
