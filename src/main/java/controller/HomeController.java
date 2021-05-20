package controller;

import view.HomeView;
import view.SeatingSetupView;

import java.awt.event.ActionEvent;

public class HomeController extends BaseController{

    private HomeView view;

    public HomeController(MainWindowController mainWindowController, HomeView view){
        super(mainWindowController);
        this.view = view;

        view.getNewGameButton().addActionListener((evt) -> mainWindowController.switchScene(SeatingSetupView.class));
    }

}
