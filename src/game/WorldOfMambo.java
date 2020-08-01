package game;


import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

public class WorldOfMambo extends World {
	private int humanCount=0;
	private int zombieCount=0;
	private ArrayList<RespawnableActor> invisibleactors = new ArrayList<RespawnableActor>();
	
	/**
	 * Constructor
	 * @param display the Display that will display this World.
	 */
	public WorldOfMambo(Display display) {
		super(display);
	}
	
	/**
	 * Adds RespawnableActors that are invisible to the map without placing them on the map
	 * however they can be placed on the map later.
	 * 
	 * @param actor the invisible actor to be placed on the map later
	 */
	public void addinvisibleactor(RespawnableActor actor) {
		invisibleactors.add(actor);
	}

    /**
	 * Run the game.
	 *
	 * On each iteration the gameloop does the following: - Checks for all the invisible 
	 * RespawnableActors stored within this world, if any are invisible, ask the 
	 * RespawnableActor if they should spawn and if they should place them on the map.
	 * 
	 * displays the player's map - processes the actions of every Actor in the game, regardless of map
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors. We chose to
	 * process all the actors.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	@Override
	public void run() {
		
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}
		
		// This loop is basically the whole game
		while (stillRunning()) {
			humanCount=0;
			zombieCount=0;
			GameMap playersMap = actorLocations.locationOf(player).map();	
			
			for (RespawnableActor actor :invisibleactors) {
				if ( !actorLocations.contains(actor) && actor.isConscious() && actor.shouldSpawn()){
					actor.Spawn(playersMap);
				}
			}
			
			playersMap.draw(display);
			
			
			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}
			
			
			
			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
				for (int y = 0; y<gameMap.getYRange().max()-1; y++){

					for (int x = 0; x<gameMap.getXRange().max()-1; x++){
						if (gameMap.at(x,y).containsAnActor()){

							if (gameMap.at(x,y).getActor().isZombie()){
								zombieCount+=1;
							}

							else if (gameMap.at(x,y).getActor().isHuman()){
								humanCount+=1;
							}

						}
					}
				}
			}
			zombieCount+=invisibleactors.size();
			if (player.getEndGameStatus() || humanCount==0 || zombieCount==0){
				break;
			}

		}
		display.println(endGameMessage());
	}
	
}