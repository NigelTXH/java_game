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
 * A class used to heal the player
 * @author Nigel Tan
 */
public class HealingVial extends UpgradableItem implements CanBeDroppedItem{
    /**
     * Constructor for HealingVial.
     */
    public HealingVial(int dropRate){
        super(dropRate,"Healing Vial", 'a', Upgradable.HEALTH);
        int levelCap = 1;
        this.addToUpgradableActionsList(new UpgradableActions(this, BuffAttributes.HEALTH, 0.7f, 200, BlackSmithEnum.ANDRE, levelCap));
    }
    /**
     * List of allowable actions only being HealAction to heal the player or upgrades if level cap not reached.
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions which contains HealAction or UpgradeActions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList(new ConsumeAction(Math.round(owner.getAttributeMaximum(BaseActorAttributes.HEALTH) * (0.1f+this.getUpgradeAmount())), this, BaseActorAttributes.HEALTH));
        for(UpgradableActions upgradableAction: this.getUpgradableActionsList()){
            if(upgradableAction.menuDescription(owner) != null){
                actions.add(upgradableAction);
            }
        }


        return actions;
    }

}
