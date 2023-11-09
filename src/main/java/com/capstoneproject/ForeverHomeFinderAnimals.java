package com.capstoneproject;

public class ForeverHomeFinderAnimals {
    String[] Dog;
    String[] Cat;
    String[] Lizard;
    String[] Turtle;
    String[] Fish;
    String[] Bird;
    String[] Rabbit;
    String[] Ferret;

    public ForeverHomeFinderAnimals() {
        Dog = null;
        Cat = null;
        Lizard = null;
        Turtle = null;
        Fish = null;
        Bird = null;
        Rabbit = null;
        Ferret = null;
    }
    public ForeverHomeFinderAnimals(String[] dog, String[] cat, String[] lizard, String[] turtle, String[] fish, String[] bird, String[] rabbit, String[] ferret) {
        Dog = dog;
        Cat = cat;
        Lizard = lizard;
        Turtle = turtle;
        Fish = fish;
        Bird = bird;
        Rabbit = rabbit;
        Ferret = ferret;
    }

    public String[] getDog() {
        return Dog;
    }

    public void setDog(String[] dog) {
        Dog = dog;
    }

    public String[] getCat() {
        return Cat;
    }

    public void setCat(String[] cat) {
        Cat = cat;
    }

    public String[] getLizard() {
        return Lizard;
    }

    public void setLizard(String[] lizard) {
        Lizard = lizard;
    }

    public String[] getTurtle() {
        return Turtle;
    }

    public void setTurtle(String[] turtle) {
        Turtle = turtle;
    }

    public String[] getFish() {
        return Fish;
    }

    public void setFish(String[] fish) {
        Fish = fish;
    }

    public String[] getBird() {
        return Bird;
    }

    public void setBird(String[] bird) {
        Bird = bird;
    }

    public String[] getRabbit() {
        return Rabbit;
    }

    public void setRabbit(String[] rabbit) {
        Rabbit = rabbit;
    }

    public String[] getFerret() {
        return Ferret;
    }

    public void setFerret(String[] ferret) {
        Ferret = ferret;
    }
    public String toString(){
        return("The animal you searched for is: " );
    }
}
