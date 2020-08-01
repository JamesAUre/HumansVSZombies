package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.*;
import game.EatingAction;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
    public default EatingAction Eat(){
        return null;
    }

    public default PickUpItemAction PickupFood(Item i){
        return null;
    }

    public default void setGunAmmo(int ammoValue){

    }

    public default int getGunAmmo(){
        return -1;
    }

    public default void setSupplyAmmo(int ammoValue){

    }

    public default int getSupplyAmmo(){
        return -1;
    }

    public default Action getReloadAction(Actor actor, WeaponItem gun) {
        return null;
    }

    public default Actions getShotgunActions(Actor actor,GameMap map){
        return null;
    }

    public default Action getSniperActions(Actor actor){
        return null;
    }
}
