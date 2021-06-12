package io;

import com.google.gson.Gson;
import constants.Constants;
import model.CounterModel;
import model.GamesDatabase;
import model.PlayerDatabase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOManager {
    private final Gson gson;
    private final MyReader reader;
    private final MyWriter writer;

    public IOManager() {
        gson = new Gson();
        reader = new MyReader();
        writer = new MyWriter();
    }

    public CounterModel readCounterModel() {
        Path path = Paths.get(Constants.SAVE_DIRECTORY, Constants.COUNTER_FILE);
        if (!Files.exists(path)) {
            System.out.println("Counter file does not exist, creating new one");
            // File does not exist, let's create a default one.
            saveCounterModel(new CounterModel());
        }

        String json = reader.readFromFile(Constants.SAVE_DIRECTORY, Constants.COUNTER_FILE);

        // Convert json string to model
        CounterModel model = gson.fromJson(json, CounterModel.class);
        System.out.println(json);
        System.out.println(model.getCounter());
        return model; // Technically can combine with above line, but writing explicitly here as an example to be more clear
    }

    public void saveCounterModel(CounterModel model) {
        // Convert Java object type to JSON string.
        String json = gson.toJson(model);

        // Write the json.
        writer.write(Constants.SAVE_DIRECTORY, Constants.COUNTER_FILE, json);
    }

    public PlayerDatabase readPlayerDatabaseModel() {
        Path path = Paths.get(Constants.SAVE_DIRECTORY, Constants.PLAYERS_FILE);
        if (!Files.exists(path)) {
            System.out.println("Players file does not exist, creating new one");
            // File does not exist, let's create a default one.
            savePlayerDatabaseModel(new PlayerDatabase());
        }

        String json = reader.readFromFile(Constants.SAVE_DIRECTORY, Constants.PLAYERS_FILE);

        // Convert json string to model
        PlayerDatabase model = gson.fromJson(json, PlayerDatabase.class);
        System.out.println(json);
        System.out.println("Number of players: " + model.getNumberOfPlayers());
        return model; // Technically can combine with above line, but writing explicitly here as an example to be more clear
    }

    public void savePlayerDatabaseModel(PlayerDatabase model) {
        // Convert Java object type to JSON string.
        String json = gson.toJson(model);

        // Write the json.
        writer.write(Constants.SAVE_DIRECTORY, Constants.PLAYERS_FILE, json);
    }

    public GamesDatabase readGamesDatabaseModel() {
        Path path = Paths.get(Constants.SAVE_DIRECTORY, Constants.GAMES_FILE);
        if (!Files.exists(path)) {
            System.out.println("Games file does not exist, creating new one");
            // File does not exist, let's create a default one.
            saveGamesDatabaseModel(new GamesDatabase());
        }

        String json = reader.readFromFile(Constants.SAVE_DIRECTORY, Constants.GAMES_FILE);

        // Convert json string to model
        GamesDatabase model = gson.fromJson(json, GamesDatabase.class);
        System.out.println(json);
        System.out.println("Games Loaded: "+model.getNumGames());
        return model; // Technically can combine with above line, but writing explicitly here as an example to be more clear
    }

    public void saveGamesDatabaseModel(GamesDatabase model) {
        // Convert Java object type to JSON string.
        String json = gson.toJson(model);

        // Write the json.
        writer.write(Constants.SAVE_DIRECTORY, Constants.GAMES_FILE, json);
    }
}
