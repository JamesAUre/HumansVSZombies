package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		WorldOfMambo world = new WorldOfMambo(new Display());

		
FancyGroundFactory groundFactory3 = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100, world);
		world.addPlayer(player, gameMap.at(42, 15));

	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};

		String[] farmers = {"Billy", "Carl", "James", "Tom"};

		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
		
		for (String name : farmers) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Farmer(name));	
		}
		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());

		
		// FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));
		
		//gameMap.at(1, 1).addActor(new MamboMarie());
		
		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Fence(), new Tree(), new Brick(), new Tile());
		
		List<String> map2 = Arrays.asList(
		"================================================================================",
		"=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=,,,,,,,,,,,,,,,,,,,,,,=",
		"=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=,,,,,,,,,,,,,,,,,,,,,,=",
		"=,,,,,,,,,,,,,,=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=,,,,,,,,,,,,,,,,,,,,,,=",
		"=,,,,,,,,,,,,,,=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=,,,,,,,,,,,,,,,,,,,,,,=",
		"====.....=============================================================.....=====",
		".........=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=..........",
		".........=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=..........",
		".........=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=..........",
		".........=,,,,,,,,,,#######################################,,,,,,,,,,=..........",
		".........=,,,,,,,,,,#.....................................#,,,,,,,,,,=..........",
		".........=,,,,,,...............................................,,,,,,=..........",
		".........=,,,,,,...............................................,,,,,,=..........",
		".........=,,,,,,...............................................,,,,,,=..........",
		".........=,,,,,,,,,,#.....................................#,,,,,,,,,,=..........",
		".........=,,,,,,,,,,#.....................................#,,,,,,,,,,=..........",
		".........=,,,,,,,,,,#.....................................#,,,,,,,,,,=..........",
		".........============.....................................============..........",
		".........++++++++++++.....................................++++++++++++..........",
		"..........++++++++++........................................+++++++.............",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................");
		GameMap gameMap2 = new GameMap(groundFactory2, map2 );
		world.addGameMap(gameMap2);		
		
		gameMap2.at(5, 2).addActor(new Zombie("Groan"));
		gameMap2.at(33,  4).addActor(new Zombie("Boo"));
		gameMap2.at(40,  7).addActor(new Zombie("Uuuurgh"));
		gameMap2.at(62, 12).addActor(new Zombie("Mortalis"));
		gameMap2.at(42, 14).addItem(new SniperRifle());
		gameMap2.at(42, 13).addItem(new Shotgun());
		gameMap2.at(41, 14).addItem(new SniperAmmo());
		gameMap2.at(41, 13).addItem(new ShotgunAmmo());
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap2.at(x, y).containsAnActor());
			gameMap2.at(x,  y).addActor(new Human(name));	
		}
		
		for (String name : farmers) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap2.at(x, y).containsAnActor());
			gameMap2.at(x,  y).addActor(new Farmer(name));	
		}
		
		Location travelto = gameMap2.at(44, 16);
		Location travelback = gameMap.at(43, 16);
		Location car1 = gameMap.at(44, 16);
		car1.setGround(new Vehicle(travelto));
		Location car2 = gameMap2.at(45, 16);
		car2.setGround(new Vehicle(travelback));
		
		world.addinvisibleactor(new MamboMarie("Mambo Marie"));
		
		world.run();
	}
}
