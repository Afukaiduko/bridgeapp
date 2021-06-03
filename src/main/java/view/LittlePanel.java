package view;

import model.CounterModel;

import javax.swing.*;

/**
 * An example of a "dumb" subcomponent. This component doesn't have it's own controller, so it needs its parent to
 * tell it when to reload from the model.
 */
public class LittlePanel extends BaseView {
    private final CounterModel model;
    private JLabel label;

    public LittlePanel(CounterModel model) {
        this.model = model;
        initializeView();
    }

    @Override
    public void refresh() {
        updateCounterValue();
    }

    @Override
    protected void initializeView() {
        label = new JLabel();
        this.add(label);
    }

    @Override
    public void onLoadedView() {

    }

    public void updateCounterValue() {
        label.setText("I am a little view that updates too! Counter is at - " + model.getCounter());
    }
}
