package game.buffactions;

import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.GameWeapon;
/**
 * A class used to increase the weapon's chance to hit
 * @author Nigel Tan
 */
public class ChanceToHitAction extends BuffAction {
    /**
     * Constructor for ChanceToHitAction.
     * @param amount amount to set the chance to hit by.
     */
    public ChanceToHitAction(float amount){
        super(amount, BuffAttributes.HITRATE);
    }
    /**
     * When executed, will set the player's chance to hit.
     * @param weapon The weapon performing the action.
     * @param map The map the actor is on.
     * @return a string describing that the weapon damage increased.
     */
    @Override
    public String execute(GameWeapon weapon, GameMap map) {
        weapon.updateHitRate(Math.round(getAmount()));
        return menuDescription(weapon);
    }
    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param weapon The weapon performing the action.
     * @return a String, e.g. "Attack chance increased to 90%".
     */
    @Override
    public String menuDescription(GameWeapon weapon) {
        return "Attack chance increased to " + weapon.chanceToHit() + "%";
    }
}
