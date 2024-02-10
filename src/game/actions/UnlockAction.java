package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Gate;
/**
 * A class used to unlock the gate
 * @author Nigel Tan
 */
public class UnlockAction extends Action {

    private Gate gate;
    /**
     * Constructor for UnlockAction.
     * @param gate gate to unlock.
     */
    public UnlockAction(Gate gate){
        this.gate = gate;
    }
    /**
     * When executed, will unlock the gate.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing that an ability is used.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        gate.unlockGate();
        return menuDescription(actor);
    }
    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Unlock gate".
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unlock gate";
    }
}
