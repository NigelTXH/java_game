package game.item.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actions.attributeactions.ConsumeAction;
import game.actions.blacksmithactions.UpgradableActions;
import game.actors.blacksmith.BlackSmithEnum;
import game.buffactions.BuffAttributes;
import game.interfaces.CanBeDroppedItem;
import game.item.UpgradableItem;
import game.upgrade.Upgradable;

/**
 * A class used to heal the player's stamina
 * @author Nigel Tan
 */
public class StaminaVial extends UpgradableItem implements CanBeDroppedItem {
    /**
     * Constructor for StaminaVial.
     */
    public StaminaVial(int dropRate){
        super(dropRate, "Stamina Vial", 'u', Upgradable.STAMINA);
        int levelCap = 1;
        this.addToUpgradableActionsList(new UpgradableActions(this, BuffAttributes.STAMINA, 0.8f, 175, BlackSmithEnum.ANDRE, levelCap));

    }
    /**
     * List of allowable actions only being StaminaIncreaseAction to heal the player's stamina or upgrades if level cap not reached.
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions which contains StaminaIncreaseAction
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList(new ConsumeAction(Math.round(owner.getAttributeMaximum(BaseActorAttributes.STAMINA) * (0.2f+ this.getUpgradeAmount())), this, BaseActorAttributes.STAMINA));
        for(UpgradableActions upgradableAction: this.getUpgradableActionsList()){
            if(upgradableAction.menuDescription(owner) != null){
                actions.add(upgradableAction);
            }
        }

        return actions;
    }

}
