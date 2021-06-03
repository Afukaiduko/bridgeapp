package view;

import constants.Constants;
import dnd.CardDraggedListener;
import enums.Rank;
import enums.Suit;
import utils.CardImageLoader;
import utils.CardUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.util.Objects;

public class CardView extends JLabel implements Comparable {
    private final Suit suit;
    private final Rank rank;

    public CardView(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        initializeView();

        // Enable Drag of the Card
        DragSource ds = new DragSource();
        CardDraggedListener listener = new CardDraggedListener(); // Both draggesture and dragsource listener.
        ds.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, listener);
        ds.addDragSourceListener(listener);
    }

    public void initializeView() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        this.setPreferredSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));

        this.setFont(Constants.CARD_FONT);
        this.setIconTextGap(10);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setFocusable(false);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        Image suitImg = CardImageLoader.getInstance().getSuitImg(suit);
        this.setIcon(new ImageIcon(suitImg));
        this.setText(rank.getDisplayName());

    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardView cardView = (CardView) o;
        return rank == cardView.rank && suit == cardView.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    @Override
    public int compareTo(Object o) {
        return CardUtils.compareCards(this, (CardView) o);
    }
}
