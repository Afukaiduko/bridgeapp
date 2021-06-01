package view;

import enums.Suit;
import model.*;

import javax.swing.*;
import enums.Position;
import utils.CardImageLoader;
import utils.CompUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BiddingView extends BaseView{
    private SeatingOrderModel seatingOrderModel;
    private BiddingModel biddingModel;

    private JPanel innerPanel;
    private JPanel playersPanel;
    private JPanel normalBidsPanel;

    private JLabel north;
    private JLabel east;
    private JLabel south;
    private JLabel west;

    private JLabel northName;
    private JLabel eastName;
    private JLabel southName;
    private JLabel westName;

    private ArrayList<NormalBid> possibleNormalBids;
    private Map<JButton, Integer> normalBidButtons;
    private Map<Integer, JLabel> bidLabels;
    private int incrementRowBy;
    private int shiftStarting;

    private Bid latestBid;

    public BiddingView(BiddingModel biddingModel, SeatingOrderModel seatingOrderModel){
        this.seatingOrderModel = seatingOrderModel;
        this.biddingModel = biddingModel;
        normalBidButtons = new HashMap<>();
        possibleNormalBids = new ArrayList<>();
        bidLabels = new HashMap();
        incrementRowBy = 0;

        createPossibleBids();
        initializeView();
    }

    private void createPossibleBids(){
        for(int i = 1; i <= 7; i++){
            NormalBid c = new NormalBid(i, Suit.CLUB);
            NormalBid d = new NormalBid(i, Suit.DIAMOND);
            NormalBid h = new NormalBid(i, Suit.HEART);
            NormalBid s = new NormalBid(i, Suit.SPADE);
            NormalBid nt = new NormalBid(i, Suit.NO_TRUMP);

            possibleNormalBids.add(c);
            possibleNormalBids.add(d);
            possibleNormalBids.add(h);
            possibleNormalBids.add(s);
            possibleNormalBids.add(nt);
        }
    }

    @Override
    public void initializeView(){
        north = new JLabel("North");
        east = new JLabel("East");
        south = new JLabel("South");
        west = new JLabel("West");

        innerPanel = new JPanel();
        playersPanel = new JPanel();
        normalBidsPanel = new JPanel();

        CompUtils.add(north, playersPanel,0,2,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(east, playersPanel,1,2,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(south, playersPanel,2,2,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(west, playersPanel,3,2,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        createBidButtons();
        createBidLabels();

        CompUtils.add(playersPanel, innerPanel,0,0,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(normalBidsPanel, innerPanel,0,1,1,1,1,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER);
        CompUtils.add(innerPanel,this,0,1,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.CENTER, 20, 20, 20, 20);

    }
    @Override
    public void onLoadedView(){
        northName = new JLabel(((Player)seatingOrderModel.getPlayerPositionsMap().get(Position.NORTH)).getName());
        eastName = new JLabel(((Player)seatingOrderModel.getPlayerPositionsMap().get(Position.EAST)).getName());
        southName = new JLabel(((Player)seatingOrderModel.getPlayerPositionsMap().get(Position.SOUTH)).getName());
        westName = new JLabel(((Player)seatingOrderModel.getPlayerPositionsMap().get(Position.WEST)).getName());

        CompUtils.add(northName, playersPanel,0,1,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(eastName, playersPanel,1,1,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(southName, playersPanel,2,1,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(westName, playersPanel,3,1,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        Position startingPosition = seatingOrderModel.getStartingBidderPosition();
        JLabel star = new JLabel("*Starting*");

        if(startingPosition == Position.NORTH){
            CompUtils.add(star, playersPanel,0,0,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            shiftStarting = 0;
        } else if(startingPosition == Position.EAST){
            CompUtils.add(star, playersPanel,1,0,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            shiftStarting = 1;
        } else if (startingPosition == Position.SOUTH){
            CompUtils.add(star, playersPanel,2,0,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            shiftStarting = 2;
        } else if(startingPosition == Position.WEST){
            CompUtils.add(star, playersPanel,3,0,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            shiftStarting = 3;
        } else {
            System.out.println("uh oh");
        }
        updateBidButtons();
    }

    private void createBidButtons() {
        JButton currentButton;
        NormalBid current;

        for(int i = 0; i < possibleNormalBids.size(); i++){
            current = possibleNormalBids.get(i);
            if(current.getSuit() == Suit.NO_TRUMP){
                currentButton = new JButton("" + current.getCallNumber() + "NT");
            } else {
                Image currentSuitImg = CardImageLoader.getInstance().getSuitImg(current.getSuit());
                ImageIcon currentSuit = new ImageIcon(currentSuitImg);

                currentButton = new JButton("" + current.getCallNumber(), currentSuit);
            }
            CompUtils.add(currentButton, normalBidsPanel, i/5, i%5, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            normalBidButtons.put(currentButton,i);
        }
    }

    private void createBidLabels(){
        JLabel currentLabel;
        NormalBid current;
        int i;
        for(i = 0; i < possibleNormalBids.size(); i++){
            current = possibleNormalBids.get(i);
            if(current.getSuit() == Suit.NO_TRUMP){
                currentLabel = new JLabel("" + current.getCallNumber() + "NT");
            } else {
                Image currentSuitImg = CardImageLoader.getInstance().getSuitImg(current.getSuit());
                ImageIcon currentSuit = new ImageIcon(currentSuitImg);

                currentLabel = new JLabel("" + current.getCallNumber());
                currentLabel.setIcon(currentSuit);
            }
            bidLabels.put(i, currentLabel);
        }

        bidLabels.put(i++,new JLabel("pass"));
        bidLabels.put(i++, new JLabel("X"));
    }

    private void updateBidButtons(){
        Iterator<Map.Entry<JButton, Integer>> iterator = normalBidButtons.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<JButton, Integer> entry = iterator.next();
            CompUtils.add(entry.getKey(), normalBidsPanel, entry.getValue()/5, entry.getValue()%5 + incrementRowBy, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            if(entry.getValue() <= possibleNormalBids.indexOf((NormalBid)latestBid)){
                entry.getKey().setEnabled(false);
            }
        }
    }

    private void updateBidSequence(){

        if(latestBid !=null){
            if(latestBid instanceof NormalBid){
                CompUtils.add(bidLabels.get(possibleNormalBids.indexOf((NormalBid)latestBid)), playersPanel, (biddingModel.getBiddingSequence().size()-1+shiftStarting)%4, (biddingModel.getBiddingSequence().size()-1+shiftStarting)/4+3 ,1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            }
            revalidate();
            repaint();
        }
    }

    public void setIncrementRowBy(){
        if(biddingModel.getBiddingSequence().size() > 0){
            incrementRowBy = biddingModel.getBiddingSequence().size()/4 + 1;
        } else{
            incrementRowBy = 0;
        }
    }

    @Override
    public void refresh(){
        super.refresh();
        updateBidButtons();
        updateBidSequence();
    }

    public void setLatestBid(Bid latestBid){
        this.latestBid = latestBid;
    }

    public Map getNormalBidButtons(){
        return normalBidButtons;
    }

    public ArrayList getPossibleNormalBids(){
        return possibleNormalBids;
    }
}
