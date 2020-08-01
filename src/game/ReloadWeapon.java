package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * The action class to reload weapons when they are empty or when not full
 */
public class ReloadWeapon extends Action {
    WeaponItem gun;
    PortableItem ammo;
    int neededAmount;
    int suppliableAmount;

    /**
     * Constructor
     * set ammo to input parameter
     * set gun to input parameter
     * @param gun The gun that needs to be reloaded
     * @param ammo the ammo item that will be used to reload the gun
     */
    ReloadWeapon(WeaponItem gun,PortableItem ammo){
        this.ammo=ammo;
        this.gun=gun;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reload "+gun.toString();
    }

    /**
     * check the amount of ammo in gun and replenishes if the ammo item has enough bullets
     * if the ammo item does not have enough bullets then just put all of the ammo bullets into the gun
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the string that will be displayed in the log
     */
    @Override
    public String execute(Actor actor, GameMap map){
        if (gun.toString()=="Shotgun"){
            neededAmount=20-gun.getGunAmmo();
        }
        else {
            neededAmount=10-gun.getGunAmmo();
            System.out.println(gun.getGunAmmo());
        }
        suppliableAmount=ammo.getSupplyAmmo()-neededAmount;
        if (suppliableAmount<0){
            gun.setGunAmmo(gun.getGunAmmo()+ammo.getSupplyAmmo());
            ammo.setSupplyAmmo(0);
            actor.removeItemFromInventory(ammo);
        }
        else{
            gun.setGunAmmo(gun.getGunAmmo()+neededAmount);
            ammo.setSupplyAmmo(ammo.getSupplyAmmo()-neededAmount);
        }
        return gun.toString() + " reloaded with " + neededAmount+" bullets" ;
    }
}
