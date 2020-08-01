package game;

/**
 * ammo item for the sniper
 * @author Sak Yu Jin
 */
public class SniperAmmo extends PortableItem {
    private int ammoValue;

    /**
     * constructor
     * initialize super class with default values
     */
    SniperAmmo(){
        super("Sniper Ammo",'"');
        ammoValue=20;
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
