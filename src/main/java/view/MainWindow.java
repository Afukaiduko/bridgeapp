package view;

import constants.Constants;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        this.setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        // Centers the frame in the middle
        this.setLocationRelativeTo(null);

        // Makes sure the program turns off when the main window is closed.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
