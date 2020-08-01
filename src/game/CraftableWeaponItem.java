package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * The template class for craftable items which are also weapons
 * 
 * @author Sak Yu Jin
 *
 */
public abstract class CraftableWeaponItem extends WeaponItem {

	/**
	 * Constructor.
	 * 
	 * @param name the name of the item
	 * @param displayName the character the item will display as
	 * @param damage the amount of damage it can inflict
	 * @param verb what it says when you damage another actor with this weapon
	 */
    CraftableWeaponItem(String name,char displayName, int damage, String verb){
        super(name,displayName,damage,verb);
    }
    
    /**
     * All craftable weapons need to have a crafted component
     * 
     * @return the items crafted version
     */
    public abstract WeaponItem getCraftedWeapon();

}
