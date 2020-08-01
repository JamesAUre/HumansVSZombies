package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates an AttackAction if the current Actor is standing
 * next to an Actor that they can attack.
 * 
 * @author ram
 *
 */
public class AttackBehaviour implements Behaviour {
	private ZombieCapability attackableTeam;
	
	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this
	 * Behaviour is allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public AttackBehaviour(ZombieCapability attackableTeam) {
		this.attackableTeam = attackableTeam;
	}

	/**
	 * Returns an AttackAction that attacks an adjacent attackable Actor.
	 * 
	 * Actors are attackable if their ZombieCapability matches the 
	 * "undeadness status" set 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Random randomBite=new Random();
		int biteChance;
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);

		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				
				//if the actor is a zombie and have 0 arms the bite chance will be 100%
				biteChance=randomBite.nextInt(2);
//				if (actor.getClass()==Zombie.class){
//					Zombie zombie=(Zombie) actor;
//					if (zombie.getArms()==0){
//						biteChance=1;
//					}
//				}

				//check if the actor have any arms, if not the actor will always bite
				if (actor.getArms()==0){
					biteChance=1;
				}

				if (biteChance==0 || actor.getArms()==2){
					return new AttackAction(e.getDestination().getActor());
				}
				else{
					return new BiteAction(e.getDestination().getActor());
				}
			}
		}
		return null;
	}

}
