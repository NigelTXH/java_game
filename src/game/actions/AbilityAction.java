package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.abilities.CreateAbility;
import game.buffactions.BuffAction;
import game.weapons.GameWeapon;
/**
 * Class to use an ability.
 * @author Nigel Tan
 */
public class AbilityAction extends Action {
    private final GameWeapon weapon;
    private final CreateAbility ability;
    private final Display display = new Display();
    /**
     * Constructor for AbilityAction.
     * @param weapon weapon to be modified
     * @param ability the ability connected to the weapon
     */
    public AbilityAction(GameWeapon weapon, CreateAbility ability) {
        this.ability = ability;
        this.weapon = weapon;

    }
    /**
     * When executed, will apply the buffs to the weapon for a certain duration as well as decreasing the actor's stamina.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing that an ability is used.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ability.renew();
        for (BuffAction buffs: ability.getEffects()) {
            display.println(buffs.execute(weapon,map));
        }

        if(actor.hasAttribute(BaseActorAttributes.STAMINA)){
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, Math.round(ability.getStamina()));
        }

        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Use ability FOCUS"
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Use ability " + ability;
    }
}
