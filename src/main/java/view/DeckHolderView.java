package view;

import dnd.CardDroppedListener;
import dnd.CardHolder;
import enums.Rank;
import enums.Suit;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.util.SortedSet;
import java.util.TreeSet;

public class DeckHolderView extends CardHolder {
    private final SortedSet<CardView> cards;
    private Suit suitSelected;

    public DeckHolderView() {
        this.cards = new TreeSet<>();
        this.suitSelected = Suit.SPADE;

        initializeView();

        CardDroppedListener cardDroppedListener = new CardDroppedListener(this);
        DropTarget dt = new DropTarget(this, DnDConstants.ACTION_MOVE, cardDroppedListener, true, null);
    }

    @Override
    protected void initializeView() {
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (Rank rank : Rank.values()) {
            cards.add(new CardView(Suit.SPADE, rank));
            cards.add(new CardView(Suit.HEART, rank));
            cards.add(new CardView(Suit.DIAMOND, rank));
            cards.add(new CardView(Suit.CLUB, rank));
        }

        reloadCards();
    }

    @Override
    public void onLoadedView() {

    }

    private void reloadCards() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();
            for (CardView c : cards) {
                if( c.getSuit() == suitSelected){
                    this.add(c);
                }
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

    public Suit getSelectedSuit(){
        return suitSelected;
    }

    public void switchSuits(Suit suit){
        this.suitSelected = suit;
        reloadCards();
    }
}
