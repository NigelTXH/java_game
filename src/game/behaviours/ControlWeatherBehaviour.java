package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ControlWeatherAction;
import game.weather.Weather;

/**
 * A class used to control the behaviour of the actor of when to change the weather
 * @author Nigel Tan
 */
public class ControlWeatherBehaviour implements Behaviour {
    private int counter = 1;
    private Weather weather;

    /**
     * Constructor for ControlWeatherAction.
     * @param weather the weather to pass to the ControlWeatherAction
     */
    public ControlWeatherBehaviour(Weather weather){
        this.weather = weather;
    }
    /**
     * When the counter hits 1, the counter goes to 3 and the ControlWeatherAction takes place.
     * Else decrement the counter by 1 and return null.
     * @param map the GameMap
     * @param actor the location of the trader
     * @return a ControlWeatherAction if the counter is 1, else return null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(counter == 1){
            counter = 3; //Counter reaches 1 then use ability again
            return new ControlWeatherAction(weather);
        }
        else{
            counter --;
            return null;
        }
    }
}
