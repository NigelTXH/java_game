package game.actors.owergrwonsantuaryactors;

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
import game.actors.Enemy;
import game.actors.Status;
import game.behaviours.AttackBehavior;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.droprate.DropItem;
import game.interfaces.CanClone;
import game.item.Runes;
import game.item.consumables.HealingVial;
import game.item.consumables.StaminaVial;

import java.util.HashMap;
import java.util.Map;

/**
 * The EldenTreeGuardian class represents an enemy character in the text-based game. It is a type of guardian that can be found
 * in the Overgrown Sanctuary area. EldenTreeGuardian has unique behaviors and can clone itself.
 *
 * This class is part of the game.actors.owergrwonsantuaryactors package and is designed for use within the game engine.
 *
 * @author Sam Leong
 */
public class EldenTreeGuardian extends Enemy implements CanClone {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    private final IntrinsicWeapon intrinsicWeapon;

    /**
     * Constructor for the EldenTreeGuardian class. Initializes the EldenTreeGuardian with a name, display character, and health points.
     * It also sets its intrinsic weapon and behaviors, as well as its capabilities.
     */
    public EldenTreeGuardian() {
        super("Elden Tree Guardian", 's', 250);
        this.intrinsicWeapon = new IntrinsicWeapon(50, "hits", 80);
        this.behaviours.put(997, new AttackBehavior());
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Status.ENEMY);

        HealingVial healingVial = new HealingVial(25);
        StaminaVial staminaVial = new StaminaVial(15);
        Runes runes = new Runes(100,250);

        this.addDroppableItem(healingVial);
        this.addDroppableItem(staminaVial);
        this.addDroppableItem(runes);

    }

    /**
     * Determines the action to be taken by the Elden Tree Guardian during its turn.
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
        if(this.isConscious()){
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
        }
        return new DoNothingAction();
    }

    /**
     * Checks if the player character is nearby in the game map.
     *
     * @param map The game map where the Elden Tree Guardian is located.
     * @return The player actor if found nearby, or null if the player is not around.
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
     * Retrieves the intrinsic weapon of the Elden Tree Guardian.
     *
     * @return The intrinsic weapon of the Elden Tree Guardian.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return this.intrinsicWeapon;
    }

    /**
     * Creates a clone of the Elden Tree Guardian actor.
     *
     * @return A new instance of the Elden Tree Guardian actor.
     */
    @Override
    public Actor duplicate() {
        return new EldenTreeGuardian();
    }

}
