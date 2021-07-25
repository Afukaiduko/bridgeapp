package controller;

import view.GameSelectView;
import view.HomeView;
import view.SeatingSetupView;

public class HomeController extends BaseController {

    private final HomeView view;

    public HomeController(MainWindowController mainWindowController, HomeView view) {
        super(mainWindowController);
        this.view = view;

        view.getNewGameButton().addActionListener((evt) -> mainWindowController.switchScene(SeatingSetupView.class));
        view.getReplayAGameButton().addActionListener((evt)-> mainWindowController.switchScene(GameSelectView.class));
    }

}
