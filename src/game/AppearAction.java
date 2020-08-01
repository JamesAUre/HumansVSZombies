package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class AppearAction extends Action {

	@Override
	public String execute(Actor actor, GameMap map) {
		map.addActor(actor, new Location(map, 1, 1));
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " appears!";
	}

}
