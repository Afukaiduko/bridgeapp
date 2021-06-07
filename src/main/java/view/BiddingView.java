package view;

import enums.Position;
import enums.Suit;
import model.*;
import utils.CardImageLoader;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BiddingView extends BaseView {
    private final SeatingOrderModel seatingOrderModel;
    private final BiddingModel biddingModel;
    private final ArrayList<NormalBid> possibleNormalBids;
    private final Map<JButton, Integer> normalBidButtons;
    private JPanel innerPanel;
    private JPanel playersPanel;
    private JPanel normalBidsPanel;
    private JPanel undoDoublePassPanel;
    private JLabel north;
    private JLabel east;
    private JLabel south;
    private JLabel west;
    private JLabel northName;
    private JLabel eastName;
    private JLabel southName;
    private JLabel westName;
    private JLabel star;
    private Position startingPosition;
    private Position latestPosition;
    private int incrementRowBy;
    private int shiftStarting;

    private Bid latestBid;
    private NormalBid latestNormalBid;

    private JButton undoButton;
    private JButton doubleButton;
    private JButton passButton;
    private JButton nextButton;

    private boolean finished;
    private boolean fourPass;

    public BiddingView(BiddingModel biddingModel, SeatingOrderModel seatingOrderModel) {
        this.seatingOrderModel = seatingOrderModel;
        this.biddingModel = biddingModel;
        this.finished = false;
        normalBidButtons = new HashMap<>();
        possibleNormalBids = new ArrayList<>();
        incrementRowBy = 0;

        createPossibleBids();
        initializeView();
    }

    private void createPossibleBids() {
        for (int i = 1; i <= 7; i++) {
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
    public void initializeView() {
        north = new JLabel("North");
        east = new JLabel("East");
        south = new JLabel("South");
        west = new JLabel("West");

        undoButton = new JButton("Undo");
        doubleButton = new JButton("Double");
        passButton = new JButton("Pass");
        nextButton = new JButton("Next");

        innerPanel = new JPanel();
        playersPanel = new JPanel();
        normalBidsPanel = new JPanel();
        undoDoublePassPanel = new JPanel();

        northName = new JLabel();
        eastName = new JLabel();
        southName = new JLabel();
        westName = new JLabel();
        star = new JLabel("*Starting*");

        CompUtils.add(north, playersPanel, 0, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(east, playersPanel, 1, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(south, playersPanel, 2, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(west, playersPanel, 3, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(undoButton, undoDoublePassPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(doubleButton, undoDoublePassPanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(passButton, undoDoublePassPanel, 2, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(nextButton, undoDoublePassPanel, 1, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        nextButton.setVisible(false);

        createBidButtons();

        CompUtils.add(playersPanel, innerPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(normalBidsPanel, innerPanel, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(undoDoublePassPanel, innerPanel, 0, 2, 1, 1, 1, 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(innerPanel, this, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 20, 20, 20, 20);
    }

    @Override
    public void onLoadedView() {
        northName.setText((seatingOrderModel.getPlayerPositionsMap().get(Position.NORTH)).getName());
        eastName.setText((seatingOrderModel.getPlayerPositionsMap().get(Position.EAST)).getName());
        southName.setText((seatingOrderModel.getPlayerPositionsMap().get(Position.SOUTH)).getName());
        westName.setText((seatingOrderModel.getPlayerPositionsMap().get(Position.WEST)).getName());

        CompUtils.add(northName, playersPanel, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(eastName, playersPanel, 1, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(southName, playersPanel, 2, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(westName, playersPanel, 3, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        startingPosition = seatingOrderModel.getStartingBidderPosition();
        latestPosition = startingPosition;

        if (startingPosition == Position.NORTH) {
            CompUtils.add(star, playersPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            shiftStarting = 0;
        } else if (startingPosition == Position.EAST) {
            CompUtils.add(star, playersPanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            shiftStarting = 1;
        } else if (startingPosition == Position.SOUTH) {
            CompUtils.add(star, playersPanel, 2, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            shiftStarting = 2;
        } else if (startingPosition == Position.WEST) {
            CompUtils.add(star, playersPanel, 3, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            shiftStarting = 3;
        } else {
            System.out.println("uh oh");
        }
        updateBidButtons();
    }

    private void createBidButtons() {
        JButton currentButton;
        NormalBid current;

        for (int i = 0; i < possibleNormalBids.size(); i++) {
            current = possibleNormalBids.get(i);
            if (current.getSuit() == Suit.NO_TRUMP) {
                currentButton = new JButton("" + current.getCallNumber() + "NT");
            } else {
                Image currentSuitImg = CardImageLoader.getInstance().getSuitImg(current.getSuit());
                ImageIcon currentSuit = new ImageIcon(currentSuitImg);

                currentButton = new JButton("" + current.getCallNumber(), currentSuit);
            }
            CompUtils.add(currentButton, normalBidsPanel, i / 5, i % 5, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
            normalBidButtons.put(currentButton, i);
        }
    }

    private void updateBidButtons() {
        if (!finished) {
            nextButton.setVisible(false);
            nextButton.setText("Next");
            passButton.setEnabled(true);
            for (Map.Entry<JButton, Integer> entry : normalBidButtons.entrySet()) {
                CompUtils.add(entry.getKey(), normalBidsPanel, entry.getValue() / 5, entry.getValue() % 5 + incrementRowBy, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
                if (latestBid == null) {
                    entry.getKey().setEnabled(true);
                    doubleButton.setEnabled(false);
                } else entry.getKey().setEnabled(entry.getValue() > possibleNormalBids.indexOf(latestNormalBid));
            }
            if (latestBid instanceof DoubleBid) {
                if (((DoubleBid) latestBid).getIsRedouble()) {
                    doubleButton.setEnabled(false);
                    doubleButton.setText("Double");
                } else {
                    doubleButton.setText("Redouble");
                    doubleButton.setEnabled(true);
                }
            } else if (latestBid instanceof PassBid) {
                doubleButton.setEnabled(false);
                doubleButton.setText("Double");
            } else if (latestBid instanceof NormalBid) {
                doubleButton.setEnabled(true);
                doubleButton.setText("Double");
            }
        } else {
            for (Map.Entry<JButton, Integer> entry : normalBidButtons.entrySet()) {
                entry.getKey().setEnabled(false);
            }
            doubleButton.setEnabled(false);
            passButton.setEnabled(false);
            nextButton.setVisible(true);
            if (fourPass) {
                nextButton.setText("Finish");
            }
        }
    }

    private void updateBidSequence() {
        ArrayList<Bid> bidSequence = (ArrayList<Bid>) biddingModel.getBiddingSequence();
        for (int i = 0; i < bidSequence.size(); i++) {
            CompUtils.add(bidSequence.get(i).getLabel(), playersPanel, (i + shiftStarting) % 4, (i + shiftStarting) / 4 + 3, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        }
        revalidate();
        repaint();
    }

    public void setIncrementRowBy() {
        if (biddingModel.getBiddingSequence().size() > 0) {
            incrementRowBy = biddingModel.getBiddingSequence().size() / 4 + 1;
        } else {
            incrementRowBy = 0;
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        setIncrementRowBy();
        updateBidButtons();
        updateBidSequence();
    }

    public void removeLatestBid() {
        playersPanel.remove(((Bid) biddingModel.getBiddingSequence().get(biddingModel.getLastIndex())).getLabel());
    }

    public Bid getLatestBid() {
        return latestBid;
    }

    public void setLatestBid(Bid latestBid) {
        this.latestBid = latestBid;
    }

    public NormalBid getLatestNormalBid() {
        return latestNormalBid;
    }

    public void setLatestNormalBid(NormalBid latestNormalBid) {
        this.latestNormalBid = latestNormalBid;
    }

    public Map getNormalBidButtons() {
        return normalBidButtons;
    }

    public ArrayList<NormalBid> getPossibleNormalBids() {
        return possibleNormalBids;
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public JButton getDoubleButton() {
        return doubleButton;
    }

    public JButton getPassButton() {
        return passButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public void setFinished(boolean value) {
        this.finished = value;
    }

    public boolean isFourPass() {
        return fourPass;
    }

    public void setFourPass(boolean value) {
        this.fourPass = value;
    }

    public void incrementLatestPosition() {
        latestPosition = latestPosition.next();
    }

    public void decrementLatestPosition() {
        latestPosition = latestPosition.previous();
    }

    public Position getLatestPosition() {
        return latestPosition;
    }
}
