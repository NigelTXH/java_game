package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.world.ControlMapInitialise;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Nigel Tan
 */
public class Player extends Actor {
    /**
     * Constructor for player.
     * The player has stamina as well as 2 capabilities.
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */

    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.STAMINA,new BaseActorAttribute(200));
        this.getIntrinsicWeapon();
        this.addCapability(PlayerCapability.IS_PLAYER);
    }

    /**
     * At each turn, select possible actions in the console menu.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
//        System.out.println("hello");
//        if (!isConscious()){
//            ControlMapInitialise.resetAll();
//            System.out.println("hello");
//        }
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();


        // return/print the console menu
        Menu menu = new Menu(actions);
        this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, Math.round(this.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.01f));

        return menu.showMenu(this, display);
    }

    /**
     * How the player stats will be shown in the console menu.
     * @return string with the player, the player's health and the player's stamina.
     */
    @Override
    public String toString() {
        return super.toString() + " (" +
                this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.STAMINA) +
                ")";
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        ControlMapInitialise.respawn(this, map);
        ControlMapInitialise.resetAll();
        return this + "respawned.";
    }

    /**
     * Increase player's damage to 15.
     * @return the intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15,"punch",80);
    }
}
