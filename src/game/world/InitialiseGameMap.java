package game.world;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Enemy;
import game.grounds.Gate;
import game.world.AllGameMapsEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The InitialiseGameMap class is responsible for initializing and managing game maps in the text-based game.
 * It provides methods to add bosses and gates to the map, as well as resetting the map when required.
 *
 * This class is part of the game.world package and is designed for use within the game engine.
 *
 * @author Sam Leong
 */
public class InitialiseGameMap {
    private final AllGameMapsEnum mapName;
    private final Actor player;
    private List<Actor> bosses = new ArrayList<>();
    private GameMap map;
    private List<Gate> gates = new ArrayList<>();

    /**
     * Constructor for the InitialiseGameMap class.
     *
     * @param mapName The enum representing the game map to be initialized.
     * @param map The GameMap to be initialized.
     * @param player The player character (Actor) in the game.
     */
    public InitialiseGameMap(AllGameMapsEnum mapName, GameMap map, Actor player){
        this.mapName = mapName;
        this.map = map;
        this.player = player;
    }

    /**
     * Add a boss character (Actor) to the game map.
     *
     * @param boss The boss character to be added to the map.
     */
    public void addBoss(Actor boss){
        this.bosses.add(boss);
    }

    /**
     * Add a gate (Gate object) to the game map.
     *
     * @param gate The gate object to be added to the map.
     */
    public void addGate(Gate gate){
        this.gates.add(gate);
    }

    /**
     * Reset the game map to its initial state, removing enemies, restoring boss characters' health,
     * and locking gates as necessary.
     */
    public void reset(){
        List<Actor> enemyList = ControlMapInitialise.getEnemyList();
        for (Actor enemy : enemyList){
            if (this.map.contains(enemy)){
                this.map.removeActor(enemy);
            }
        }
        if (!bosses.isEmpty()){
            for (Actor boss : bosses){
                if (boss.isConscious()){
                    boss.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE,
                            boss.getAttributeMaximum(BaseActorAttributes.HEALTH));
                }
            }
        }

        if (!this.gates.isEmpty()){
            for (Gate gate : gates){
                gate.lockGate();
            }
        }
        if (this.mapName == AllGameMapsEnum.ABANDONEDVILLAGE){
            this.map.moveActor(player, this.map.at(28,5));
        }
    }



}
