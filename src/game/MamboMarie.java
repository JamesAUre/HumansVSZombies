package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Mamboo Marie is a RespawnableActor that has a 5% chance of respawning per turn while vanished,
 * she will vanish after 30 turns of being revealed and will summon zombies every 10 turns while appeared.
 * Mamboo Marie will always appear on the map the player is in when coming out of a vanish.
 * 
 * @author James 
 *
 */
public class MamboMarie extends RespawnableActor {
	private Random rand = new Random();
	private int tick = 0;
	private boolean invisible;
	private Behaviour[] behaviours = {
		new WanderBehaviour()
	};

	/**
	 * Constructor.
	 * 
	 * @param name the name of Mambo Marie
	 */
	public MamboMarie(String name) {
		super(name, 'M', 100);
		this.tick = 0;
		this.invisible = true;
	}
	

	/**
	 * Mambo Marie will return a VanishAction every 30 turns, SummonZombieAction every 10 turns
	 * otherwise she will try to return a MoveActorAction. If she can't call any of these she will
	 * return a DoNothingAction.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current MamboMarie is
	 * @param display the Display where the Mambo Marie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		tick++;
		
		if (tick == 30) {
			this.invisible = true;
			tick = 0;
			return new VanishAction(this);
		}
		if (tick%10 == 0) {
			return new SummonZombieBehaviour(5).getAction(this, map);
		}
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);

			if (action != null){
				
				return action;
			}
		}
		return new DoNothingAction();	
	}
	/**
	 * Will roll a 1/20 chance of returning true, indicating she should appear
	 * otherwise return false to indicate she should remain vanished
	 * 
	 * @see RespawnableActor#shouldSpawn()
	 */
	@Override
	public boolean shouldSpawn() {
		if(rand.nextInt(20)==0 && invisible) {
			return true;
		}
		return false;	
	}
	
	/**
	 * This function will place Mambo Marie on the edge of the players map
	 * 
	 * @param map the map Mambo Marie should appear
	 * @see RespawnableActor#Spawn(GameMap))
	 */
	@Override
	public void Spawn(GameMap map) {
		int x = 0;
		int y = 0;
		final int xwidth = map.getXRange().max();
		final int ywidth = map.getYRange().max();
		do {
			if(rand.nextBoolean()) {
				//put it somewhere random on the edge of the y axis
				x = (int) Math.floor(Math.random() * xwidth);
				if(rand.nextBoolean()) {
					y = 0;
				}
				else {
					y = ywidth;
				}
			}
			
			else {
				//put it somewhere random on the edge of the x axis
				y = (int) Math.floor(Math.random() * ywidth);
				if(rand.nextBoolean()) {
					x = 0;
				}
				else {
					x = xwidth;
				}
			}
		} 
		while (map.at(x, y).containsAnActor() || !map.at(x, y).canActorEnter(this));
		map.addActor(this, map.at(x, y));
		this.invisible = false;
	}
	
}
