package controller;

import enums.Suit;
import view.InGameView;

import java.awt.event.ActionEvent;

public class InGameController extends BaseController {

    private final InGameView view;

    public InGameController(MainWindowController mainWindowController, InGameView view){
        super(mainWindowController);
        this.view = view;

        view.getSwitchToSpadeButton().addActionListener(this::switchToSpade);
        view.getSwitchToHeartButton().addActionListener(this::switchToHeart);
        view.getSwitchToDiamondButton().addActionListener(this::switchToDiamond);
        view.getSwitchToClubButton().addActionListener(this::switchToClub);
    }

    private void switchTo(Suit suit){
        if(view.getSelectedSuit() != suit){
            view.switchSuits(suit);
            view.revalidate();
            view.repaint();
        }
    }

    private void switchToSpade(ActionEvent e){
        switchTo(Suit.SPADE);
    }

    private void switchToHeart(ActionEvent e){
        switchTo(Suit.HEART);
    }

    private void switchToDiamond(ActionEvent e){
        switchTo(Suit.DIAMOND);
    }

    private void switchToClub(ActionEvent e){
        switchTo(Suit.CLUB);
    }
}
