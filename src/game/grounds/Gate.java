package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.UnlockAction;
import game.item.CanUnlock;
import game.world.AllGameMaps;
import game.world.AllGameMapsEnum;

import java.util.List;

/**
 * A class used to implement the gate
 * @author Nigel Tan
 */
public class Gate extends Ground {

    private int x;
    private int y;
    private boolean isUnlocked = false;
    /**
     * Constructor for gate.
     */
    public Gate() {
        super('=');
    }

    /**
     * Returns an UnlockAction if a valid key is in the player's inventory or a MoveActorAction if the gate is unlocked.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, collection of actions of either an unlock action or a move actor action.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();


        if(isUnlocked){
            if(location.containsAnActor() && location.getActor().equals(actor)){
                List<AllGameMapsEnum> destinations = this.findCapabilitiesByType(AllGameMapsEnum.class);
                if(destinations.size() > 0){
                    for(AllGameMapsEnum allGameMapsEnum: destinations){
                        GameMap destination = AllGameMaps.getGameMap(allGameMapsEnum);
                        actionList.add(new MoveActorAction(destination.at(x,y), "to "+ allGameMapsEnum.toString()));
                    }
                }
            }

        }
        else{
            actionList.add(checkPlayerHasKey(actor));
        }





        return actionList;
    }

    /**
     * Returns an UnlockAction if a valid key is in the player's inventory.
     * @param actor the Actor that may have the key
     * @return an UnlockAction
     */
    public UnlockAction checkPlayerHasKey(Actor actor){
        int size = this.findCapabilitiesByType(AllGameMapsEnum.class).size();
        int counter = 0;
        for(Item item: actor.getItemInventory()){
            if(item.hasCapability(CanUnlock.CANUNLOCK)){
                List<AllGameMapsEnum> availableUnlocks = item.findCapabilitiesByType(AllGameMapsEnum.class);
                if(availableUnlocks.size() > 0){
                    for(AllGameMapsEnum allGameMapsEnum: availableUnlocks){
                        if(this.hasCapability(allGameMapsEnum)){
                            counter ++;
                        }
                    }
                }
            }
        }

        if(counter == size){
            return new UnlockAction(this);
        }
        return null;
    }
    /**
     * Method to set the destination's X coordinate.
     * @param x set the destination's X coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Method to set the destination's Y coordinate.
     * @param y set the destination's Y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Method to set the gate to be unlocked.
     */
    public void unlockGate() {
        isUnlocked = true;
    }

    public void lockGate(){
        isUnlocked = false;
    }
    /**
     * If the gate is unlocked, actors can move to the gate, else the actor cannot.
     * @return true if unlocked, else false.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(isUnlocked){
            return true;
        }
        return false;
    }
}
