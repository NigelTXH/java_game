package game.abilities;

import game.buffactions.AttackUpAction;
import game.buffactions.ChanceToHitAction;
/**
 * Class to implement the focus ability
 * @author Nigel Tan
 */
public class FocusAbility extends CreateAbility {


    /**
     * Constructor for FocusAbility which increases the attack and chance to hit.
     * @param expiry the duration before expiry.
     * @param damageIncrease percentage of damage increase.
     * @param chanceToHitIncrease the chance out of 100 that the attack will land.
     * @param stamina how much stamina the ability uses.
     */
    public FocusAbility(int expiry, float damageIncrease, float chanceToHitIncrease, float stamina){
        super(Ability.FOCUS,expiry+1, stamina); //For example, 5 turns AFTER being cast
        effects.add(new AttackUpAction(damageIncrease));
        effects.add(new ChanceToHitAction(chanceToHitIncrease));
    }

}
