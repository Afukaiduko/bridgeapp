package io;

import com.google.gson.Gson;
import constants.Constants;
import model.CounterModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOManager {
    private Gson gson;
    private MyReader reader;
    private MyWriter writer;

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


}
