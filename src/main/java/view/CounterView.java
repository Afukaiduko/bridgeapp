package view;

import model.CounterModel;

import javax.swing.*;
import java.awt.*;

public class CounterView extends BaseView {
    private CounterModel model;

    private JLabel titleLabel;
    private JLabel counterLabel;
    private JButton incrementButton;
    private JButton decrementButton;

    private JButton switchToFastCounterButton;

    public CounterView(CounterModel model) {
        this.model = model;
        initializeView();
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

    public void updateCounterValue() {
        counterLabel.setText("Counter is " + model.getCounter());
    }


    @Override
    public void onLoad() {
        updateCounterValue();
    }

    @Override
    protected void initializeView() {
        titleLabel = new JLabel("Hello World!");
        counterLabel = new JLabel();
        updateCounterValue(); // Initial value
        incrementButton = new JButton("Add 1");
        decrementButton = new JButton("Minus 1");

        switchToFastCounterButton = new JButton("Switch to Fast counting");

        // Setup the view.
        this.setLayout(new FlowLayout());
        this.add(titleLabel);
        this.add(counterLabel);
        this.add(incrementButton);
        this.add(decrementButton);
        this.add(switchToFastCounterButton);
    }
}
