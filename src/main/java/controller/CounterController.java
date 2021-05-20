package controller;

import model.CounterModel;
import view.CounterView;
import view.FastCounterView;
import view.HomeView;

import java.awt.event.ActionEvent;


public class CounterController extends BaseController {
    private CounterModel model;
    private CounterView view;

    public CounterController(MainWindowController mainWindowController, CounterModel model, CounterView view) {
        super(mainWindowController);
        this.model = model;
        this.view = view;

        view.getIncrementButton().addActionListener(this::handleIncrement);
        view.getDecrementButton().addActionListener(this::handleDecrement);
        view.getSwitchToFastCounterButton().addActionListener((evt) -> mainWindowController.switchScene(FastCounterView.class));

        view.getBridgeButton().addActionListener((evt) -> mainWindowController.switchScene(HomeView.class));
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
