package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * The SniperRifle class which extends WeaponItem
 * @author Sak Yu Jin
 */
public class SniperRifle extends WeaponItem {
    private int ammo=10;

    /**
     * Constructor for the class initialized with default values
     */
    public SniperRifle() {
        super("Sniper", '}', 45, "pew");
    }

    /**
     * The method to set the value of ammo in this gun
     * @param ammo the value that will be set to
     */
    @Override
    public void setGunAmmo(int ammo) {
        this.ammo = ammo;
    }

    /**
     * The method to return the gun ammo for this gun
     * @return ammo value
     */
    @Override
    public int getGunAmmo() {
        return ammo;
    }

    /**
     * Function for gun to return a reload action
     * @param actor actor holding the gun
     * @param gun this gun, will be used to be passed into the reload action
     * @return reload action if it is a valid action or null otherwise
     */
    @Override
    public Action getReloadAction(Actor actor, WeaponItem gun){
        List<Item> playerItems= actor.getInventory();
        for (int i = 0; i < playerItems.size(); i++){
            if (playerItems.get(i).toString()=="Sniper Ammo" && gun.getGunAmmo()<10){

                return new ReloadWeapon(gun,(PortableItem) playerItems.get(i));
            }
        }

        return null;
    }

    /**
     * Check if actor has aimed previously, if not then return a new sniper function
     * if actor has previously aimed they can now fire or aim for another turn
     * @param actor Actor holding the sniper
     * @return SniperAction()
     */
    @Override
    public  Action getSniperActions(Actor actor){
        Actions returnVal= new Actions();
        if (actor.getAimCount()==0 && this.getGunAmmo()!=0){

            return new SniperAction();
        }
        return null;
    }

}
