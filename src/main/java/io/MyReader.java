package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyReader {
    /**
     * Reads the contents of the file, returns as String
     */
    public String readFromFile(String dir, String fileName) {
        try {
            Path path = Paths.get(dir, fileName);
            byte[] bytes = Files.readAllBytes(path);

            String contents = new String(bytes, "UTF-8");

            System.out.println(contents);
            return contents;
        } catch (IOException e) {
            System.out.println("Could not read file " + fileName);
            e.printStackTrace();
        }
        return null;
    }
}
