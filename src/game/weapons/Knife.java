package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.abilities.CreateAbility;
import game.actions.blacksmithactions.UpgradableActions;
import game.actors.blacksmith.BlackSmithEnum;
import game.buffactions.BuffAttributes;
import game.monologue.MonologueEnum;
import game.upgrade.Upgradable;

/**
 * A class for the knife of the game
 * @author Nigel Tan
 */
public class Knife extends UpgradableGameWeapon {

    /**
     * Constructor for a knife without an ability.
     * @param name name of weapon
     * @param displayChar char to be displayed on the map
     * @param damage the damage to be dealt
     * @param hitRate the chance to hit
     */
    public Knife(String name, char displayChar, int damage, int hitRate, Upgradable upgradable){
        super(name, displayChar, damage, "stabs", hitRate, upgradable);
        this.addCapability(MonologueEnum.HAS_GREAT_KNIFE);
        this.addToUpgradableActionsList(new UpgradableActions(this, BuffAttributes.HITRATE, 1f, 2000, BlackSmithEnum.ANDRE));
    }
    /**
     * Constructor for a knife with an ability.
     * @param name name of weapon
     * @param displayChar char to be displayed on the map
     * @param damage the damage to be dealt
     * @param hitRate the chance to hit
     * @param ability the ability
     */
    public Knife(String name, char displayChar, int damage, int hitRate, CreateAbility ability, Upgradable upgradable){
        super(name, displayChar, damage, "stabs", hitRate, ability, upgradable);
        this.addCapability(MonologueEnum.HAS_GREAT_KNIFE);
        this.addToUpgradableActionsList(new UpgradableActions(this, BuffAttributes.HITRATE, 1f, 2000, BlackSmithEnum.ANDRE));
    }

    /**
     * The menu description of UpgradableActions is checked everytime if the
     * upgrade is not null before displaying the option of upgrading.
     * @param owner the owner of the weapon.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);

        for(UpgradableActions upgradableAction: this.getUpgradableActionsList()){
            if(upgradableAction.menuDescription(owner) != null){
                actions.add(upgradableAction);
            }
        }

        return actions;
    }

}
