package game.actors.burialgroundactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.Status;
import game.behaviours.AttackBehavior;
import game.behaviours.WanderBehaviour;
import game.actors.Enemy;
import game.droprate.DropItem;
import game.interfaces.CanClone;
import game.item.consumables.HealingVial;
import game.item.Runes;
import game.item.consumables.StaminaVial;

import java.util.HashMap;
import java.util.Map;
/**
 * A class used to implement the hollow soldier
 * @author Nigel Tan
 */
public class HollowSoldier extends Enemy implements CanClone {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * Constructor for HollowSoldier.
     * Behaviours are inserted as well as the drops
     */
    public HollowSoldier() {
        super("Hollowed Soldier", '&', 200);
        this.behaviours.put(998, new AttackBehavior());
        this.behaviours.put(999, new WanderBehaviour());

        Runes runes = new Runes(100, 100);
        this.addDroppableItem(runes);

        HealingVial healingVial = new HealingVial(20);
        this.addDroppableItem(healingVial);

        StaminaVial staminaVial = new StaminaVial(20);
        this.addDroppableItem(staminaVial);
        this.getIntrinsicWeapon();

        this.addCapability(Status.ENEMY);
    }

    /**
     * At each turn, select to either attack or wander around.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
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
     * Increase HollowSoldier's damage to 50.
     * @return the intrinsic weapon
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50,"punch",50);
    }

    /**
     * The hollow soldier can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of possible attack actions for the player
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
     * Creates a clone of the HollowSoldier actor.
     *
     * @return A new instance of the HollowSoldier actor.
     */
    @Override
    public Actor duplicate() {
        return new HollowSoldier();
    }
}
