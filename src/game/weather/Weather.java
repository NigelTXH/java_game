package game.weather;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A class used to store the possible weathers.
 * @author Nigel Tan
 */
public class Weather {
    private HashMap<WeatherEnum, Boolean> weathers = new HashMap<>();
    /**
     * Constructor for Weather.
     * A hashmap is used to symbolise whether the weather is rainy or sunny through a boolean.
     */
    public Weather(){
        weathers.put(WeatherEnum.RAINY,false);
        weathers.put(WeatherEnum.SUNNY,false);
    }

    /**
     * Get the boolean weather if the weather is true or false.
     * @param weatherEnum the weather to check if it is true or false
     * @return the boolean when the hashmap is checked using the weatherEnum as the key.
     */
    public boolean isWeather(WeatherEnum weatherEnum){
        return weathers.get(weatherEnum);
    }

    /**
     * A getter for the weather hashmap
     * @return a copy of the weathers hashmap.
     */
    public Map<WeatherEnum, Boolean> getWeathers() {
        return Collections.unmodifiableMap(weathers);
    }

    /**
     * A setter for the weatherEnum's boolean value.
     * @param weatherEnum the weatherEnum to change the boolean to
     * @param bool the boolean to set the weatherEnum previous boolean was.
     */
    public void setWeather(WeatherEnum weatherEnum, boolean bool){
        weathers.replace(weatherEnum, bool);
    }
}
