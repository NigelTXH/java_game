package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Monologue class. A monologue can have a default and an alternate if a condition is fulfilled
 * Created by:
 * @author Nigel Tan
 * Modified by:
 * @author Nigel Tan
 */
public class Monologue {
    private final String defaultMonologue;
    private final String altMonologue;
    private final List<MonologueEnum> condition = new ArrayList<>();

    /**
     * The constructor of the Monologue class if there is a condition. Null is added to the conditions list.
     * The default is also declared here.
     * @param defaultMonologue The monologue by default
     */
    public Monologue(String defaultMonologue){
        this.defaultMonologue = defaultMonologue;
        this.altMonologue = null;
        this.condition.add(null);
    }

    /**
     * The constructor of the Monologue class if there is a condition. Conditions are added to the conditions list.
     * The default and alternate monologue are also declared here.
     * @param defaultMonologue The monologue if the condition is not fulfilled
     * @param altMonologue  The monologue if the condition is fulfilled
     * @param condition The conditions to be added for the alternate monologue to occur
     */
    public Monologue(String defaultMonologue, String altMonologue, MonologueEnum... condition){
        this.defaultMonologue = defaultMonologue;
        this.altMonologue = altMonologue;
        for(MonologueEnum monologueEnum: condition){
            this.condition.add(monologueEnum);
        }
    }

    /**
     * Checks if the player has fulfilled all the conditions for the alternate monologue to appear
     * instead.
     * @param player The actor to check the conditions from
     * @return true if all the conditions are fulfilled, false otherwise.
     */
    public boolean checkPlayerConditions(Actor player){
        int counter = 0;
        for(MonologueEnum monologueEnum: condition){
            if(player.hasCapability(monologueEnum)){
                counter++;
            }
        }

        if(counter == condition.size()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * What the actor with the monologue will say based on if the player has fulfilled the conditions.
     * If the conditions are fulfilled, the alternate monologue is used. Else use the default monologue.
     * If the default monologue is null, return "..." instead.
     * @param player The actor to check the conditions from
     * @return true if all the conditions are fulfilled, false otherwise.
     */
    public String sayMonologue(Actor player){
        if(condition.get(0) != null){
            if(checkPlayerConditions(player)){
                return altMonologue;
            }
        }

        if(defaultMonologue == null){
            return "...";
        }

        return defaultMonologue;
    }
}
