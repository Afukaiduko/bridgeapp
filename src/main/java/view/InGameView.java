package view;

import enums.Position;
import enums.Suit;
import model.*;
import utils.CardImageLoader;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InGameView extends BaseView {
    private final DeckHolderView deckView;
    private final Map<Position, PlayerCardHolderView> playerCardViews;

    private final InGameModel inGameModel;
    private final BiddingModel biddingModel;
    private final SeatingOrderModel seatingOrderModel;

    private JButton switchToSpadeButton;
    private JButton switchToHeartButton;
    private JButton switchToDiamondButton;
    private JButton switchToClubButton;

    private JButton homeButton;
    private JButton previousRoundButton;
    private JButton nextRoundButton;
    private JButton finishGame;

    private JPanel gameplayPanel;
    private JPanel deckPanel;
    private JLabel contractLabel;
    private JLabel tricksNSLabel;
    private JLabel tricksEWLabel;
    private ImageIcon contractIcon;//??might not do


    public InGameView(InGameModel inGameModel, SeatingOrderModel seatingOrderModel, BiddingModel biddingModel) {
        this.inGameModel = inGameModel;
        this.seatingOrderModel = seatingOrderModel;
        this.biddingModel = biddingModel;
        this.deckView = new DeckHolderView();
        this.playerCardViews = new HashMap<>();

        initializeView();
    }

    @Override
    public void initializeView() {
        //Test
        /*
        CardHolderExampleView cardPanel1 = new CardHolderExampleView();
        cardPanel1.setPreferredSize(new Dimension(300, 300));
        cardPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        CompUtils.add(cardPanel1, this, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
*/
        deckPanel = new JPanel();
        gameplayPanel = new JPanel();

        JPanel menusPanel = new JPanel();

        switchToSpadeButton = new JButton();
        switchToHeartButton = new JButton();
        switchToDiamondButton = new JButton();
        switchToClubButton = new JButton();

        contractLabel = new JLabel();
        tricksNSLabel = new JLabel();
        tricksEWLabel = new JLabel();

        homeButton = new JButton("Home");
        previousRoundButton = new JButton("Previous Round");
        nextRoundButton = new JButton("Next Round");
        finishGame = new JButton("Finish");

        setSuitButtonIcon(switchToSpadeButton, Suit.SPADE);
        setSuitButtonIcon(switchToHeartButton, Suit.HEART);
        setSuitButtonIcon(switchToDiamondButton, Suit.DIAMOND);
        setSuitButtonIcon(switchToClubButton, Suit.CLUB);

        CompUtils.add(deckView, deckPanel, 1, 0, 1, 4, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToSpadeButton, deckPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToHeartButton, deckPanel, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToDiamondButton, deckPanel, 0, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToClubButton, deckPanel, 0, 3, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        createPlayerCardHolderViews();

        CompUtils.add(homeButton, menusPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(previousRoundButton, menusPanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(contractLabel, gameplayPanel, 6, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(tricksNSLabel, gameplayPanel, 6, 5, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(tricksEWLabel, gameplayPanel, 6, 6, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(nextRoundButton, gameplayPanel, 3, 3, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(finishGame, gameplayPanel, 3, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        finishGame.setVisible(false);

        CompUtils.add(menusPanel, gameplayPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(deckPanel, this, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

    }

    private void createPlayerCardHolderViews() {
        for (Position p : Position.values()) {
            playerCardViews.put(p, new PlayerCardHolderView(deckView));
        }

        CompUtils.add(playerCardViews.get(Position.NORTH), gameplayPanel, 3, 1, 1, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER);
        CompUtils.add(playerCardViews.get(Position.EAST), gameplayPanel, 6, 3, 1, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER);
        CompUtils.add(playerCardViews.get(Position.SOUTH), gameplayPanel, 3, 5, 1, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER);
        CompUtils.add(playerCardViews.get(Position.WEST), gameplayPanel, 0, 3, 1, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER);

        CompUtils.add(gameplayPanel, this, 0, 0, 1, 1, 1, 4, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    }

    private void setSuitButtonIcon(JButton button, Suit suit) {
        Image suitImg = CardImageLoader.getInstance().getSuitImg(suit);
        ImageIcon suitIcon = new ImageIcon(suitImg);
        button.setIcon(suitIcon);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void onLoadedView() {
        contractLabel.setText("Contract: " + biddingModel.getContract() + " " + biddingModel.getDirection());
        inGameModel.setGame(new Game(biddingModel.getContract(), biddingModel.getDirection(), seatingOrderModel.getPlayerPositionsMap()));
        inGameModel.setStartingPlayerPosition(biddingModel.getStartingPlayerPosition());
    }

    @Override
    public void refresh() {
        super.refresh();
        updateTricksTaken();
        repaint();
        revalidate();
    }

    public void updateTricksTaken() {
        tricksNSLabel.setText("N/S: " + inGameModel.getGame().getTricksNS() + " Tricks");
        tricksEWLabel.setText("E/W: " + inGameModel.getGame().getTricksEW() + " Tricks");
    }

    public void resetPlayerCardViews() {
        for (Map.Entry<Position, PlayerCardHolderView> entry : playerCardViews.entrySet()) {
            entry.getValue().reset();
        }
    }

    public JButton getSwitchToSpadeButton() {
        return switchToSpadeButton;
    }

    public JButton getSwitchToHeartButton() {
        return switchToHeartButton;
    }

    public JButton getSwitchToDiamondButton() {
        return switchToDiamondButton;
    }

    public JButton getSwitchToClubButton() {
        return switchToClubButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getPreviousRoundButton() {
        return previousRoundButton;
    }

    public JButton getNextRoundButton() {
        return nextRoundButton;
    }

    public Suit getSelectedSuit() {
        return deckView.getSelectedSuit();
    }

    public void switchSuits(Suit suit) {
        deckView.switchSuits(suit);
    }

    public Map<Position, Card> getCards() {
        Map<Position, Card> cards = new HashMap<>();
        for (Map.Entry<Position, PlayerCardHolderView> entry : playerCardViews.entrySet()) {
            cards.put(entry.getKey(), new Card(entry.getValue().getCard().getRank(), entry.getValue().getCard().getSuit()));
        }
        return cards;
    }
}
