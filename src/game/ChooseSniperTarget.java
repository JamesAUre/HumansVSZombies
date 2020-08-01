package game;

import edu.monash.fit2099.engine.*;

/**
 * Class to set target as certain actor
 * @author Sak Yu Jin
 */
public class ChooseSniperTarget extends Action {


    Actor target;
    private int aimCounter;

    /**
     * The constructor will set the variable target to the paramter
     * @param actor the target that will be shot
     */
    ChooseSniperTarget(Actor actor){
        target=actor;

    }

    /**
     * Check the aim counter, where the sniper's accuracy will depend on the aim count
     * Create a submenu for the user to fire instantly or aim another turn
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns the action description that will be displayed in the log
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.setSniperTarget(target);
        Actions AimOrFire=new Actions();
        aimCounter=actor.getAimCount();

        if (aimCounter<2){
            AimOrFire.add(new ContinueAim());
        }
        AimOrFire.add(new FireNow(target));

        Menu aimOrFireMenu=new Menu();
        String returnString=aimOrFireMenu.showMenu(actor,AimOrFire,new Display()).execute(actor,map);

        return target +" chosen as target\n"+returnString;
    }

    /**
     * The string that will be displayed in the menu
     * @param actor The actor performing the action.
     * @return the menu description for player to choose
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Choose " + target + " as the target";
    }
}
