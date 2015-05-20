package de.hawlandshut.sgheldd.prak4;

/**
 * Created by s-gheldd on 5/20/15.
 */
public class Main {


    public static void main(String...args){
        int points = 0;
        for (int i = 0; i<10;i++){
            System.out.println("Round " + i + ":");
            DiceThrow diceThrow = new DiceThrow();
            points += buildBestGreedy(diceThrow);
            System.out.println("Points: "+points);
        }
    }

    public static int buildBestGreedy(DiceThrow diceThrow){
        int points = 0;
        while (Building.City.canBuild(diceThrow)){
            diceThrow.reduceByBuilding(Building.City);
            points += Building.City.getPoints();
            System.out.println("Build City");
        }
        while (Building.House.canBuild(diceThrow)){
            diceThrow.reduceByBuilding(Building.House);
            points += Building.House.getPoints();
            System.out.println("Build House");
        }
        while (Building.Knight.canBuild(diceThrow)){
            diceThrow.reduceByBuilding(Building.Knight);
            points += Building.Knight.getPoints();
            System.out.println("Build Knight");
        }
        while (Building.Street.canBuild(diceThrow)){
            diceThrow.reduceByBuilding(Building.Street);
            points += Building.Street.getPoints();
            System.out.println("Build Street");
        }
        return points;
    }
}
