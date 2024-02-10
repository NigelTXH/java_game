package game.abilities;

import game.buffactions.BuffAction;

import java.util.ArrayList;
import java.util.List;
/**
 * Abstract class to create abilities
 * @author Nigel Tan
 */
public abstract class CreateAbility {

    List<BuffAction> effects = new ArrayList<>();
    private final int expiry;
    private Ability abilityName;
    private int duration;
    private final float stamina;

    /**
     * Constructor for CreateAbility.
     * @param abilityName the ability name.
     * @param expiry when the buff expires.
     * @param stamina how much stamina is used by the player.
     */
    public CreateAbility(Ability abilityName, int expiry, float stamina){
        this.expiry = expiry;
        this.abilityName = abilityName;
        this.duration = expiry;
        this.stamina = stamina;
    }

    /**
     * Method which get the abilityName.
     * @return the ability name is returned.
     */
    public Ability getAbilityName() {
        return abilityName;
    }
    /**
     * Method which get the effects.
     * @return the effects are returned.
     */
    public List<BuffAction> getEffects() {
        return effects;
    }
    /**
     * Method which get the stamina.
     * @return the stamina is returned.
     */
    public float getStamina() {
        return stamina;
    }
    /**
     * Method which reduces the duration.
     */
    public void reduceDuration(){
        duration--;
    }
    /**
     * Method which checks if the duration is 0 meaning it has expired.
     * @return a boolean value where true if it has expired.
     */
    public boolean expire(){

        if(duration == 0){
            return true;
        }
        return false;
    }
    /**
     * Method to renew the duration
     */
    public void renew(){
        duration = expiry;
    }
    /**
     * Method to return the abilityName as a string.
     * @return the ability name as a string is returned.
     */
    @Override
    public String toString() {
        return abilityName.toString();
    }
}
