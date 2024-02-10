package game.actions.talkactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.HasMonologue;
import game.monologue.Monologue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing how the player will listen to the actor's monologue. This class
 * chooses a possible monologue that was added or if the condition is fulfilled.
 * Created by:
 * @author Nigel Tan
 * Modified by:
 * @author Nigel Tan
 */
public class ListenAction extends Action {
    private HasMonologue monologueActor;
    /**
     * The constructor of the ListenAction class.
     * @param monologueActor The actor doing the monologue
     */
    public ListenAction(HasMonologue monologueActor){
        this.monologueActor = monologueActor;
    }

    /**
     * A method executed by the player to listen to the actor's possible monologue.
     * When executed, it first checks if the actor has any monologues, when the
     * monologues are more than 1, then an array list consisting of the possible dialogues
     * is created. If the monologue does not give "...", then add it to the possible dialogues
     * because "..." represents that no option to pick this dialogue. If the size is just 1 or 0,
     * then it is possible to print "..." as the result meaning that the actor has nothing
     * to talk about.
     * @param actor the actor performing this action
     * @param map the GameMap
     * @return the result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random random = new Random();
        String result;
        if(monologueActor.getMonologue().size() > 0) {
            if(monologueActor.getMonologue().size() > 1){
                List<String> possibleDialogue = new ArrayList<>();
                for(Monologue subResult: monologueActor.getMonologue()){
                    String monologue = subResult.sayMonologue(actor);
                    if(!(monologue.equals("..."))){
                        possibleDialogue.add(monologue);
                    }
                }

                result = possibleDialogue.get(random.nextInt(possibleDialogue.size()));
            }
            else{
                result = monologueActor.getMonologue().get(0).sayMonologue(actor);
        }   }
        else{
            result = "...";
        }
        return monologueActor.getMonologueActor() + ": " + result;
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Listen to " + monologueActor.getMonologueActor();
    }
}
