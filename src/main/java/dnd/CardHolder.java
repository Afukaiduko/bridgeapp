package dnd;

import view.BaseView;
import view.CardView;

/**
 * Card holders can accept Card Drag and drops.
 */
public abstract class CardHolder extends BaseView {
    protected abstract void acceptCard(CardView card);

    protected abstract void removeCard(CardView card);
}
