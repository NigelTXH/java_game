package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.abilities.FocusAbility;
import game.actors.Player;
import game.actors.abandonedvillageactors.WanderingUndead;
import game.actors.abxervyerbattleactors.Abxervyer;
import game.actors.abxervyerbattleactors.ForestKeeperBoss;
import game.actors.abxervyerbattleactors.RedWolfBoss;
import game.actors.ancientwoodactors.ForestKeeper;
import game.actors.ancientwoodactors.IsolatedTraveller;
import game.actors.ancientwoodactors.RedWolf;
import game.actors.blacksmith.Andre;
import game.actors.blacksmith.BlackSmithEnum;
import game.actors.burialgroundactors.HollowSoldier;
import game.actors.owergrwonsantuaryactors.EldenTreeGuardian;
import game.actors.owergrwonsantuaryactors.LivingBranch;
import game.displays.FancyMessage;
import game.grounds.Gate;
import game.grounds.Puddle;
import game.grounds.spawners.*;
import game.item.Key;
import game.item.consumables.BloodBerry;
import game.item.consumables.StaminaVial;
import game.upgrade.Upgradable;
import game.weapons.Knife;
import game.weapons.Sword;
import game.weather.Weather;
import game.world.AllGameMaps;
import game.world.AllGameMapsEnum;
import game.world.ControlMapInitialise;
import game.world.InitialiseGameMap;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Nigel Tan
 *
 */
public class Application {
    /**
     * How the application runs
     * @param args not sure to be honest
     */
    public static void main(String[] args) {

        World world = new World(new Display());



        GameMap gameMap = AllGameMapsEnum.ABANDONEDVILLAGE.createGameMap();
        GameMap gameMap2 = AllGameMapsEnum.BURIALGROUND.createGameMap();
        GameMap gameMap3 = AllGameMapsEnum.ANCIENT_WOODS.createGameMap();
        GameMap gameMap4 = AllGameMapsEnum.OVERGROWN_SANCTUARY.createGameMap();
        AllGameMaps.addGameMap(gameMap, world, AllGameMapsEnum.ABANDONEDVILLAGE);
        AllGameMaps.addGameMap(gameMap2, world, AllGameMapsEnum.BURIALGROUND);
        AllGameMaps.addGameMap(gameMap3, world, AllGameMapsEnum.ANCIENT_WOODS);
        AllGameMaps.addGameMap(gameMap4, world, AllGameMapsEnum.OVERGROWN_SANCTUARY);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("The Abstracted One", '@', 200);
        IsolatedTraveller isolatedTraveller = new IsolatedTraveller(player);
        FocusAbility focus = new FocusAbility(5,0.1f,90f, player.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.2f);
        Sword broadsword = new Sword("broad sword", '1', 110, 80, focus, Upgradable.ATTACK);
        Knife greatKnife = new Knife("great knife", '>', 75, 70, Upgradable.HITRATE);
        Gate gate1 = AllGameMaps.addGate(gameMap, 30, 5, 20, 10,AllGameMapsEnum.BURIALGROUND);
        Gate gate2 = AllGameMaps.addGate(gameMap2, 20, 10, 30, 5,AllGameMapsEnum.ABANDONEDVILLAGE);
        Gate gate3 = AllGameMaps.addGate(gameMap3, 0,0,19,10,AllGameMapsEnum.BURIALGROUND);
        Gate gate4 = AllGameMaps.addGate(gameMap2, 19,10,0,0,AllGameMapsEnum.ANCIENT_WOODS);

        Weather weather = new Weather();
        Actor boss = new Abxervyer(player, weather);
        gameMap3.at(12,12).addActor(boss);
        gameMap3.at(20,12).setGround(new EmptyHutBoss(new ForestKeeperBoss(player, weather), 15,weather));
        gameMap3.at(20,12).setGround(new BushBoss(new RedWolfBoss(weather), 30, weather));
        gameMap.at(28,5).addItem(broadsword);
        gameMap.at(30,10).setGround(new Graveyard(new WanderingUndead()));
        gameMap2.at(30,10).setGround(new Graveyard(new HollowSoldier()));
        gameMap3.at(30,2).setGround(new EmptyHut(new ForestKeeper(player), 15));
        gameMap3.at(50,2).setGround(new Bush(new RedWolf(),30));
        gameMap3.at(0,1).addItem(new BloodBerry(100));
        gameMap3.at(0,1).addItem(broadsword);
        gameMap3.at(0,2).setGround(new Puddle());

        gameMap4.at(30,10).setGround(new Graveyard(new HollowSoldier()));
        //testing
        Key oldKey = new Key(100);
        oldKey.canUnlock(AllGameMapsEnum.BURIALGROUND);
        oldKey.canUnlock(AllGameMapsEnum.ANCIENT_WOODS);

        Key key2 = new Key(100);
        key2.canUnlock(AllGameMapsEnum.ANCIENT_WOODS);
        key2.canUnlock(AllGameMapsEnum.OVERGROWN_SANCTUARY);

        gameMap.at(29,5).addItem(oldKey);
        world.addPlayer(player, gameMap.at(28, 6));
        gameMap.at(28,6).addItem(new StaminaVial(25));
        AllGameMaps.addGate(gameMap3, 0, 3, 20, 10, AllGameMapsEnum.ANCIENT_WOODS, AllGameMapsEnum.OVERGROWN_SANCTUARY);
        gameMap3.at(0,1).addItem(key2);
        gameMap3.at(1,0).addActor(isolatedTraveller);;
        gameMap.at(30,6).addActor(new Andre(BlackSmithEnum.ANDRE));
//        gameMap3.locationOf(player).addItem(oldKey);

        gameMap4.at(0,5).setGround(new EmptyHut(new EldenTreeGuardian(),20));
        gameMap4.at(5,6).setGround(new Bush(new LivingBranch(),90));

        InitialiseGameMap initMap1 = new InitialiseGameMap(AllGameMapsEnum.ABANDONEDVILLAGE,gameMap, player);
        InitialiseGameMap initMap2 = new InitialiseGameMap(AllGameMapsEnum.BURIALGROUND,gameMap2, player);
        InitialiseGameMap initMap3 = new InitialiseGameMap(AllGameMapsEnum.ANCIENT_WOODS,gameMap3, player);
        InitialiseGameMap initMap4 = new InitialiseGameMap(AllGameMapsEnum.OVERGROWN_SANCTUARY,gameMap4, player);

        //test
        gate1.unlockGate();
        initMap1.addGate(gate1);
        initMap2.addGate(gate2);
        initMap2.addGate(gate4);
        initMap3.addGate(gate3);

        initMap3.addBoss(boss);

        ControlMapInitialise.addInitMap(AllGameMapsEnum.ABANDONEDVILLAGE, initMap1);
        ControlMapInitialise.addInitMap(AllGameMapsEnum.BURIALGROUND, initMap2);
        ControlMapInitialise.addInitMap(AllGameMapsEnum.ANCIENT_WOODS, initMap3);
        ControlMapInitialise.addInitMap(AllGameMapsEnum.OVERGROWN_SANCTUARY, initMap4);
        world.run();
    }
}
