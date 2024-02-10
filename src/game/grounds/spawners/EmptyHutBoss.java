package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.CanClone;
import game.weather.Weather;
import game.weather.WeatherEnum;

/**
 * The EmptyHutBoss class represents a special type of empty hut terrain called "Empty Hut Boss" in the game world.
 * Empty Hut Bosses can spawn certain types of monsters and have their spawn chance adjusted based on weather conditions.
 *
 * The EmptyHutBoss class extends the EmptyHut class and overrides the tick method to adapt its spawn chance to sunny weather.
 *
 * @author Nigel Tan
 * @version 1.0
 */
public class EmptyHutBoss extends EmptyHut{
    private Weather weather;

    /**
     * Constructor for the EmptyHutBoss class.
     * Initializes an empty hut boss terrain that can spawn a specific type of monster and adjusts its spawn chance based on weather.
     *
     * @param monster The prototype of the monster that can spawn in empty hut bosses.
     * @param weather The weather object representing the current weather conditions in the game.
     */
    public EmptyHutBoss(CanClone monster, int spawnChance, Weather weather){
        super(monster, spawnChance);
        this.weather = weather;
    }

    /**
     * Overrides the tick method to adapt the spawn chance of the Empty Hut Boss based on weather conditions.
     * During sunny weather, the spawn chance is increased by 2 times the default spawn chance.
     * Otherwise, the spawn chance is reverted to its default value.
     *
     * @param location The location where the tick operation is performed.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(weather.isWeather(WeatherEnum.SUNNY)){
            int newSpawnRate = Math.round(this.DEFAULT_SPAWN_CHANCE * 2F);
            this.setSpawnChance(newSpawnRate);
        } else {
            this.revertSpawnChance();
        }
    }
}
