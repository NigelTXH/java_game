package game.buffactions;

import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.GameWeapon;
/**
 * A class used to increase the weapon's attack
 * @author Nigel Tan
 */
public class AttackUpAction extends BuffAction {
    /**
     * Constructor for AttackUpAction.
     * @param amount amount to increase the attack by.
     */
    public AttackUpAction(float amount){
        super(amount, BuffAttributes.ATTACK);
    }
    /**
     * When executed, will increase the player's damage.
     * @param weapon The weapon performing the action.
     * @param map The map the actor is on.
     * @return a string describing that the weapon damage increased.
     */
    @Override
    public String execute(GameWeapon weapon, GameMap map) {
        weapon.increaseDamageMultiplier(getAmount());
        return menuDescription(weapon);
    }
    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param weapon The weapon performing the action.
     * @return a String, e.g. "Attack increased to 121".
     */
    @Override
    public String menuDescription(GameWeapon weapon) {
        return "Attack increased to " + weapon.damage();
    }
}
