package game.world;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.grounds.Gate;

import java.util.HashMap;
/**
 * A class for storing all the gameMaps
 * @author Nigel Tan
 */
public class AllGameMaps {
    private static HashMap<AllGameMapsEnum, GameMap> gameMaps = new HashMap<>();

    /**
     * Static method to add a gameMap to the word and also add to the gameMaps list.
     * @param gameMap the gameMap to be added
     * @param world the world
     * @param gameMapsEnum the name of the gameMap
     */
    public static void addGameMap(GameMap gameMap, World world, AllGameMapsEnum gameMapsEnum){
        world.addGameMap(gameMap);
        gameMaps.put(gameMapsEnum, gameMap);
    }
    /**
     * Static method to add a gate to the world.
     * @param current the gameMap to place the gate at
     * @param sX X coordinate of the gate
     * @param sY Y coordinate of the gate
     * @param dX X coordinate of the destination
     * @param dY Y coordinate of the destination
     * @param allDestination the destination of the gate
     */
    public static Gate addGate(GameMap current,int sX, int sY, int dX, int dY, AllGameMapsEnum...  allDestination){
        Gate gate = new Gate();

        for(AllGameMapsEnum destination: allDestination){
            gate.addCapability(destination);
        }

        gate.setX(dX);
        gate.setY(dY);
        current.at(sX, sY).setGround(gate);

        return gate;

    }
    /**
     * Static method to get the gameMap.
     * @param gameMapsEnum the name of the gameMap
     * @return a gameMap
     */
    public static GameMap getGameMap(AllGameMapsEnum gameMapsEnum){
        return gameMaps.get(gameMapsEnum);
    }

    public static Gate addGateUnlocked(GameMap current,int sX, int sY, int dX, int dY,AllGameMapsEnum...  allDestination){
        Gate gate = new Gate();
        for(AllGameMapsEnum destination: allDestination){
            gate.addCapability(destination);
        }
        gate.setX(dX);
        gate.setY(dY);
        gate.unlockGate();
        current.at(sX, sY).setGround(gate);

        return gate;
    }
}
