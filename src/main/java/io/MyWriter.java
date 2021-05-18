package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyWriter {
    /**
     * Writes to file, overwriting if the file exists.
     */
    public void write(String dir, String fileName, String toWrite) {
        checkDirectoryExists(dir);
        try {
            Path path = Paths.get(dir, fileName);
            byte[] bytes = toWrite.getBytes();

            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println("Could not save file");
            e.printStackTrace();
        }
    }

    private void checkDirectoryExists(String dir) {
        Path path = Paths.get(dir);
        if (!Files.exists(path)) {
            // If the directory doesn't exist, create all necessary directories.
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Could not create directories");
                e.printStackTrace();
            }
        }
    }
}
