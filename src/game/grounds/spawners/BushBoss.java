package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.CanClone;
import game.weather.Weather;
import game.weather.WeatherEnum;

/**
 * The BushBoss class represents a special type of bush terrain called "Bush Boss" in the game world.
 * Bush Bosses can spawn certain types of monsters and have their spawn chance adjusted based on weather conditions.
 *
 * The BushBoss class extends the Bush class and overrides the tick method to adapt its spawn chance to rainy weather.
 *
 * @author Sam Leong
 */
public class BushBoss extends Bush{
    private Weather weather;

    /**
     * Constructor for the BushBoss class.
     * Initializes a bush boss terrain that can spawn a specific type of monster and adjusts its spawn chance based on weather.
     *
     * @param monster The prototype of the monster that can spawn in bush bosses.
     * @param weather The weather object representing the current weather conditions in the game.
     */
    public BushBoss(CanClone monster, int spawnChance, Weather weather) {
        super(monster, spawnChance);
        this.weather = weather;

    }

    /**
     * Overrides the tick method to adapt the spawn chance of the Bush Boss based on weather conditions.
     * During rainy weather, the spawn chance is increased by 1.5 times the default spawn chance.
     * Otherwise, the spawn chance is reverted to its default value.
     *
     * @param location The location where the tick operation is performed.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.weather.isWeather(WeatherEnum.RAINY)){
            //note: i made it so that i times the original spawn rate instead of directly setting it.
            int newSpawnRate = Math.round(this.DEFAULT_SPAWN_CHANCE * 1.5F);
            this.setSpawnChance(newSpawnRate);
        } else {
            this.revertSpawnChance();
        }
    }
}
