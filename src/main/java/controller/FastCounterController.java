package controller;


import model.CounterModel;
import utils.IntegerUtils;
import view.CounterView;
import view.FastCounterView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;

public class FastCounterController extends BaseController {
    private final CounterModel model;
    private final FastCounterView view;

    private int iCountBy;

    public FastCounterController(MainWindowController mainWindowController, CounterModel model, FastCounterView view) {
        super(mainWindowController);
        this.model = model;
        this.view = view;

        this.iCountBy = 0;

        // Setup the listeners
        view.getIncrementButton().addActionListener(this::handleIncrement);

        view.getIncrementByField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTextChange(e);
            }
        });

        view.getIncrementByField().setText("2"); // Setup the initial count to 2

        view.getSwitchToCounterButton().addActionListener((evt) -> mainWindowController.switchScene(CounterView.class));
    }

    private void handleTextChange(DocumentEvent evt) {
        String sIncrementBy = view.getIncrementByField().getText();
        if (IntegerUtils.isInteger(sIncrementBy)) {
            view.showNotIntegerWarning(false);
            iCountBy = Integer.parseInt(sIncrementBy);
        } else {
            view.showNotIntegerWarning(true);
            iCountBy = 0;
        }

    }

    private void handleIncrement(ActionEvent evt) {
        // Update model
        model.incrementCounterBy(iCountBy);

        // Tell View to update itself.
        view.updateCounterValue();
    }
}
