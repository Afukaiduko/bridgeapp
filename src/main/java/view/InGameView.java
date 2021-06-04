package view;

import enums.Suit;
import model.BiddingModel;
import model.SeatingOrderModel;
import utils.CardImageLoader;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;

public class InGameView extends BaseView {
    private final DeckHolderView deckView;
    //private final GameplayView gameView;

    private final BiddingModel biddingModel;
    private final SeatingOrderModel seatingOrderModel;

    private JButton switchToSpadeButton;
    private JButton switchToHeartButton;
    private JButton switchToDiamondButton;
    private JButton switchToClubButton;


    public InGameView(BiddingModel biddingModel, SeatingOrderModel seatingOrderModel) {
        this.biddingModel = biddingModel;
        this.seatingOrderModel = seatingOrderModel;
        this.deckView = new DeckHolderView();
        //this.gameView = new GameplayView();
        initializeView();
    }

    @Override
    public void initializeView() {
        //Test
        CardHolderExampleView cardPanel1 = new CardHolderExampleView();
        cardPanel1.setPreferredSize(new Dimension(300, 300));
        cardPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        CompUtils.add(cardPanel1, this, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        JPanel deckPanel = new JPanel();

        switchToSpadeButton = new JButton();
        switchToHeartButton = new JButton();
        switchToDiamondButton = new JButton();
        switchToClubButton = new JButton();

        setSuitButtonIcons(switchToSpadeButton, Suit.SPADE);
        setSuitButtonIcons(switchToHeartButton, Suit.HEART);
        setSuitButtonIcons(switchToDiamondButton, Suit.DIAMOND);
        setSuitButtonIcons(switchToClubButton, Suit.CLUB);

        CompUtils.add(deckView, deckPanel, 1, 0, 1, 4, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToSpadeButton, deckPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToHeartButton, deckPanel, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToDiamondButton, deckPanel, 0, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToClubButton, deckPanel, 0, 3, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(deckPanel, this, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

    }

    private void setSuitButtonIcons(JButton button, Suit suit) {
        Image suitImg = CardImageLoader.getInstance().getSuitImg(suit);
        ImageIcon suitIcon = new ImageIcon(suitImg);
        button.setIcon(suitIcon);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void onLoadedView() {

    }

    public JButton getSwitchToSpadeButton(){
        return switchToSpadeButton;
    }

    public JButton getSwitchToHeartButton(){
        return switchToHeartButton;
    }

    public JButton getSwitchToDiamondButton(){
        return switchToDiamondButton;
    }

    public JButton getSwitchToClubButton(){
        return switchToClubButton;
    }

    public Suit getSelectedSuit(){
        return deckView.getSelectedSuit();
    }

    public void switchSuits(Suit suit){
        deckView.switchSuits(suit);
    }
}
