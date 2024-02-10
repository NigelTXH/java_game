package game.actors.abxervyerbattleactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ancientwoodactors.RedWolf;
import game.weather.Weather;
import game.weather.WeatherEnum;

/**
 * The RedWolfBoss class represents a special type of Red Wolf actor, known as the Red Wolf Boss.
 * This actor has the ability to adapt to the weather conditions, increasing its damage during sunny weather.
 *
 * The RedWolfBoss class extends the RedWolf class and overrides the playTurn method to adapt its damage
 * based on the weather conditions.
 *
 * @author Sam Leong
 */
public class RedWolfBoss extends RedWolf {
    private Weather weather;

    /**
     * Constructor for the RedWolfBoss class.
     *
     * @param weather The weather object representing the current weather conditions in the game.
     */
    public RedWolfBoss(Weather weather){
        this.weather = weather;
    }

    /**
     * Overrides the playTurn method to adapt the Red Wolf Boss's damage based on the weather conditions.
     * During sunny weather, the Red Wolf Boss increases its damage multiplier.
     *
     * @param actions    The list of possible actions the Red Wolf Boss can take.
     * @param lastAction The last action performed by the Red Wolf Boss.
     * @param map        The game map in which the Red Wolf Boss is located.
     * @param display    The display used to show the game world.
     * @return The action to be taken by the Red Wolf Boss during its turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (weather.isWeather(WeatherEnum.SUNNY)){
            this.increaseDamageMultiplier(1.5f);
        } else{
            this.updateDamageMultiplier(1);
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
