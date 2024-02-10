package game.actors.abxervyerbattleactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.actors.ancientwoodactors.ForestKeeper;
import game.weather.Weather;
import game.weather.WeatherEnum;

/**
 * The ForestKeeperBoss class represents a special type of Forest Keeper actor, known as the Forest Keeper Boss.
 * This actor has the ability to adapt to the weather conditions, healing itself during rainy weather.
 *
 * The ForestKeeperBoss class extends the ForestKeeper class and overrides the playTurn method to adapt its healing ability
 * based on the weather conditions.
 *
 * @author Nigel Tan
 */
public class ForestKeeperBoss extends ForestKeeper {
    public Weather weather;

    /**
     * Constructor for the ForestKeeperBoss class.
     *
     * @param player  The player actor with which the Forest Keeper Boss interacts.
     * @param weather The weather object representing the current weather conditions in the game.
     */
    public ForestKeeperBoss(Player player, Weather weather){
        super(player);
        this.weather = weather;
    }

    /**
     * Overrides the playTurn method to adapt the Forest Keeper Boss's healing ability based on the weather conditions.
     * During rainy weather, the Forest Keeper Boss heals itself.
     *
     * @param actions    The list of possible actions the Forest Keeper Boss can take.
     * @param lastAction The last action performed by the Forest Keeper Boss.
     * @param map        The game map in which the Forest Keeper Boss is located.
     * @param display    The display used to show the game world.
     * @return The action to be taken by the Forest Keeper Boss during its turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(weather.isWeather(WeatherEnum.RAINY)){
            this.heal(10);
        }
        Action result = super.playTurn(actions, lastAction, map, display);
        return result;
    }
}
