package game.actors.abxervyerbattleactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.Enemy;
import game.actors.Status;
import game.behaviours.AttackBehavior;
import game.behaviours.ControlWeatherBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.displays.FancyMessage;
import game.grounds.Gate;
import game.item.Runes;
import game.monologue.MonologueEnum;
import game.weather.Weather;
import game.weather.WeatherEnum;
import game.world.AllGameMaps;
import game.world.AllGameMapsEnum;
import game.world.ControlMapInitialise;

import java.util.HashMap;
import java.util.Map;
/**
 * Class representing Abxeryver, who can control the weather.
 * Created by:
 * @author Nigel Tan
 * Modified by:
 * @author Nigel Tan and Sam Leong
 */
public class Abxervyer extends Enemy {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final IntrinsicWeapon intrinsicWeapon;
    private Weather weather;

    /**
     * The constructor of the Actor class.
     * @param player The player to follow
     * @param weather The weather to control
     */
    public Abxervyer(Actor player, Weather weather) {
        super("Abxervyer", 'Y', 2000);
        this.intrinsicWeapon = new IntrinsicWeapon(80, "whacks", 25);
        this.behaviours.put(996, new ControlWeatherBehaviour(weather));
        this.behaviours.put(998, new FollowBehaviour(player));
        this.behaviours.put(997, new AttackBehavior());
        this.behaviours.put(999, new WanderBehaviour());
        this.weather = weather;

        Runes runes = new Runes(100, 2000);

        this.addDroppableItem(runes);
        this.addCapability(Status.ENEMY);
    }
    /**
     * At each turn, choose the best behaviour which is not null and also the lowest number in the behaviours hashmap.
     * Priority from highest to lowest: Control Weather > Attack > Follow > Wander.
     * Else do nothing.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the prioritised action that can be performed in that iteration or do nothing
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        int numberToChoose = -1;
        Action bestAction = null;
        for (int key : behaviours.keySet()) {

            Action action = behaviours.get(key).getAction(this, map);
            if(action != null){
                if(key < numberToChoose || numberToChoose == -1){ //If key is 998 (attack) vs numberToChoose is 999 (wander), bestAction will be attack
                    numberToChoose = key;
                    bestAction = action;
                }
            }


        }

        if(bestAction != null){
            return bestAction;
        }
        return new DoNothingAction();

    }
    /**
     * Increase boss's damage to 15.
     * @return the intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return this.intrinsicWeapon;
    }

    /**
     * Determines the allowable actions for Abxeryver when interacting with other actors.
     *
     * @param otherActor The actor with which Abxeryver interacts.
     * @param direction  The direction in which the interaction is happening.
     * @param map        The game map in which the interaction occurs.
     * @return A list of allowable actions for Abxeryver when interacting with other actors.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * When the boss is unconscious, drop a gate and also drop the items through the super method
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */

    @Override
    public String unconscious(Actor actor, GameMap map) {
        actor.addCapability(MonologueEnum.DEFEATED_AXERYVER);
        for(WeatherEnum weathers: weather.getWeathers().keySet()){
            weather.setWeather(weathers, false);
        }

        int sy = map.locationOf(this).y();
        int sx = map.locationOf(this).x();
        Gate gate = AllGameMaps.addGateUnlocked(map, sx, sy, 20, 10, AllGameMapsEnum.ANCIENT_WOODS);
        ControlMapInitialise.initMaps.get(AllGameMapsEnum.ABANDONEDVILLAGE).addGate(gate);
        new Display().println(FancyMessage.BOSS_DEFEATED);
        return super.unconscious(actor, map);
    }

}
