package model;

import java.util.ArrayList;
import java.util.List;

public class GamesDatabase {
    //TODO: change from list to something that is better sorted
    private final List<Game> games;

    public GamesDatabase(){
        games = new ArrayList<>();
    }

    public void addGame(Game game){
        games.add(game);
    }
}
