package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * The shotgun class which extends the WeaponItem
 * @author Sak Yu Jin
 */
public class Shotgun extends WeaponItem {
    private int ammo=20;

    /**
     * Constructor of the shotgun
     * initialize the super class with default values
     */
    public Shotgun() {
        super("Shotgun", ']', 35, "bang");
    }

    /**
     * Change the ammo value in the gun
     * @param ammo the value to be changed to
     */
    @Override
    public void setGunAmmo(int ammo) {
        this.ammo = ammo;
    }

    /**
     * Getter for ammo value
     * @return the ammo value
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
            if (playerItems.get(i).toString()=="Shotgun Ammo" && gun.getGunAmmo()<20){
                return new ReloadWeapon(gun,(PortableItem) playerItems.get(i));
            }
        }

        return null;
    }

    /**
     * Get the possible shotgun actions to shoot in the available direction
     * @param actor Actor that will be shooting the gun
     * @param map map where the Actor is on
     * @return the ShotgunActions in different directions
     */
    @Override
    public Actions getShotgunActions(Actor actor,GameMap map){
        Actions returnVal=new Actions();
        List<Exit>playerExits=map.locationOf(actor).getExits();
        for (int j = 0; j < playerExits.size(); j++){
            Location possibleExit= playerExits.get(j).getDestination();
            String exitName=playerExits.get(j).getName();
            if (this.getGunAmmo()!=0){
                returnVal.add(new ShotgunAction(possibleExit,exitName));
            }
        }

        return returnVal;
    }
}
