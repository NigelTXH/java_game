package game.grounds.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Enemy;
import game.interfaces.CanClone;
import game.interfaces.CanSpawn;
import game.world.ControlMapInitialise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Spawners abstract class represents a type of terrain that can spawn actors.
 * It provides functionality for spawning actors at specific locations and managing spawn chances.
 * Classes that extend this abstract class can implement their own spawn mechanics.
 *
 * The Spawners class extends the Ground class and implements the CanSpawn interface.
 * It maintains a list of potential spawn locations and uses randomization to determine if actors should spawn.
 *
 * @author Sam Leong
 */
public abstract class Spawners extends Ground implements CanSpawn {
    List<Location> spawnLocation = new ArrayList<>();
    private final Random random = new Random();
    private int spawnChance;

    protected final int DEFAULT_SPAWN_CHANCE;
    private CanClone monster;

    /**
     * Constructor for the Spawners abstract class.
     *
     * @param displayChar The character to display for this type of terrain.
     * @param monster     The prototype of the monster that can be spawned.
     * @param spawnChance The default spawn chance for this terrain.
     */
    public Spawners(char displayChar, CanClone monster, int spawnChance) {
        super(displayChar);
        this.monster = monster;
        this.DEFAULT_SPAWN_CHANCE = spawnChance;
        this.spawnChance = DEFAULT_SPAWN_CHANCE;
    }

    /**
     * Reverts the spawn chance to its default value.
     */
    public void revertSpawnChance(){
        this.spawnChance = DEFAULT_SPAWN_CHANCE;
    }


    @Override
    public void tick(Location location) {
        spawnMechanic(location);
    }

    /**
     * Sets the spawn chance for this terrain.
     *
     * @param value The new spawn chance value.
     */
    public void setSpawnChance(int value) {
        this.spawnChance = value;
    }

    /**
     * Retrieves the current spawn chance for this terrain.
     *
     * @return The current spawn chance value.
     */
    public int getSpawnChance() {
        return this.spawnChance;
    }

    /**
     * Checks if a monster can spawn at a given location and adds it to the list of potential spawn locations.
     *
     * @param location The location where the monster may spawn.
     * @param monster  The prototype of the monster to be spawned.
     */
    @Override
    public void canSpawnMonster(Location location, Actor monster) {

        if(location.canActorEnter(monster) && !(location.containsAnActor())){
            spawnLocation.add(location);
        }
    }

    /**
     * Implements the spawn mechanic for this terrain.
     * It iterates through exit locations and checks if a monster can spawn at each location.
     * If conditions are met and the random chance allows it, a monster is spawned.
     * The spawnLocation list is cleared to reset possible spawn locations.
     *
     * @param location The location where the spawn mechanic is applied.
     */
    @Override
    public void spawnMechanic(Location location) {
        for(Exit exits: location.getExits()){
            Location destination = exits.getDestination();
            canSpawnMonster(destination, monster.duplicate());

        }

        if (!spawnLocation.isEmpty()) {
            Location result = spawnLocation.get(random.nextInt(spawnLocation.size()));
            if (random.nextInt(1,100) <= this.spawnChance){

                Actor enemy = monster.duplicate();
                result.addActor(enemy);
                ControlMapInitialise.addEnemy(enemy);

            }
            spawnLocation.clear(); //Clear so that possible spawn locations are reset

        }
    }
}

