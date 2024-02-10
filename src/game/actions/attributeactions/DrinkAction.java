package game.actions.attributeactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;

public class DrinkAction extends Action {
    private final Ground ground;

    public DrinkAction(Ground ground){
        this.ground = ground;

    }
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        ConsumeAction healAction = new ConsumeAction(1, this.ground, BaseActorAttributes.HEALTH);
        ConsumeAction staminaIncreaseAction = new ConsumeAction((int) Math.round(0.01 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)), this.ground, BaseActorAttributes.STAMINA);

        result += healAction.execute(actor, map) + "\n";
        result += staminaIncreaseAction.execute(actor, map) + "\n";
        return result += "mud water is tasty";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+ " drinks from "+ this.ground;
    }
}
