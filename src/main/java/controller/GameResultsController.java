package controller;

import model.*;
import view.GameResultsView;

import java.awt.event.ActionEvent;

public class GameResultsController extends BaseController {
    private final InGameModel inGameModel;
    private final BiddingModel biddingModel;
    private final SeatingOrderModel seatingOrderModel;
    private final GamesDatabase gamesDatabase;
    private GameResultsView view;

    public GameResultsController(MainWindowController mainWindowController, GameResultsView view, InGameModel inGameModel, BiddingModel biddingModel, SeatingOrderModel seatingOrderModel, GamesDatabase gamesDatabase) {
        super(mainWindowController);
        this.view = view;
        this.inGameModel = inGameModel;
        this.biddingModel = biddingModel;
        this.seatingOrderModel = seatingOrderModel;
        this.gamesDatabase = gamesDatabase;

        view.getSaveGameYesButton().addActionListener(this::handleYesButton);
    }

    private void handleYesButton(ActionEvent e) {
        SaveGame saveGame = new SaveGame(inGameModel.getGame(), biddingModel.getBiddingSequence(), seatingOrderModel.getStartingBidderPosition());
        gamesDatabase.addGame(saveGame);
        view.setAnsweredSaveGame(true);
        view.refresh();
    }

    private void handleNoButton(ActionEvent e) {
        view.setAnsweredSaveGame(true);
        view.refresh();
    }
}
