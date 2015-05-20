package de.hawlandshut.sgheldd.prak4;

import static de.hawlandshut.sgheldd.prak4.Resource.*;

/**
 * This enum represents all the available building types in the Settlers of Catan game.
 * @author Georg Held
 * Created by s-gheldd on 5/19/15.
 */
public enum Building {

    /**
     * A street. Requiring 1 clay, 1 wood. Generates 1 point.
     */
    Street(1,Clay,Wood),

    /**
     * A house. Requiring 1 sheep, 1 grain, 1 wood, 1 clay. Generates 3 points.
     */
    House(3,Sheep,Grain,Wood,Clay),

    /**
     * A knight. Requiring 1 sheep, 1 grain, 1 stone. Generates 2 points.
     */
    Knight(2,Sheep,Grain,Stone),

    /**
     * A city. Requiring 2 Grain, 3 Stone. Generates 5 points.
     */
    City(5,Grain,Grain,Stone,Stone,Stone);

    private final int[] resources = new int[Resource.NUMRESOURCES];
    private final int points;

    //private redundant
    Building(int points, Resource... resources){
        this.points=points;
        for (Resource resource : resources){
            this.resources[resource.ordinal()]++;
        }
    }

    /**
     * Returns the points of a particular building.
     * @return points of the building
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Returns the number of a particular resource needed for a specific building.
     * @param resource the sought after resource
     * @return the amount of resource needed for construction
     */
    public int getNumResources(Resource resource){
        return this.resources[resource.ordinal()];
    }


    /**
     * Can a particular building be build, using the resources of a single dice throw.
     * @param diceThrow the dice throw that can be used
     * @return true of the building can be build, false otherwise
     */
    public boolean canBuild(DiceThrow diceThrow){
        for (int i = 0; i< this.resources.length; i++){
            if (this.resources[i] > diceThrow.getNumResources(Resource.values()[i]))
                return false;
        }
        return true;
    }

}