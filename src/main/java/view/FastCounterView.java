package view;

import model.CounterModel;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;

public class FastCounterView extends BaseView {
    private JLabel titleLabel;
    private JLabel counterLabel;
    private JButton incrementButton;

    private JLabel incrementByLabel;
    private JTextField incrementByField;

    private JLabel warningLabel;
    private JButton switchToCounterButton;

    private CounterModel model;

    public FastCounterView(CounterModel model) {
        this.model = model;
        initializeView();
    }

    public JButton getSwitchToCounterButton() {
        return switchToCounterButton;
    }

    public JTextField getIncrementByField() {
        return incrementByField;
    }

    public JButton getIncrementButton() {
        return incrementButton;
    }

    public void updateCounterValue() {
        counterLabel.setText("Counter is " + model.getCounter());
    }

    public void showNotIntegerWarning(boolean b) {
        warningLabel.setVisible(b);
    }

    @Override
    public void onLoad() {
        updateCounterValue();
    }

    protected void initializeView() {
        titleLabel = new JLabel("Hello World!");

        counterLabel = new JLabel();
        updateCounterValue(); // Get initial counter value from model
        incrementButton = new JButton("Add");

        incrementByLabel = new JLabel("Increment By:");
        incrementByField = new JTextField(); // Set by controller.

        warningLabel = new JLabel("The counter can only be changed by an Integer");
        switchToCounterButton = new JButton("Switch to regular counting");

        JPanel innerPanel = new JPanel(); // Have a panel to have some padding around the edges
        // Setup the view.
        CompUtils.add(titleLabel, innerPanel, 0, 0, 2, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(incrementByLabel, innerPanel, 0, 1, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(incrementByField, innerPanel, 1, 1, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(warningLabel, innerPanel, 0, 2, 2, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(counterLabel, innerPanel, 0, 3, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(incrementButton, innerPanel, 1, 3, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(switchToCounterButton, innerPanel, 0, 4, 2, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(innerPanel, this, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 20, 20, 20, 20);
    }
}
