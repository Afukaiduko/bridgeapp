package controller;

import view.BaseView;
import view.MainWindow;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindowController {
    private final Map<Class, BaseView> viewsMap;
    private final MainWindow window;

    public MainWindowController(MainWindow window) {
        this.window = window;
        viewsMap = new HashMap<>();
    }

    public void registerScene(Class className, BaseView scene) {
        viewsMap.put(className, scene);
    }

    public void switchScene(Class className) {
        BaseView scene = viewsMap.get(className);
        if (scene != null) {
            SwingUtilities.invokeLater(() -> {
                window.setContentPane(scene);
                scene.onLoadedView();
                scene.refresh(); // Tell the view to refresh itself.
                window.validate();
            });
        } else {
            System.out.println("Not found");
        }
    }
}
