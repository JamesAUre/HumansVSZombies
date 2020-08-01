package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import java.util.Random;

/**
 * Controller for the SayAction class, here you can specify what you want the actor to
 * say and how frequently.
 * 
 * @author Sak Yu Jin
 *
 */
public class SayBehaviour implements Behaviour {
    
	/**
	 * Rolls a 1/10 chance to say 'Braiiins', if successful actor will use its
	 * turn to say Braiiins which will be displayed on the menu
	 * 
	 * @param actor the actor performing the behaviour
	 * @param map the map representing where the actor will perform the behaviour
	 * @return a SayAction instance or null
	 */
	@Override
    public Action getAction(Actor actor, GameMap map){
        Random r=new Random();
        int sayChance=r.nextInt(100);

        if (sayChance<10){
            return new SayAction("Braiiins");
        }
        return null;
    }
}
