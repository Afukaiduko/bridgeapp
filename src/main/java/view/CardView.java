package view;

import constants.Constants;
import dnd.CardDraggedListener;
import enums.Suit;
import utils.CardImageLoader;
import utils.CardUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.util.Objects;

public class CardView extends JLabel implements Comparable {
    private Suit suit;
    private int number; // TODO This probably can be an enum then you don't need the CardUtils to convert the number.

    public CardView(Suit suit, int number) {
        this.suit = suit;
        this.number = number;
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
        this.setText(CardUtils.convertNumberToCardText(number));

    }

    public Suit getSuit() {
        return suit;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardView cardView = (CardView) o;
        return number == cardView.number && suit == cardView.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, number);
    }

    @Override
    public int compareTo(Object o) {
        return CardUtils.compareCards(this, (CardView) o);
    }
}
