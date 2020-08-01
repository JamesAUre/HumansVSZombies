package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;
import game.Shotgun;
import game.SniperRifle;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
	public default void harvest(Location harvestlocation, GameMap map) {

	}

	public default int getArms(){
		return 2;

	}

	public default int getLegs(){
		return 2;
	}

	public default boolean lastTurnWalkStatus(){
		return false;
	}

	public default void setLastTurnWalk(boolean status){
	}

	public default int getAimCount(){
		return -1;
	}

	public default void setAimCount(int aimCount){

	}

	public default void setSniperTarget(Actor actor){

    }

    public default WeaponItem getSniper(){
		return null;
	}

	public default WeaponItem getShotgun(){
		return null;
	}


	public default boolean isHuman(){
		return false;
	}

	public default boolean isZombie(){
		return false;
	}

	public default boolean isPlayer(){
		return false;
	}

	public default void endGame(){};

	public default boolean getEndGameStatus(){
		return false;
	}

}
