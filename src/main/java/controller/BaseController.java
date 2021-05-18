package controller;

public abstract class BaseController {
    protected MainWindowController controller;

    public BaseController(MainWindowController controller) {
        this.controller = controller;
    }
}
