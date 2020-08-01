package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

/**
 * ShotgunAction is the class that handles the damage actors will take when players choose a direction to fire in
 * @author Sak Yu Jin
 */
public class ShotgunAction extends Action {

    private String returnString="";
    private String direction="";
    private Actor temp;
    Location shootDirection;
    Random r = new Random();
    /**
     * Constructor
     * @param location Location in which the bullet is fired
     * @param direction The name of the direction
     */
    public ShotgunAction(Location location,String direction){
        shootDirection=location;
        this.direction=direction;
        returnString="Player fired in direction of "+direction;

    }

    /**
     * Damages actors in range of fire and removes them if they are killed
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that will show in the log after player has performed the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        float chance = r.nextFloat();
        map.locationOf(actor).getExits();
        int possDirec1X=0;
        int possDirec1Y=0;
        int possDirec2X=0;
        int possDirec2Y=0;
        int mainDirecX=0;
        int mainDirecY=0;

        int x=shootDirection.x();
        int y=shootDirection.y();
        int actorX=map.locationOf(actor).x();
        int actorY=map.locationOf(actor).y();
        Random r = new Random();


        x=x-actorX;
        y=y-actorY;

        if ((x==0 && y==1) || (x==0 && y==-1)){
            reduceAmmo(actor);
            mainDirecX=actorX+x;
            mainDirecY=actorY+y;
            for (int i = 0; i<4; i++){
                possDirec1X=actorX+x;
                possDirec2X=actorX+x;

                if (checkBounds(mainDirecX,mainDirecY) && map.at(mainDirecX,mainDirecY).getActor()!=null && chance <= 0.75f){
                    chance = r.nextFloat();
                    if (chance <= 0.75f){
                        map.at(mainDirecX,mainDirecY).getActor().hurt(35);
                        returnString+=map.at(mainDirecX,mainDirecY).getActor()+ "is damaged for 45\n";
                        if (!map.at(mainDirecX,mainDirecY).getActor().isConscious()){
                            returnString+=map.at(mainDirecX,mainDirecY).getActor()+ "is killed with a shotgun\n";
                            if (map.at(mainDirecX,mainDirecY).getActor().isHuman()){map.locationOf(map.at(mainDirecX,mainDirecY).getActor()).addItem(new CorpseItem("corpse",'c'));}
                            map.removeActor(map.at(mainDirecX,mainDirecY).getActor());

                        }
                    }
                    else{
                        returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                    }


                }




                for (int j=0;j<i+1;j++){
                    possDirec1X-=1;
                    possDirec2X+=1;


                    if (checkBounds(possDirec1X,mainDirecY) && map.at(possDirec1X,mainDirecY).containsAnActor()) {
                        chance = r.nextFloat();
                        if (chance <= 0.75f){
                            map.at(possDirec1X, mainDirecY).getActor().hurt(35);
                            returnString += map.at(possDirec1X, mainDirecY).getActor() + "is damaged for 45\n";
                            if (!map.at(possDirec1X, mainDirecY).getActor().isConscious()) {
                                returnString += map.at(possDirec1X, mainDirecY).getActor() + "is killed with a shotgun\n";
                                if (map.at(possDirec1X, mainDirecY).getActor().isHuman()){map.locationOf(map.at(possDirec1X,mainDirecY).getActor()).addItem(new CorpseItem("corpse",'c'));}
                                map.removeActor(map.at(possDirec1X, mainDirecY).getActor());

                            }
                        }
                        else {
                            returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                        }

                    }



                    if (checkBounds(possDirec2X,mainDirecY) && map.at(possDirec2X,mainDirecY).getActor()!=null){
                        chance = r.nextFloat();
                        if (chance <= 0.75f){
                            map.at(possDirec2X,mainDirecY).getActor().hurt(35);
                            returnString+=map.at(possDirec2X,mainDirecY).getActor()+ "is damaged for 45\n";
                            if (!map.at(possDirec2X,mainDirecY).getActor().isConscious()){
                                returnString+=map.at(possDirec2X,mainDirecY).getActor()+ "is killed with a shotgun\n";
                                if (map.at(possDirec2X, mainDirecY).getActor().isHuman()){map.locationOf(map.at(possDirec2X,mainDirecY).getActor()).addItem(new CorpseItem("corpse",'c'));}
                                map.removeActor(map.at(possDirec2X,mainDirecY).getActor());

                            }
                        }
                        else{
                            returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                        }

                    }


                }
                mainDirecY+=y;

            }
            returnString = returnString.trim();
            return returnString;

        }

        else if ((x==-1 && y==0) || (x==1 && y==0)){
            reduceAmmo(actor);
            mainDirecX=actorX+x;
            mainDirecY=actorY+y;
            for (int i = 0; i<3; i++){
                possDirec1Y=actorY+y;
                possDirec2Y=actorY+y;


                if (checkBounds(mainDirecX,mainDirecY) && map.at(mainDirecX,mainDirecY).getActor()!=null ){
                    chance = r.nextFloat();
                    if (chance <= 0.75f){
                        map.at(mainDirecX,mainDirecY).getActor().hurt(35);
                        returnString+=map.at(mainDirecX,mainDirecY).getActor()+ "is damaged for 45\n";
                        if (!map.at(mainDirecX,mainDirecY).getActor().isConscious()){
                            returnString+=map.at(mainDirecX,mainDirecY).getActor()+ "is killed with a shotgun\n";
                            if (map.at(mainDirecX, mainDirecY).getActor().isHuman()){map.locationOf(map.at(mainDirecX,mainDirecY).getActor()).addItem(new CorpseItem("corpse",'c'));}
                            map.removeActor(map.at(mainDirecX,mainDirecY).getActor());

                        }
                    }
                    else{
                        returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                    }


                }


                for (int j=0;j<i+1;j++){
                    possDirec1Y-=1;
                    possDirec2Y+=1;


                    if (checkBounds(mainDirecX,possDirec1Y) && map.at(mainDirecX,possDirec1Y).containsAnActor() ) {
                        chance = r.nextFloat();
                        if (chance <= 0.75f){
                            map.at(mainDirecX, possDirec1Y).getActor().hurt(35);
                            returnString += map.at(mainDirecX, possDirec1Y).getActor() + "is damaged for 45\n";
                            if (!map.at(mainDirecX, possDirec1Y).getActor().isConscious()) {
                                returnString += map.at(mainDirecX, possDirec1Y).getActor() + "is killed with a shotgun\n";
                                if (map.at(mainDirecX, possDirec1Y).getActor().isHuman()){map.locationOf(map.at(mainDirecX,possDirec1Y).getActor()).addItem(new CorpseItem("corpse",'c'));}
                                map.removeActor(map.at(mainDirecX, possDirec1Y).getActor());

                            }
                        }
                        else{
                            returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                        }

                    }

                    if (checkBounds(mainDirecX,possDirec2Y) && map.at(mainDirecX,possDirec2Y).getActor()!=null){
                        chance = r.nextFloat();
                        if (chance <= 0.75f){
                            map.at(mainDirecX,possDirec2Y).getActor().hurt(35);
                            returnString+=map.at(mainDirecX,possDirec2Y).getActor()+ "is damaged for 45\n";
                            if (!map.at(mainDirecX,possDirec2Y).getActor().isConscious()){
                                returnString+=map.at(mainDirecX,possDirec2Y).getActor()+ "is killed with a shotgun\n";
                                if (map.at(mainDirecX, possDirec2Y).getActor().isHuman()){map.locationOf(map.at(mainDirecX,possDirec2Y).getActor()).addItem(new CorpseItem("corpse",'c'));}
                                map.removeActor(map.at(mainDirecX,possDirec2Y).getActor());
                            }
                        }
                        else{
                            returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                        }

                    }
                }
                mainDirecX+=x;
            }
            returnString = returnString.trim();
            return returnString;

        }

        else if (x==-1 && y==-1) {
            reduceAmmo(actor);
            mainDirecX=actorX-3;
            mainDirecY=actorY-3;
            for (int i = 0; i<4; i++){
                mainDirecX=actorX-3;
                for (int j=0;j<4;j++){
                    chance = r.nextFloat();
                    if (checkBounds(mainDirecX,mainDirecY) && map.at(mainDirecX,mainDirecY).containsAnActor() && (mainDirecX!=actorX && mainDirecY!=actorY) ) {
                        if (chance <= 0.75f){
                            map.at(mainDirecX, mainDirecY).getActor().hurt(35);
                            returnString += map.at(mainDirecX, mainDirecY).getActor() + "is damaged for 45\n";
                            if (!map.at(mainDirecX, mainDirecY).getActor().isConscious()) {
                                returnString += map.at(mainDirecX, mainDirecY).getActor() + "is killed with a shotgun\n";
                                if (map.at(mainDirecX, mainDirecY).getActor().isHuman()){map.locationOf(map.at(mainDirecX,mainDirecY).getActor()).addItem(new CorpseItem("corpse",'c'));}
                                map.removeActor(map.at(mainDirecX, mainDirecY).getActor());

                            }
                        }
                        else{
                            returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                        }

                    }
                    mainDirecX+=1;
                }
                mainDirecY+=1;

            }
            returnString = returnString.trim();
            return returnString;

        }

        else if (x==1 && y ==-1) {
            reduceAmmo(actor);
            mainDirecX=actorX+3;
            mainDirecY=actorY-3;
            for (int i = 0; i<4; i++){
                mainDirecX=actorX;
                for (int j=0;j<4;j++){

                    if (checkBounds(mainDirecX,mainDirecY) && map.at(mainDirecX,mainDirecY).containsAnActor() && (mainDirecX!=actorX && mainDirecY!=actorY)) {


                        if (chance <= 0.75f){
                            map.at(mainDirecX, mainDirecY).getActor().hurt(35);
                            returnString += map.at(mainDirecX, mainDirecY).getActor() + "is damaged for 45\n";
                            if (!map.at(mainDirecX, mainDirecY).getActor().isConscious()) {
                                returnString += map.at(mainDirecX, mainDirecY).getActor() + "is killed with a shotgun\n";
                                if (map.at(mainDirecX, mainDirecY).getActor().isHuman()){map.locationOf(map.at(mainDirecX,mainDirecY).getActor()).addItem(new CorpseItem("corpse",'c'));}
                                map.removeActor(map.at(mainDirecX, mainDirecY).getActor());

                            }
                        }
                        else{
                            returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                        }

                    }
                    mainDirecX+=1;
                }
                mainDirecY+=1;

            }
            returnString = returnString.trim();
            return returnString;

        }

        else if (x==-1 && y ==1) {
            reduceAmmo(actor);
            mainDirecX=actorX-3;
            mainDirecY=actorY;
            for (int i = 0; i<4; i++){
                mainDirecX=actorX-3;
                for (int j=0;j<4;j++){

                    if (checkBounds(mainDirecX,mainDirecY) && map.at(mainDirecX,mainDirecY).containsAnActor() && (mainDirecX!=actorX && mainDirecY!=actorY)) {
                        if (chance <= 0.75f){
                            map.at(mainDirecX, mainDirecY).getActor().hurt(35);
                            returnString += map.at(mainDirecX, mainDirecY).getActor() + "is damaged for 45\n";
                            if (!map.at(mainDirecX, mainDirecY).getActor().isConscious()) {
                                returnString += map.at(mainDirecX, mainDirecY).getActor() + "is killed with a shotgun\n";
                                if (map.at(mainDirecX, mainDirecY).getActor().isHuman()){map.locationOf(map.at(mainDirecX,mainDirecY).getActor()).addItem(new CorpseItem("corpse",'c'));}
                                map.removeActor(map.at(mainDirecX, mainDirecY).getActor());

                            }
                        }
                        else{
                            returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                        }

                    }
                    mainDirecX+=1;
                }
                mainDirecY+=1;
            }
            returnString = returnString.trim();
            return returnString;
        }

        else if (x==1 && y ==1) {
            reduceAmmo(actor);
            mainDirecX=actorX;
            mainDirecY=actorY;
            for (int i = 0; i<4; i++){
                mainDirecX=actorX;
                for (int j=0;j<4;j++){

                    if (checkBounds(mainDirecX,mainDirecY) && map.at(mainDirecX,mainDirecY).containsAnActor() && (mainDirecX!=actorX && mainDirecY!=actorY)) {
                        if (chance <= 0.75f){
                            map.at(mainDirecX, mainDirecY).getActor().hurt(35);
                            returnString += map.at(mainDirecX, mainDirecY).getActor() + "is damaged for 45\n";
                            if (!map.at(mainDirecX, mainDirecY).getActor().isConscious()) {
                                returnString += map.at(mainDirecX, mainDirecY).getActor() + "is killed with a shotgun\n";
                                if (map.at(mainDirecX, mainDirecY).getActor().isHuman()){map.locationOf(map.at(mainDirecX,mainDirecY).getActor()).addItem(new CorpseItem("corpse",'c'));}
                                map.removeActor(map.at(mainDirecX, mainDirecY).getActor());

                            }
                        }
                        else{
                            returnString+="Shotgun missed "+map.at(possDirec1X, mainDirecY).getActor();
                        }

                    }
                    mainDirecX+=1;
                }
                mainDirecY+=1;
            }
            returnString = returnString.trim();
            return returnString;
        }

        return null;
    }

    /**
     * The string that will appear in the menu
     * @param actor The actor performing the action.
     * @return The menu string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoots with shotgun in direction of " +direction;
    }

    /**
     * To reduce the amount of ammo by 1 after the player has taken a shot
     * @param actor
     */
    private void reduceAmmo(Actor actor){
        actor.getShotgun().setGunAmmo(actor.getShotgun().getGunAmmo()-1);
    }

    /**
     * To check if the bullet fired does not fire out of bounds
     * @param x the x position of the bullet that has been fired
     * @param y the y position of the bullet that has been fired
     * @return boolean true or false show if the bullet is in the game map or not
     */
    private boolean checkBounds(int x, int y){
        if (x<=79 && x>=0 && y>=0 && y<=23){
            return true;
        }
        else{
         return false;
        }
    }
}
