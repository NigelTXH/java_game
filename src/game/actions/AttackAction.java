package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.PlayerCapability;
import game.displays.FancyMessage;
import game.world.ControlMapInitialise;

import java.util.Random;
/**
 * A class used to perform an attack
 * @author Nigel Tan
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    private Actor attacker;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     * @param weapon the weapon used to attack the target
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }
    /**
     * When executed, the target will be either be damaged by the damage of the weapon or missed.
     * If the health of the target is 0, it is unconcious and drop anything that is droppable.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing that an attack is possible.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.attacker = actor;
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            Display display = new Display();
            result += "\n" + target.unconscious(actor, map);
            if(target.hasCapability(PlayerCapability.IS_PLAYER)){
                display.println(FancyMessage.YOU_DIED);
            }
        }

        return result;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player attacks Wandering Undead at North with broad sword"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }

}
