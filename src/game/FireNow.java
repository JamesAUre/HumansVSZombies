package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

/**
 * The class that handles everything when the sniper is fired and after the sniper is fired
 * @author Sak Yu Jin
 */
public class FireNow extends Action {

    private String returnString="";
    private Actor target;
    private int aimCount=0;
    Random r = new Random();

    /**
     * constructor
     * set the target variable to the paramter value
     * @param target the target to be shot
     */
    FireNow(Actor target){
        this.target=target;
    }

    /**
     * The menu description
     * @param actor The actor performing the action.
     * @return the menu description that will be shown for actors to select
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Fire now";
    }

    /**
     * Checks the aim count and adjusts the accuracy of sniper accordingly
     * Removes the actors that will have died
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the string that will be shown in the log
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        float chance = r.nextFloat();
        aimCount=actor.getAimCount();
        actor.setAimCount(0);
        if (aimCount==0){
            if (chance <= 0.75f){
                target.hurt(40);
                reduceAmmo(actor);
                returnString=target+" shot by sniper for 40";
                if (!target.isConscious()){
                    returnString=target+" got killed by the sniper rifle";
                    if (target.isHuman()){map.locationOf(target).addItem(new CorpseItem("corpse",'c'));}
                    map.removeActor(target);

                }
            }
            else{
                returnString="Sniper shot missed!";

            }
           return returnString;


        }
        else if(aimCount==1){
            if (chance <= 0.90f){
                target.hurt(80);
                returnString="DOUBLE DAMAGE! "+target+" shot by sniper for 80";
                if (!target.isConscious()){
                    returnString=target+" got killed by the sniper rifle";
                    if (target.isHuman()){map.locationOf(target).addItem(new CorpseItem("corpse",'c'));}
                    map.removeActor(target);
                }

            }
            else {
                returnString="Sniper shot missed!";

            }
            return returnString;
        }


        else{
            target.hurt(1000);
            returnString="INSTA-KILL! "+target+" got killed by the sniper rifle";
            if (target.isHuman()){map.locationOf(target).addItem(new CorpseItem("corpse",'c'));}
            map.removeActor(target);
            return returnString;

        }

    }

    /**
     * to show that the player has aimed the last turn
     * @return true
     */
    @Override
    public boolean getAimLastTurn(){
        return true;
    }

    /**
     * TO reduce the ammo of the gun by one after firing
     * @param actor the Actor using the gun
     */
    private void reduceAmmo(Actor actor){
        actor.getSniper().setGunAmmo(actor.getSniper().getGunAmmo()-1);
    }
}
