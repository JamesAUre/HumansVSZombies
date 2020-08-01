package game;

import java.util.Random;
import java.util.List;
import java.io.*;
import edu.monash.fit2099.engine.*;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */


public class Zombie extends ZombieActor {
	private int arms;
	private int legs;
	private boolean haslostlimb;
	protected Random rand = new Random();
	private boolean lastTurnWalk=false;

	private Behaviour[] behaviours = {
			new SayBehaviour(),
			new PickupWeaponBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()

	};

	/**
	 * Constructor.
	 * 
	 * @param name the name of the zombie
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		arms = 2;
		legs = 2;
		haslostlimb = false;
	}
	
	/**
	 * arm accessor
	 * 
	 * @return the number of arms the zombie has
	 */
	public int getArms() {
		return arms;
	}
	
	/**
	 * leg accessor
	 * @return the number of legs the zombie has
	 */
	public int getLegs() {
		return legs;
	}
	
	/**
	 * Damages the zombie, reducing its health
	 * 
	 * @param points the amount of damage inflicted upon the zombie
	 */
	@Override
	public void hurt(int points) {
		hitPoints -= points;
		if (rand.nextInt(4) == 0) {
			haslostlimb = true;
		}
	}
	
	private void dropLimb(GameMap map) {
		if(rand.nextBoolean()) {
			dropArm(map);
		}
		else dropLeg(map);
	}
	
	private void dropArm(GameMap map) {
		if (arms > 0) {
			map.locationOf(this).addItem(new ZombieArm());
			
			if ((arms == 2 && rand.nextBoolean()) || arms == 1) {
				List<Item> invent = getInventory();
				for(Item items : invent) {
					if (items.asWeapon() != null) {
						map.locationOf(this).addItem(items);
						removeItemFromInventory(items);
						break;
					}
				}
				
			}
			arms--;
		}
	}
	
	private void dropLeg(GameMap map) {
		if (legs > 0) {
			
			map.locationOf(this).addItem(new ZombieLeg());
			legs --;
		}
	}
	
	/**
	 * Gets the basic zombie attack
	 * 
	 * @return a punch attack, which is an instance of IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		if(haslostlimb) {
			dropLimb(map);
			haslostlimb = false;
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);

			if (action != null){

				// This is for leg impairment, affecting the frequency of walking
//				if (behaviour instanceof WanderBehaviour){
////					if (this.legs==0){
////						continue;
////					}
////
////					if (this.legs==1){
////						if(!lastTurnWalk) {
////							lastTurnWalk=true;
////							return action;
////						}
////						else {
////							lastTurnWalk=false;
////							continue;
////						}
////					}
////					return action;
////				}
				
				// This is for arm impairment, you can't pick up a weapon with no arms!
//				else if(behaviour instanceof PickupWeaponBehaviour) {
//					if (this.arms == 0) {
//						continue;
//					}
//				}
				
				return action;
			}
		}
		return new DoNothingAction();	
	}


	public boolean lastTurnWalkStatus(){
		return lastTurnWalk;
	}

	public void setLastTurnWalk(boolean Status){
		lastTurnWalk=Status;
	}

	@Override
	public boolean isZombie(){
		return true;
	}
}