package de.hawlandshut.sgheldd.prak4;

import java.util.Random;

/**
 * This class represents a dace throw for every resource in the game Settlers of Catan.
 *
 * @see Resource
 * @see Building
 * @author Georg Held
 * Created by s-gheldd on 5/19/15.
 */
public class DiceThrow {
    private final static Random RANDOM = new Random();
    private final static int NUMBERROLLS = 6;
    private final int[] resources = new int[Resource.NUMRESOURCES];

    /**
     * A throw of dice for every resource.
     */
    public DiceThrow(){
        RANDOM.setSeed(System.nanoTime());

        for (int i = 0; i < this.resources.length; i++){
            this.resources[i] = RANDOM.nextInt(NUMBERROLLS);
        }
    }

    /**
     * Gets the amount of a single resource, that is available.
     * @param resource the resource in question
     * @return the amount of the resource, that is available
     * @see Resource
     */
    public int getNumResources(Resource resource){
        return this.resources[resource.ordinal()];
    }

    /**
     * Reduces the available resources, by the amounts needed to build a single building.
     * @param building the to be build building
     * @see Building
     */
    public void reduceByBuilding(Building building) {
        if (building.canBuild(this)) {
            for (int i = 0; i < this.resources.length; i++) {
                this.resources[i] -= building.getNumResources(Resource.values()[i]);
            }
        } else assert false;
    }
}
