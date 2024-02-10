package game.interfaces;

/**
 * An interface used if the actor can be talked to
 * @author Nigel Tan
 */
public interface AbleToTalkInterface {
    /**
     * A setter for the isTalking attribute.
     * @param bool        set isTalking to true or false
     */

    void talkingTo(boolean bool);

    /**
     * A getter for the isTalking attribute.
     * @return a copy of the boolean.
     */
    boolean getIsTalking();
}
