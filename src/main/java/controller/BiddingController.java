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

        addListenersToNormalBidButtons();
    }

    private void addListenersToNormalBidButtons(){
        Map<JButton, Integer> normalBidButtons = view.getNormalBidButtons();
        Iterator<Map.Entry<JButton, Integer>> iterator = normalBidButtons.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<JButton, Integer> entry = iterator.next();
            entry.getKey().addActionListener(this::normalBidButton);
        }
    }

    private void normalBidButton(ActionEvent e){
        int bidIndex = indexOfPressedButton(e);
        if(bidIndex != -1){
            view.setLatestBid((Bid)view.getPossibleNormalBids().get(bidIndex));
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
