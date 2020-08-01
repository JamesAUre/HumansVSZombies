package game;

import edu.monash.fit2099.engine.*;

import java.io.IOException;
import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Human {
	private WeaponItem shotgun;
	private WeaponItem sniper;
	private Actor sniperTarget;
	private Location possibleExit;
	private String exitName;
	private int aimCount;
	private boolean aimLastTurnStatus;
	private Menu menu = new Menu();
	private WorldOfMambo world;
	private boolean endGameStatus=false;
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
	
	public Player(String name, char displayChar, int hitPoints, WorldOfMambo world) {
		super(name, displayChar, hitPoints);
		this.world = world;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		// Handle multi-turn Actions
		List<Item> playerItems= this.getInventory();
		if (this.getAimCount()>0){
			actions.add(new ContinueAim());
			actions.add(new FireNow(sniperTarget));
		}
		for (int i = 0; i < playerItems.size(); i++){
			if (playerItems.get(i).toString()=="Zombie arm" || playerItems.get(i).toString()=="Zombie leg" ){
				System.out.println("test");
				CraftableWeaponItem armOrLeg=(CraftableWeaponItem)playerItems.get(i);
				actions.add(new CraftingAction(armOrLeg));

			}

			if (playerItems.get(i).toString()=="food"){
				actions.add(new EatingAction((FoodItem) playerItems.get(i)));

			}
			if (playerItems.get(i).getShotgunActions(this,map)!=null){
				shotgun=(Shotgun)playerItems.get(i);
				actions.add(playerItems.get(i).getShotgunActions(this,map));
				if (playerItems.get(i).getReloadAction(this,shotgun)!=null){
					actions.add(playerItems.get(i).getReloadAction(this,shotgun));
				}
			}

			if (playerItems.get(i).getSniperActions(this)!=null){
				sniper=(SniperRifle)playerItems.get(i);
				actions.add(playerItems.get(i).getSniperActions(this));
				if (playerItems.get(i).getReloadAction(this,sniper)!=null){
					actions.add(playerItems.get(i).getReloadAction(this,sniper));
				}
			}
		}
		
		//actions.add(map.locationOf(this).getGround().allowableActions(this, map.locationOf(this), ""));
		
		
		//System.out.println(actor.get)
		
		//if (map.locationOf(this).getGround().getDisplayChar()=='*'){
		//	actions.add(new HarvestAction(map.locationOf(this)));
		//}

		//if (map.locationOf(this).getGround().getDisplayChar()=='>'){
		//	
		//	GameMap newmap = world.getmaps(1);
		//	newmap.draw(display);
		//	actions.add(new MoveActorAction(new Location(map, 4, 4), "to town"));
			//actions.add(new DriveAction(new Location(map, 4,4), "town"));
			//
			//map.moveActor(this, new Location(newmap, 2, 2));
			//newmap.addActor(this, new Location(newmap, 2, 2));
			//map.removeActor(this);
			//actions.add(new DoNothingAction());
			//actions.add(new DriveAction(new Location(newmap, 1, 4), "to town"));
		//}

		if (lastAction.getNextAction() != null){
			return lastAction.getNextAction();
		}

		actions.add(new QuitGameAction());
		Action finalAction=menu.showMenu(this, actions, display);
		if (!finalAction.getAimLastTurn()){
			aimCount=0;
		}
		return finalAction;
	}
	
	public void harvest(Location harvestlocation, GameMap map) {
		this.addItemToInventory(new FoodItem("apple", 5));
	}
	
	@Override
	public void setAimCount(int aimCount){
		this.aimCount=aimCount;
	}

	@Override
	public int getAimCount(){
		return aimCount;
	}

	@Override
	public void setSniperTarget(Actor target){
		sniperTarget=target;
	}

	@Override
	public WeaponItem getShotgun(){
		return shotgun;
	}

	@Override
	public WeaponItem getSniper(){
		return sniper;
	}

	@Override
	public boolean isPlayer(){
		return true;
	}

	@Override
	public void endGame(){
		endGameStatus=true;
	}

	@Override
	public boolean getEndGameStatus(){
		return endGameStatus;
	}
}


