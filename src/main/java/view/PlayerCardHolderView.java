package view;

import dnd.CardDroppedListener;
import dnd.CardHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

public class PlayerCardHolderView extends CardHolder {

    private CardView card;
    private final DeckHolderView deck;

    public PlayerCardHolderView(DeckHolderView deck) {
        this.deck = deck;

        initializeView();

        CardDroppedListener cardDroppedListener = new CardDroppedListener(this);
        DropTarget dt = new DropTarget(this, DnDConstants.ACTION_MOVE, cardDroppedListener, true, null);
    }

    @Override
    protected void initializeView() {
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(70, 115));
    }

    @Override
    public void onLoadedView() {

    }

    private void reloadCards() {
        this.removeAll();
        if (card != null) {
            this.add(card);
        }
        SwingUtilities.invokeLater(() -> {
            // Force component to update
            this.repaint();
            this.revalidate();
        });
    }

    @Override
    protected void acceptCard(CardView card) {
        if (this.card != null) {
            deck.addBackToDeck(this.card);
        }
        this.card = card;
        reloadCards();
    }

    @Override
    protected void removeCard(CardView card) {
        if (this.card == card) {
            this.card = null;
        }
        reloadCards();
    }

    public CardView getCard() {
        return card;
    }

    public void reset() {
        this.card = null;
        reloadCards();
    }
}
