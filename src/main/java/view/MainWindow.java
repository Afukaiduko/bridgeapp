package view;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame {
    private Map<Class, BaseView> viewsMap;

    public MainWindow() {
        viewsMap = new HashMap<>();
        this.setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        // Centers the frame in the middle
        this.setLocationRelativeTo(null);

        // Makes sure the program turns off when the main window is closed.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void registerPanel(Class className, BaseView view) {
        viewsMap.put(className, view);
    }

    public void switchPanels(Class className) {
        BaseView view = viewsMap.get(className);
        if (view != null) {
            SwingUtilities.invokeLater(() -> {
                this.setContentPane(view);
                view.onLoad(); // Tell the view to refresh itself.
                this.validate();
            });
        } else {
            System.out.println("Not found");
        }
    }

}
