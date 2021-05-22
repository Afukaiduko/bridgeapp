package model;

public class Player {

    private int ID;
    private String name;

    public Player(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }
}
