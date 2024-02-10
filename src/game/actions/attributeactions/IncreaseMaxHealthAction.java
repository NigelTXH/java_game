package game.actions.attributeactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class IncreaseMaxHealthAction extends Action {
    private final int increaseAmount;
    private final Item item;

    public IncreaseMaxHealthAction(int increaseAmount, Item item){
        this.increaseAmount = increaseAmount;
        this.item = item;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,this.increaseAmount);
        return actor + " maximum hp increased for "+this.increaseAmount+" points.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses "+ item+ " to increase max hp";
    }
}
