package model;

import java.util.ArrayList;

public class PlayerDatabase {
    ArrayList<Player>  players;

    public PlayerDatabase() {
        players  = new ArrayList<>();
    }

    public int getNumberOfPlayers(){
        return players.size();
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Player getLatestPlayer(){
        return players.get(players.size()-1);
    }
}