package controller;

import model.Player;
import model.PlayerDatabase;
import model.SeatingOrderModel;
import view.SeatingSetupView;

import java.awt.event.ActionEvent;

public class SeatingSetupController extends BaseController{

    private PlayerDatabase players;
    private SeatingSetupView view;
    private SeatingOrderModel model;

    public SeatingSetupController(MainWindowController mainWindowController, PlayerDatabase players, SeatingSetupView view, SeatingOrderModel model){
        super(mainWindowController);
        this.model = model;
        this.view = view;
        this.players = players;

        view.getAddPlayerButton().addActionListener(this::addPlayer);
    }

    private void addPlayer(ActionEvent e){
        String newPlayerName = view.getNewPlayerTF().getText();
        players.addPlayer(new Player(model.getID(), newPlayerName));
        System.out.println("adding player");
        view.addLatestPlayerToComboBox();
    }
}
