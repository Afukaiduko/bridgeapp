package controller;

public abstract class BaseController {
    protected MainWindowController mainWindowController;

    public BaseController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
