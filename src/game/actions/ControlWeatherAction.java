package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weather.Weather;
import game.weather.WeatherEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class used to control the weather
 * @author Nigel Tan
 */
public class ControlWeatherAction extends Action {
    private Weather weather;
    /**
     * Constructor for ControlWeatherAction.
     * @param weather the weather to change the current WeatherEnum to false and randomly select
     *                one possible WeatherEnum to true
     */
    public ControlWeatherAction(Weather weather){
        this.weather = weather;
    }

    /**
     * When an actor picks this action, it will check the value of the hashmap of WeatherEnum to boolean values.
     * If the WeatherEnum is true, turn it to false, else add the weather to possible weather to change to true.
     * Once that is done, if possibleWeatherToChange is not empty, a random weather from that list is chosen to
     * be set to true.
     * @param actor the actor performing this action
     * @param map the GameMap
     * @return if no weather is chosen, the weather is NONE, else it is a random weather.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        WeatherEnum chosenWeather = WeatherEnum.NONE;
        List<WeatherEnum> possibleWeatherToChange = new ArrayList<>();
        for(WeatherEnum weatherEnum: weather.getWeathers().keySet()){
            if(!(weather.getWeathers().get(weatherEnum))){
                possibleWeatherToChange.add(weatherEnum);
            }
            else{
                weather.setWeather(weatherEnum, false);
            }
        }

        if(!possibleWeatherToChange.isEmpty()){
            Random random = new Random();
            chosenWeather = possibleWeatherToChange.get(random.nextInt(possibleWeatherToChange.size()));
            weather.setWeather(chosenWeather, true);

        }
        return "Change weather to " + chosenWeather;
    }

    /**
     * A method for displaying the menu description but since the boss is using it,
     * there is no point in giving a proper menu description.
     * @param actor the actor wanting to perform this action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
