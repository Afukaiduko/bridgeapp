package controller;

import view.MainWindow;

public class BaseController {
    protected MainWindow window;

    public BaseController(MainWindow window) {
        this.window = window;
    }
}
