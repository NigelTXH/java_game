package game.world;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Void;
import game.grounds.*;

import java.util.Arrays;
import java.util.List;
/**
 * Use this enum to store all the maps that could be used by the world.
 * @author Nigel Tan
 */
public enum AllGameMapsEnum {

    /**
     * The abandoned village layout.
     */
    ABANDONEDVILLAGE(Arrays.asList(
            "...........................................................",
            "...#######.................................................",
            "...#__.....................................................",
            "...#..___#.................................................",
            "...###.###................#######..........................",
            "..........................#_____#..........................",
            "........~~................#_____#..........................",
            ".........~~~..............###_###..........................",
            "...~~~~~~~~................................................",
            "....~~~~~.................................###..##..........",
            "~~~~~~~...................................#___..#..........",
            "~~~~~~....................................#..___#..........",
            "~~~~~~~~~.................................#######.........."), new FancyGroundFactory(new Dirt(),
            new Wall(), new Floor(), new Puddle(), new Void(),  new Gate())),
    /**
     * The burial ground layout.
     */
    BURIALGROUND(Arrays.asList(
                         "...........+++++++........~~~~~~++....~~",
                         "...........++++++.........~~~~~~+.....~~",
                         "............++++...........~~~~~......++",
                         "............+.+.............~~~.......++",
                         "..........++~~~.......................++",
                         ".........+++~~~....#######...........+++",
                         ".........++++~.....#_____#.........+++++",
                         "..........+++......#_____#........++++++",
                         "..........+++......###_###.......~~+++++",
                         "..........~~.....................~~...++",
                         "..........~~~..................++.......",
                         "...........~~....~~~~~.........++.......",
                         "......~~....++..~~~~~~~~~~~......~......",
                         "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                         "....+~~~~..++++++++~~~..~~~~~..~~~~~...."), new FancyGroundFactory(new Dirt(),
            new Wall(), new Floor(), new Puddle(), new Void())),

    ANCIENT_WOODS(Arrays.asList(
            "...........................................................",
            "...#######.................................................",
            "...#_____#.................................................",
            "...#_____#.................................................",
            "...###_###................#######..........................",
            "..........................#_____#..........................",
            "........~~................#_____#..........................",
            ".........~~~..............###_###..........................",
            "...~~~~~~~~................................................",
            "....~~~~~................###..##...........................",
            "~~~~~~~..................#___..#...........................",
            "~~~~~~...................#..___#...........................",
            "~~~~~~~~~................#######..........................."), new FancyGroundFactory(new Dirt(),
            new Wall(), new Floor(), new Puddle(), new Void())),


    OVERGROWN_SANCTUARY(Arrays.asList("++++.....++++........++++~~~~~.......~~~..........",
            "++++......++.........++++~~~~.........~...........",
            "+++..................+++++~~.......+++............",
            "....................++++++......++++++............",
            "...................++++........++++++~~...........",
            "...................+++.........+++..~~~...........",
            "..................+++..........++...~~~...........",
            "~~~...........................~~~..~~~~...........",
            "~~~~............+++..........~~~~~~~~~~...........",
            "~~~~............+++.........~~~~~~~~~~~~..........",
            "++~..............+++.......+~~........~~..........",
            "+++..............+++......+++..........~~.........",
            "+++..............+++......+++..........~~.........",
            "~~~..............+++......+++..........~~~........",
            "~~~~.............+++......+++..........~~~........"), new FancyGroundFactory(new Dirt(),
            new Wall(), new Floor(), new Puddle(), new Void()));

    private List<String> map;
    private FancyGroundFactory fancyGroundFactory;
    /**
     * Constructor for AllGameMapsEnum.
     * @param map what the location looks like
     * @param fancyGroundFactory creating a valid ground for the map
     */
    AllGameMapsEnum(List<String> map, FancyGroundFactory fancyGroundFactory){
        this.map = map;
        this.fancyGroundFactory = fancyGroundFactory;
    }
    /**
     * Method to get the map of a location.
     * @return the map of a location
     */
    public List<String> getMap() {
        return map;
    }
    /**
     * Method to get the fancy ground factory of a location.
     * @return the fancy ground factory of a location
     */
    public FancyGroundFactory getFancyGroundFactory() {
        return fancyGroundFactory;
    }
    /**
     * Method to create the gameMap.
     * @return the gameMap
     */
    public GameMap createGameMap(){
        return new GameMap(this.fancyGroundFactory, this.map);
    }
}
