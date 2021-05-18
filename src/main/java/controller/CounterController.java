package controller;

import model.CounterModel;
import view.CounterView;
import view.FastCounterView;
import view.MainWindow;

import java.awt.event.ActionEvent;


public class CounterController extends BaseController {
    private CounterModel model;
    private CounterView view;

    public CounterController(MainWindow window, CounterModel model, CounterView view) {
        super(window);
        this.model = model;
        this.view = view;

        view.getIncrementButton().addActionListener(this::handleIncrement);
        view.getDecrementButton().addActionListener(this::handleDecrement);
        view.getSwitchToFastCounterButton().addActionListener((evt) -> window.switchPanels(FastCounterView.class));
    }

    private void handleIncrement(ActionEvent evt) {
        // Here we don't actually care about the event.

        // Update model
        model.incrementCounter();

        // Tell the view to update itself
        view.updateCounterValue();
    }
    private void handleDecrement(ActionEvent evt) {
        model.decrementCounter();
        view.updateCounterValue();
    }

}
