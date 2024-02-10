package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Behaviour;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class FollowBehaviour implements Behaviour {

	private final Actor target;

	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		double currentDistance = distance(here, there);
		Exit currentExit = null;
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				double newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					currentDistance = newDistance;
					currentExit = exit;
				}
			}
		}
		if (currentExit != null){
			return new MoveActorAction(currentExit.getDestination(), currentExit.getName());
		}

		return null;
	}

	/**
	 * Compute the Euclidean distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private double distance(Location a, Location b) {
		return Math.sqrt(Math.pow((a.x()- b.x()),2) + Math.pow((a.y() - b.y()),2));
	}
}
