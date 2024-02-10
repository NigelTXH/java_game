package game.actors.blacksmith;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.talkactions.ListenAction;
import game.interfaces.HasMonologue;
import game.monologue.Monologue;
import game.monologue.MonologueEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing the blacksmith named Andre, who is a blacksmith that the player can upgrade or listen to.
 * Created by:
 * @author Nigel Tan
 * Modified by:
 * @author Nigel Tan
 */
public class Andre extends BlackSmith implements HasMonologue {
    private List<Monologue> monologueList = new ArrayList<>();
    /**
     * The constructor of the Andre class. Upgradable and listen actions are added here.
     */
    public Andre(BlackSmithEnum blackSmithEnum){
        super("Andre", 'B', 200);
        this.addCapability(blackSmithEnum);

        monologueList.add(new Monologue("I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing."));
        monologueList.add(new Monologue("It’s dangerous to go alone. Take my creation with you on your adventure!"));
        monologueList.add(new Monologue("Ah, it’s you. Let’s get back to make your weapons stronger."));
        monologueList.add(new Monologue("Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!", "Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.", MonologueEnum.DEFEATED_AXERYVER));
        monologueList.add(new Monologue(null, "Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.", MonologueEnum.HAS_GREAT_KNIFE));
    }

    /**
     * As well as with the normal allowable actions for blacksmith. Andre also adds a listen action
     * if the talk action was performed prior to listen to any monologue.
     *
     * @param otherActor the Actor that might perform the actions given
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of possible actions for the player
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(super.allowableActions(otherActor, direction, map));

        if(this.getIsTalking() == true){
            actions.add(new ListenAction(this));
        }

        return actions;
    }

    /**
     * A getter for the monologueList attribute.
     * @return a copy of the monologueList.
     */
    @Override
    public List<Monologue> getMonologue(){
        return Collections.unmodifiableList(monologueList);
    }

    /**
     * A getter for the actor's name etc.
     * @return A string containing the actor's name etc.
     */
    @Override
    public String getMonologueActor() {
        return this.toString();
    }
}
