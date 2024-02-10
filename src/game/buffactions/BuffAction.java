package game.buffactions;

import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.GameWeapon;
/**
 * An abstract class used to buff the weapon
 * @author Nigel Tan
 */
public abstract class BuffAction {
    private float amount;
    private BuffAttributes buffName;
    /**
     * Constructor for BuffAction.
     * @param amount amount to increase the attack by.
     * @param buffName name of buff.
     */
    public BuffAction(float amount, BuffAttributes buffName){
        this.amount = amount;
        this.buffName = buffName;
    }
    /**
     * When executed, will buff the player.
     * @param weapon The weapon performing the action.
     * @param map The map the actor is on.
     * @return a string describing that the buff occured.
     */
    public abstract String execute(GameWeapon weapon, GameMap map);
    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param weapon The weapon performing the action.
     * @return a String to show that a buff occurred.
     */
    public abstract String menuDescription(GameWeapon weapon);
    /**
     * Method which get the amount.
     * @return the amount is returned.
     */
    public float getAmount() {
        return amount;
    }
    /**
     * Method which get the buff name.
     * @return the buff name is returned.
     */
    public BuffAttributes getBuffName() {
        return buffName;
    }
}
