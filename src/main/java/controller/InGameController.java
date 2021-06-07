package controller;

import enums.Position;
import enums.Suit;
import model.Card;
import model.Game;
import model.InGameModel;
import model.Round;
import view.HomeView;
import view.InGameView;

import java.awt.event.ActionEvent;
import java.util.Map;

public class InGameController extends BaseController {

    private final InGameView view;
    private final InGameModel model;

    public InGameController(MainWindowController mainWindowController, InGameView view, InGameModel model) {
        super(mainWindowController);
        this.view = view;
        this.model = model;

        view.getSwitchToSpadeButton().addActionListener(this::switchToSpade);
        view.getSwitchToHeartButton().addActionListener(this::switchToHeart);
        view.getSwitchToDiamondButton().addActionListener(this::switchToDiamond);
        view.getSwitchToClubButton().addActionListener(this::switchToClub);
        view.getHomeButton().addActionListener((evt) -> mainWindowController.switchScene(HomeView.class));
        view.getNextRoundButton().addActionListener(this::handleNextRound);
        view.getPreviousRoundButton().addActionListener(this::handlePreviousRound);
    }

    private void handleNextRound(ActionEvent e) {
        Game game = model.getGame();
        Round round = new Round(model.getStartingPlayerPosition(), model.getGame().getContract().getContractBid().getSuit());
        Map<Position, Card> cards = view.getCards();

        for (Map.Entry<Position, Card> entry : cards.entrySet()) {
            round.addCard(entry.getKey(), entry.getValue());
        }

        round.findCapturer();
        if (round.getCapturer() == Position.NORTH || round.getCapturer() == Position.SOUTH) {
            game.incrementTricksNS();
        } else {
            game.incrementTricksEW();
        }
        System.out.println("Trick taken by " + round.getCapturer());
        model.setStartingPlayerPosition(round.getCapturer());
        game.addRound(round);
        view.resetPlayerCardViews();
        view.refresh();
    }

    private void handlePreviousRound(ActionEvent e) {

    }

    private void switchTo(Suit suit) {
        if (view.getSelectedSuit() != suit) {
            view.switchSuits(suit);
            view.revalidate();
            view.repaint();
        }
    }

    private void switchToSpade(ActionEvent e) {
        switchTo(Suit.SPADE);
    }

    private void switchToHeart(ActionEvent e) {
        switchTo(Suit.HEART);
    }

    private void switchToDiamond(ActionEvent e) {
        switchTo(Suit.DIAMOND);
    }

    private void switchToClub(ActionEvent e) {
        switchTo(Suit.CLUB);
    }
}
