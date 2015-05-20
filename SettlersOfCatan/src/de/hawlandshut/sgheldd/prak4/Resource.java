package de.hawlandshut.sgheldd.prak4;

/**
 * This enum represents the resources available in the Settlers of Catan game.
 *
 * @author Georg Held
 * Created by s-gheldd on 5/19/15.
 */
public enum Resource {
    Sheep, Clay, Wood, Grain, Stone;

    /**
     * The number of different resources.
     */
    static final int NUMRESOURCES = values().length;
}
