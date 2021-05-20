package view;

import model.CounterModel;

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
