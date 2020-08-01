package game;

/**
 * ammo item for the sniper
 * @author Sak Yu Jin
 */
public class ShotgunAmmo extends PortableItem {
    int ammoValue=30;

    /**
     * constructor
     * initialize super class with default values
     */
    ShotgunAmmo(){
        super("Shotgun Ammo",'=');
    }

    /**
     * set the ammo value of this item
     * @param ammoValue the ammo value to be set to
     */
    @Override
    public void setSupplyAmmo(int ammoValue) {
        this.ammoValue = ammoValue;
    }

    /**
     * gets the current ammo value of item
     * @return ammo value
     */
    @Override
    public int getSupplyAmmo() {
        return ammoValue;
    }
}
