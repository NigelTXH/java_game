package game.actors.owergrwonsantuaryactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Enemy;
import game.actors.Status;
import game.behaviours.AttackBehavior;
import game.behaviours.WanderBehaviour;
import game.interfaces.CanClone;
import game.item.Runes;
import game.item.consumables.BloodBerry;

import java.util.HashMap;
import java.util.Map;

/**
 * The LivingBranch class represents an enemy character in the text-based game. It is a type of enemy that can be found
 * in the Overgrown Sanctuary area. LivingBranch has unique behaviors and can clone itself.
 *
 * This class is part of the game.actors.owergrwonsantuaryactors package and is designed for use within the game engine.
 *
 * @author Sam Leong
 */
public class LivingBranch extends Enemy implements CanClone {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    private final IntrinsicWeapon intrinsicWeapon;

    /**
     * Constructor for the LivingBranch class. Initializes the LivingBranch with a name, display character, and health points.
     * It also sets its intrinsic weapon and behaviors, as well as its capabilities.
     */
    public LivingBranch() {
        super("Living Branch", '?', 75);
        this.intrinsicWeapon = new IntrinsicWeapon(250, "hits", 90);
        this.behaviours.put(997, new AttackBehavior());
        this.addCapability(Status.ENEMY);

        BloodBerry bloodBerry = new BloodBerry(50);
        Runes runes = new Runes(100, 500);

        this.addDroppableItem(bloodBerry);
        this.addDroppableItem(runes);
    }

    /**
     * Determines the action to be taken during the LivingBranch's turn based on its behaviors and game state.
     *
     * @param actions    The list of possible actions.
     * @param lastAction The last action performed by the LivingBranch.
     * @param map        The game map where the LivingBranch is located.
     * @param display    The display for rendering the game.
     * @return The chosen action for the LivingBranch.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
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
     * Retrieves the intrinsic weapon of the LivingBranch.
     *
     * @return The intrinsic weapon of the LivingBranch.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return this.intrinsicWeapon;
    }

    /**
     * Creates a duplicate instance of the LivingBranch.
     *
     * @return A new LivingBranch instance.
     */
    @Override
    public Actor duplicate() {
        return new LivingBranch();
    }
}
