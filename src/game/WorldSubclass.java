package game;

import edu.monash.fit2099.engine.*;

import java.util.Iterator;
import java.util.List;



public class WorldSubclass extends World {
    private boolean humanStatus=false;
    private boolean endStatus=false;
    private int humanCount;
    private int zombieCount;
    private int playerCount;
    WorldSubclass(Display display){
        super(display);

    }



    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();

        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        while (stillRunning() && !endStatus) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning())
                    processActorTurn(actor);
            }

            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                gameMap.tick();
                for (int y = 0; y<24; y++){
                    for (int x = 0; x<80; x++){
                        if (gameMap.at(x,y).containsAnActor()){
                            if (gameMap.at(x,y).getActor().isHuman()){
                                humanCount+=1;
                            }

                            else if (gameMap.at(x,y).getActor().isZombie()){
                                zombieCount+=1;
                            }

                            else if (gameMap.at(x,y).getActor().isPlayer()){
                                playerCount+=1;
                            }

                        }
                    }
                }
            }

            if (player.getEndGameStatus()){
                break;
            }

            if (playerCount==0){
                break;
            }
            if (humanCount==0 || zombieCount==0){
                break;
            }

        }
        display.println(endGameMessage());
    }

}
