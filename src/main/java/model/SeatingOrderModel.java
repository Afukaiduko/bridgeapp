package model;

public class SeatingOrderModel {
    private int nextID;

    public SeatingOrderModel(PlayerDatabase model){
        this.nextID = model.getNumberOfPlayers() + 1;
    }

    public void incrementID(){
        nextID++;
    }

    public int getID(){
        return nextID;
    }


}
