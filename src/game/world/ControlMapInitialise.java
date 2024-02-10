package game.world;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.item.Runes;

import java.util.*;

/**
 * The ControlMapInitialise class is responsible for managing and initializing game maps and game state in the text-based game.
 * It provides methods for resetting maps, adding and clearing enemy lists, and handling player respawns.
 *
 * This class is part of the game.world package and is designed for use within the game engine.
 *
 * @author Sam Leong
 */
public class ControlMapInitialise {
    public static final Map<AllGameMapsEnum, InitialiseGameMap> initMaps = new HashMap<>();

    public static final List<Actor> enemyList = new ArrayList<>();

    public static Runes lostRunes;

    public static Location lostRunesLocation;

    /**
     * Adds an initialization map for a specific game map to the initMaps collection.
     *
     * @param mapName The enum representing the game map.
     * @param initMap The InitializeGameMap object associated with the game map.
     */
    public static void addInitMap(AllGameMapsEnum mapName, InitialiseGameMap initMap){
        initMaps.put(mapName, initMap);
    }

    /**
     * Resets all game maps to their initial state and clears the enemy list.
     */
    public static void resetAll(){
        for (InitialiseGameMap map : initMaps.values()){
            map.reset();
        }
        clearEnemyList();
    }

    /**
     * Adds an enemy actor to the enemy list.
     *
     * @param enemy The enemy actor to be added.
     */
    public static void addEnemy(Actor enemy){
        enemyList.add(enemy);
    }

    /**
     * Clears the enemy list.
     */
    public static void clearEnemyList(){
        enemyList.clear();
    }

    /**
     * Retrieves an unmodifiable list of enemy actors.
     *
     * @return An unmodifiable list of enemy actors.
     */
    public static List<Actor> getEnemyList(){
        return Collections.unmodifiableList(enemyList);
    }

    /**
     * Respawns the player character and manages the lost runes item.
     *
     * @param player The player character (Actor).
     * @param map The game map where the player is respawned.
     */
    public static void respawn(Actor player, GameMap map){
        Runes newLostRunes = new Runes(100, player.getBalance());
        if (lostRunes != null && lostRunesLocation != null){
            lostRunesLocation.removeItem(lostRunes);
        }
        map.locationOf(player).addItem(newLostRunes);
        lostRunes = newLostRunes;
        lostRunesLocation = map.locationOf(player);
        player.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, player.getAttributeMaximum(BaseActorAttributes.HEALTH));
        player.deductBalance(player.getBalance());
    }
}
