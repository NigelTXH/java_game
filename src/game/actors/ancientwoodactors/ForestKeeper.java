package game.actors.ancientwoodactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.Status;
import game.behaviours.AttackBehavior;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.actors.Enemy;
import game.droprate.DropItem;
import game.interfaces.CanClone;
import game.item.consumables.HealingVial;
import game.item.Runes;

import java.util.HashMap;
import java.util.Map;

/**
 * The ForestKeeper class represents a non-player character (NPC) called the Forest Keeper.
 * The Forest Keeper is a character in the game that has various behaviors, including following the player,
 * attacking when necessary, and wandering randomly.
 *
 * @author Sam Leong
 */
public class ForestKeeper extends Enemy implements CanClone {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final IntrinsicWeapon intrinsicWeapon;
    private Actor player;

    /**
     * Constructor for the ForestKeeper class.
     *
     * @param player The player actor that the Forest Keeper will follow and interact with.
     */

    public ForestKeeper(Actor player) {
        super("Forest Keeper", '8', 125);
        this.intrinsicWeapon = new IntrinsicWeapon(25, "whacks", 75);
        this.behaviours.put(997, new AttackBehavior());
        this.behaviours.put(999, new WanderBehaviour());


        Runes runes = new Runes(100,50);

        HealingVial healingVial = new HealingVial(25);
        this.addDroppableItem(healingVial);
        this.addDroppableItem(runes);
        this.addCapability(Status.ENEMY);
    }

    /**
     * Determines the action to be taken by the Forest Keeper during its turn.
     *
     * @param actions    The list of possible actions the Forest Keeper can take.
     * @param lastAction The last action performed by the Forest Keeper.
     * @param map        The game map in which the Forest Keeper is located.
     * @param display    The display used to show the game world.
     * @return The action to be taken by the Forest Keeper during its turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Actor player = isPlayerAround(map);
        if (player != null && !this.behaviours.containsKey(998)){
            this.behaviours.put(998, new FollowBehaviour(player));
        }

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
     * Checks if the player is around the Red Wolf's location.
     * If the player is found in an adjacent location, the Red Wolf can decide to follow the player.
     *
     * @param map The game map in which the Red Wolf is located.
     * @return The player actor if found in an adjacent location; otherwise, null.
     */
    public Actor isPlayerAround(GameMap map){
        for (Exit exit: map.locationOf(this).getExits()){
            if (exit.getDestination().containsAnActor()
                    && exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                return map.getActorAt(exit.getDestination());
            }
        }
        return null;
    }

    /**
     * Retrieves the intrinsic weapon of the Forest Keeper.
     *
     * @return The intrinsic weapon of the Forest Keeper.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return this.intrinsicWeapon;
    }

    /**
     * Creates a clone of the Forest Keeper actor.
     *
     * @return A new instance of the Forest Keeper actor.
     */
    @Override
    public Actor duplicate(){
        return new ForestKeeper(player);
    }

    /**
     * Determines the allowable actions for the Forest Keeper when interacting with other actors.
     *
     * @param otherActor The actor with which the Forest Keeper interacts.
     * @param direction  The direction in which the interaction is happening.
     * @param map        The game map in which the interaction occurs.
     * @return A list of allowable actions for the Forest Keeper when interacting with other actors.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
