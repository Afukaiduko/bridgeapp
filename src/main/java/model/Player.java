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

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object anObject){
        if(this == anObject){
            return true;
        }
        if(anObject instanceof Player){
            if(this.getID() == ((Player) anObject).getID()){
                return true;
            }
        }
        return false;
    }
}
