package view;

import enums.Rank;
import enums.Suit;
import model.CounterModel;
import utils.CardImageLoader;

import javax.swing.*;
import java.awt.*;

public class CounterView extends BaseView {
    private CounterModel model;

    private LittlePanel littlePanel;

    private JLabel titleLabel;
    private JLabel counterLabel;
    private JButton incrementButton;
    private JButton decrementButton;

    private JButton switchToFastCounterButton;

    private JButton bridgeButton;
    private JButton testBidButton;

    public CounterView(CounterModel model) {
        this.model = model;
        initializeView();
    }

    public void updateCounterValue() {
        counterLabel.setText("Counter is " + model.getCounter());

        // Make sure we update our simple subcomponents.
        littlePanel.updateCounterValue();
    }

    @Override
    public void refresh() {
        super.refresh();
        updateCounterValue();
    }

    @Override
    protected void initializeView() {
        titleLabel = new JLabel("Hello World!");
        counterLabel = new JLabel();
        incrementButton = new JButton("Add 1");
        decrementButton = new JButton("Minus 1");

        switchToFastCounterButton = new JButton("Switch to Fast counting");

        bridgeButton = new JButton("swtich to bridge");

        Image suitImg = CardImageLoader.getInstance().getSuitImg(Suit.HEART);
        ImageIcon heart = new ImageIcon(suitImg);
        testBidButton = new JButton("1",heart);

        // Setup a sub component of this class. Make sure it's added as a subview so it's properly refreshed!
        littlePanel = new LittlePanel(model);
        this.registerSubView(littlePanel);

        updateCounterValue(); // Initial value

        // Setup the view.
        this.setLayout(new FlowLayout());
        this.add(titleLabel);
        this.add(counterLabel);
        this.add(incrementButton);
        this.add(decrementButton);
        this.add(switchToFastCounterButton);
        this.add(littlePanel);
        this.add(bridgeButton);
        this.add(testBidButton);


        // Test code for demonstrating drag and drop cards
        CardHolderExampleView cardPanel1 = new CardHolderExampleView();
        CardHolderExampleView cardPanel2 = new CardHolderExampleView();

        CardView c1 = new CardView(Suit.DIAMOND, Rank.KING);
        CardView c2 = new CardView(Suit.HEART, Rank.TWO);
        CardView c3 = new CardView(Suit.CLUB, Rank.FIVE);
        CardView c4 = new CardView(Suit.SPADE, Rank.THREE);
        CardView c5 = new CardView(Suit.HEART, Rank.SEVEN);
        cardPanel1.acceptCard(c1);
        cardPanel2.acceptCard(c2);
        cardPanel1.acceptCard(c3);
        cardPanel1.acceptCard(c4);
        cardPanel2.acceptCard(c5);
        cardPanel1.setPreferredSize(new Dimension(300, 300));
        cardPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cardPanel2.setPreferredSize(new Dimension(300, 300));
        cardPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(cardPanel1);
        this.add(cardPanel2);
    }

    public JButton getIncrementButton() {
        return incrementButton;
    }

    public JButton getDecrementButton() {
        return decrementButton;
    }

    public JButton getSwitchToFastCounterButton() {
        return switchToFastCounterButton;
    }

    public JButton getBridgeButton(){return bridgeButton;}
}
