package view;

import dnd.CardDroppedListener;
import dnd.CardHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An example of CardHolder - aka a JPanel that can accept cards dropped into it.
 * <p>
 * TODO - Customize this layout so that the cards are rendered as needed
 * (Sorting the cards and making it so that they go in order)
 */
public class CardHolderExampleView extends CardHolder {
    private final SortedSet<CardView> cards;

    public CardHolderExampleView() {
        cards = new TreeSet<>();

        initializeView();

        CardDroppedListener cardDroppedListener = new CardDroppedListener(this);
        DropTarget dt = new DropTarget(this, DnDConstants.ACTION_MOVE, cardDroppedListener, true, null);
    }

    @Override
    protected void initializeView() {
        this.setLayout(new FlowLayout());
        reloadCards();
    }

    @Override
    public void onLoadedView() {
    }

    private void reloadCards() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();
            for (CardView c : cards) {
                this.add(c);
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
}
