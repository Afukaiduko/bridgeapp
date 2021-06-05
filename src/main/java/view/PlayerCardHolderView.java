package view;

import dnd.CardDroppedListener;
import dnd.CardHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.util.SortedSet;
import java.util.TreeSet;

public class PlayerCardHolderView extends CardHolder {

    private final SortedSet<CardView> cards;
    private boolean displayCard;

    public PlayerCardHolderView() {
        this.cards = new TreeSet<>();

        initializeView();

        CardDroppedListener cardDroppedListener = new CardDroppedListener(this);
        DropTarget dt = new DropTarget(this, DnDConstants.ACTION_MOVE, cardDroppedListener, true, null);
    }

    @Override
    protected void initializeView() {
        this.displayCard = true;
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(70, 115));
    }

    @Override
    public void onLoadedView() {

    }

    private void reloadCards() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();
            if (displayCard && cards.last() != null) {
                this.add(cards.last());
            }
            // Force component to update
            this.repaint();
            this.revalidate();
        });
    }

    @Override
    protected void acceptCard(CardView card) {
        if (cards.contains(card)) {
            return;
        }
        cards.add(card);
        reloadCards();
    }

    @Override
    protected void removeCard(CardView card) {
        if (!cards.contains(card)) {
            return;
        }
        cards.remove(card);
        reloadCards();
    }

    public void setDisplayCard(boolean displayCard) {
        this.displayCard = displayCard;
    }

    public boolean getDisplayCard() {
        return this.displayCard;
    }
}
