package model;

import java.util.ArrayList;
import java.util.List;

public class GamesDatabase {

    //TODO: change from list to something that is better sorted
    private final List<SaveGame> games;

    public GamesDatabase() {
        games = new ArrayList<>();
    }

    public void addGame(SaveGame game) {
        games.add(game);
    }

    public int getNumGames() {
        return games.size();
    }

    public List<SaveGame> getGames(){
        return games;
    }
}
