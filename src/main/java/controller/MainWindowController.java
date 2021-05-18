package controller;

import view.BaseView;
import view.MainWindow;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindowController {
    private Map<Class, BaseView> viewsMap;
    private MainWindow window;

    public MainWindowController(MainWindow window) {
        this.window = window;
        viewsMap = new HashMap<>();
    }

    public void registerPanel(Class className, BaseView view) {
        viewsMap.put(className, view);
    }

    public void switchPanels(Class className) {
        BaseView view = viewsMap.get(className);
        if (view != null) {
            SwingUtilities.invokeLater(() -> {
                window.setContentPane(view);
                view.onLoad(); // Tell the view to refresh itself.
                window.validate();
            });
        } else {
            System.out.println("Not found");
        }
    }
}
