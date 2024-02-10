package game.interfaces;

/**
 * An interface used if an actor can talk
 * @author Nigel Tan
 */
public interface TalkingInterface {
    /**
     * A setter for a boolean attribute.
     * @param bool        set  a boolean to true or false
     */
    void talkingTo(boolean bool);
    /**
     * A getter for a boolean attribute.
     * @return a copy of the boolean.
     */
    boolean getIsTalking();
}
