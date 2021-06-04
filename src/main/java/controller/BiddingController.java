package controller;

import enums.ContractDouble;
import model.*;
import view.BiddingView;
import view.InGameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class BiddingController extends BaseController {
    private final BiddingModel model;
    private final BiddingView view;

    public BiddingController(MainWindowController mainWindowController, BiddingModel biddingModel, BiddingView biddingView) {
        super(mainWindowController);
        this.model = biddingModel;
        this.view = biddingView;

        view.getUndoButton().addActionListener(this::handleUndoButton);
        view.getPassButton().addActionListener(this::handlePassButton);
        view.getDoubleButton().addActionListener(this::handleDoubleButton);
        view.getNextButton().addActionListener(this::handleNextButton);
        addListenersToNormalBidButtons();
    }

    private void handleNextButton(ActionEvent e) {
        Contract contract;
        if (view.isFourPass()) {
            contract = new Contract(null, null, view.isFourPass());
            //mainWindowController.switchScene(GameResults.class);
        } else {
            if (model.isDoubled()) {
                if (model.isRedoubled()) {
                    contract = new Contract(view.getLatestNormalBid(), ContractDouble.REDOUBLED, false);
                } else {
                    contract = new Contract(view.getLatestNormalBid(), ContractDouble.DOUBLED, false);
                }
            } else {
                contract = new Contract(view.getLatestNormalBid(), ContractDouble.NOT_DOUBLED, false);
            }
            mainWindowController.switchScene(InGameView.class);
        }
        model.setContract(contract);
    }

    private void handleDoubleButton(ActionEvent e) {
        DoubleBid doubleBid;
        boolean isRedouble = view.getLatestBid() instanceof DoubleBid;
        doubleBid = new DoubleBid(isRedouble);
        model.addBid(doubleBid);
        view.setLatestBid(doubleBid);
        view.refresh();
    }

    private void handlePassButton(ActionEvent e) {
        PassBid pass = new PassBid();
        model.addBid(pass);
        view.setLatestBid(pass);
        checkIfFinished();
        view.refresh();
    }

    private void handleUndoButton(ActionEvent e) {
        int lastIndex = model.getLastIndex();
        if (lastIndex > -1) {
            view.removeLatestBid();
            if (lastIndex > 0) {
                view.setLatestBid(model.get(lastIndex - 1));
            } else {
                view.setLatestBid(null);
            }
            model.remove(lastIndex);
            view.setLatestNormalBid(findLatestNormalBid());
        }
        view.setFinished(false);
        view.refresh();
    }

    private void addListenersToNormalBidButtons() {
        Map<JButton, Integer> normalBidButtons = view.getNormalBidButtons();
        for (Map.Entry<JButton, Integer> entry : normalBidButtons.entrySet()) {
            entry.getKey().addActionListener(this::handleNormalBidButton);
        }
    }

    private void handleNormalBidButton(ActionEvent e) {
        int bidIndex = indexOfPressedButton(e);
        if (bidIndex != -1) {
            NormalBid bidClicked = view.getPossibleNormalBids().get(bidIndex);
            model.addBid(bidClicked);
            view.setLatestBid(bidClicked);
            view.setLatestNormalBid(bidClicked);
            view.refresh();
        }
    }

    private NormalBid findLatestNormalBid() {
        for (int i = model.getBiddingSequence().size() - 1; i >= 0; i--) {
            if (model.getBiddingSequence().get(i) instanceof NormalBid) {
                return (NormalBid) model.getBiddingSequence().get(i);
            }
        }
        return null;
    }

    private int indexOfPressedButton(ActionEvent e) {
        Map<JButton, Integer> normalBidButtons = view.getNormalBidButtons();
        for (Map.Entry<JButton, Integer> entry : normalBidButtons.entrySet()) {
            if (entry.getKey() == e.getSource()) {
                return entry.getValue();
            }
        }
        return -1;
    }

    private void checkIfFinished() {
        List bids = model.getBiddingSequence();
        int last = bids.size() - 1;
        if (last >= 3 && bids.get(last) instanceof PassBid && bids.get(last - 1) instanceof PassBid && bids.get(last - 2) instanceof PassBid) {
            view.setFourPass(false);
            view.setFinished(true);
            if (bids.get(last - 3) instanceof PassBid) {
                view.setFourPass(true);
            }
        }
    }
}
