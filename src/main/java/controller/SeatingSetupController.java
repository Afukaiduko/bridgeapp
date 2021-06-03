package controller;

import enums.Position;
import model.Player;
import model.PlayerDatabase;
import model.SeatingOrderModel;
import view.BiddingView;
import view.SeatingSetupView;

import java.awt.event.ActionEvent;

public class SeatingSetupController extends BaseController {

    private final PlayerDatabase players;
    private final SeatingSetupView view;
    private final SeatingOrderModel model;

    public SeatingSetupController(MainWindowController mainWindowController, PlayerDatabase players, SeatingSetupView view, SeatingOrderModel model) {
        super(mainWindowController);
        this.model = model;
        this.view = view;
        this.players = players;

        view.getAddPlayerButton().addActionListener(this::addPlayer);
        view.getNextButton().addActionListener(this::next);
    }

    private void addPlayer(ActionEvent e) {
        String newPlayerName = view.getNewPlayerTF().getText();
        players.addPlayer(new Player(model.getID(), newPlayerName));

        System.out.println("adding player");
        view.addLatestPlayerToComboBox();

        model.incrementID();
    }

    private void next(ActionEvent e) {
        addPlayersToPlayerPositionsMap();

        if (validPlayerSelection()) {
            if (view.getNorthButton().isSelected()) {
                model.setStartingBidderPosition(Position.NORTH);
            } else if (view.getEastButton().isSelected()) {
                model.setStartingBidderPosition(Position.EAST);
            } else if (view.getSouthButton().isSelected()) {
                model.setStartingBidderPosition(Position.SOUTH);
            } else if (view.getWestButton().isSelected()) {
                model.setStartingBidderPosition(Position.WEST);
            } else {
                System.out.println("Starting bidder not selected, unable to proceed");
                return;
            }
            mainWindowController.switchScene(BiddingView.class);
            System.out.println("Bid starts at " + model.getStartingBidderPosition() + " with " + model.getPlayerPositionsMap().get(model.getStartingBidderPosition()));
        }
    }

    private boolean validPlayerSelection() {
        if (model.getPlayerPositionsMap().size() == 4) {
            return true;
        } else {
            System.out.println("Duplicate player selected, unable to proceed");
            return false;
        }
    }

    private void addPlayersToPlayerPositionsMap() {
        model.putPlayerPosition(Position.NORTH, (Player) view.getCbxNorth().getSelectedItem());
        model.putPlayerPosition(Position.EAST, (Player) view.getCbxEast().getSelectedItem());
        model.putPlayerPosition(Position.SOUTH, (Player) view.getCbxSouth().getSelectedItem());
        model.putPlayerPosition(Position.WEST, (Player) view.getCbxWest().getSelectedItem());
    }
}
