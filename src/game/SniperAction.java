package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Sniper Action class where all the  functions of sniper will be handled
 */
public class SniperAction extends Action {


    SniperAction(){
    }

    /**
     * Returns the menu description
     * @param actor The actor performing the action.
     * @return the menu description that will be presented to player
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Use the sniper rifle";
    }

    /**
     * This method creates a submenu by scanning the map for potential targets then adding them to the new menu
     * players can choose to shoot any target on the map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns the string that will be shown in the log
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Actions  chooseTargetActions=new Actions();

        for (int y = 0; y<map.getXRange().max(); y++){
            for (int x = 0; x<map.getYRange().max(); x++){
                if (map.at(x,y).containsAnActor() && (x!=map.locationOf(actor).x() && y!=map.locationOf(actor).y())){

                    chooseTargetActions.add(new ChooseSniperTarget(map.at(x,y).getActor()));
                }
            }
        }
        Menu targetSubMenu =new Menu();
        String returnSring =targetSubMenu.showMenu(actor,chooseTargetActions,new Display()).execute(actor,map);
        return returnSring;
    }
}
