package controller;

import model.*;
import view.BiddingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;

public class BiddingController extends BaseController{
    private BiddingModel model;
    private BiddingView view;
    private SeatingOrderModel seatingOrder;

    public BiddingController(MainWindowController mainWindowController, BiddingModel biddingModel, BiddingView biddingView, SeatingOrderModel seatingOrderModel){
        super(mainWindowController);
        this.model = biddingModel;
        this.view = biddingView;
        this.seatingOrder = seatingOrderModel;

        view.getUndoButton().addActionListener(this::handleUndoButton);
        addListenersToNormalBidButtons();
    }

    private void handleUndoButton(ActionEvent e){
        int lastIndex = model.getLastIndex();
        if(lastIndex > -1){
            view.removeLatestBid();
            if(lastIndex > 0) {
                view.setLatestBid(model.get(lastIndex - 1));
            } else {
                view.setLatestBid(null);
            }
            model.remove(lastIndex);
        }
        view.refresh();
    }

    private void addListenersToNormalBidButtons(){
        Map<JButton, Integer> normalBidButtons = view.getNormalBidButtons();
        Iterator<Map.Entry<JButton, Integer>> iterator = normalBidButtons.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<JButton, Integer> entry = iterator.next();
            entry.getKey().addActionListener(this::handleNormalBidButton);
        }
    }

    private void handleNormalBidButton(ActionEvent e){
        int bidIndex = indexOfPressedButton(e);
        if(bidIndex != -1){
            Bid bidClicked = (Bid)view.getPossibleNormalBids().get(bidIndex);
            model.addBid(bidClicked);
            view.setLatestBid(bidClicked);
            view.refresh();
        }
    }

    private int indexOfPressedButton(ActionEvent e) {
        Map<JButton, Integer> normalBidButtons = view.getNormalBidButtons();
        Iterator<Map.Entry<JButton, Integer>> iterator = normalBidButtons.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<JButton, Integer> entry = iterator.next();
            if(entry.getKey() == e.getSource()){
                return entry.getValue();
            }
        }
        return -1;
    }
}
