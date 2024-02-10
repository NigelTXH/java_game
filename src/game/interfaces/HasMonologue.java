package game.interfaces;

import game.monologue.Monologue;

import java.util.List;
/**
 * An interface used if an actor has a monologue
 * @author Nigel Tan
 */
public interface HasMonologue {
    /**
     * A getter for the monologueList attribute.
     * @return a copy of the monologueList.
     */
    List<Monologue> getMonologue();
    /**
     * A getter for the actor's name etc.
     * @return A string containing the actor's name etc.
     */
    String getMonologueActor();
}
