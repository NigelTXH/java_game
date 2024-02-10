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
 * The RedWolf class represents a non-player character (NPC) called the Red Wolf.
 * The Red Wolf is a creature in the game that has behaviors such as attacking and wandering.
 * It is also capable of following the player when in close proximity.
 * This class extends the ActorCanDrop class and implements the CanClone interface.
 *
 * @author Sam Leong
 */
public class RedWolf extends Enemy implements CanClone {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    private final IntrinsicWeapon intrinsicWeapon;

    /**
     * Constructor for the RedWolf class.
     * Initializes the Red Wolf character with a name, display character, and hit points.
     * Also assigns behaviors, intrinsic weapons, and drops for the Red Wolf.
     */
    public RedWolf() {
        super("Red Wolf", 'r', 25);
        this.intrinsicWeapon = new IntrinsicWeapon(15, "bites", 80);
        this.behaviours.put(997, new AttackBehavior());
        this.behaviours.put(999, new WanderBehaviour());

        HealingVial healingVial = new HealingVial(25);
        Runes runes = new Runes(100,25);

        this.addDroppableItem(runes);
        this.addDroppableItem(healingVial);
        this.addCapability(Status.ENEMY);
    }

    /**
     * Determines the action to be taken by the Red Wolf during its turn.
     * The Red Wolf may attack the player, follow the player, or wander randomly.
     *
     * @param actions    The list of possible actions the Red Wolf can take.
     * @param lastAction The last action performed by the Red Wolf.
     * @param map        The game map in which the Red Wolf is located.
     * @param display    The display used to show the game world.
     * @return The action to be taken by the Red Wolf during its turn.
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
                if(key < numberToChoose || numberToChoose == -1){ //If key is 997 (attack) vs numberToChoose is 998 (follow), bestAction will be attack
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
     * Retrieves the intrinsic weapon of the Red Wolf.
     *
     * @return The intrinsic weapon of the Red Wolf.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return this.intrinsicWeapon;
    }

    /**
     * Creates a clone of the Red Wolf actor.
     *
     * @return A new instance of the Red Wolf actor.
     */
    @Override
    public Actor duplicate(){
        return new RedWolf();
    }

    /**
     * Determines the allowable actions for the Red Wolf when interacting with other actors.
     *
     * @param otherActor The actor with which the Red Wolf interacts.
     * @param direction  The direction in which the interaction is happening.
     * @param map        The game map in which the interaction occurs.
     * @return A list of allowable actions for the Red Wolf when interacting with other actors.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
