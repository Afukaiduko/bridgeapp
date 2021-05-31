package utils;

import enums.Rank;
import view.CardView;

public class CardUtils {

    /**
     * Sorts by suit first (whatever order they are in the enums)
     * Otherwise, sorts by the card value (smallest to largest)
     */
    public static int compareCards(CardView c1, CardView c2) {
        int stCompare = c1.getSuit().compareTo(c2.getSuit());
        if (stCompare != 0) {
            return stCompare;
        }
        return c1.getRank().compareTo(c2.getRank());
    }
}
