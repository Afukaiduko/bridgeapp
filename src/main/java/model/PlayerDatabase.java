package model;

import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase {
    List<Player> players;

    public PlayerDatabase() {
        players  = new ArrayList<>();
    }

    public int getNumberOfPlayers(){
        return players.size();
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Player getLatestPlayer(){
        return players.get(players.size()-1);
    }
}