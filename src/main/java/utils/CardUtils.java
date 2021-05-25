package utils;

import enums.Rank;
import view.CardView;

public class CardUtils {
    public static String convertRankToCardText(Rank rank) {
        switch (rank) {
            case ACE:
                return "A";
            case KING:
                return "K";
            case QUEEN:
                return "Q";
            case JACK:
                return "J";
            case TEN:
                return "10";
            case NINE:
                return "9";
            case EIGHT:
                return "8";
            case SEVEN:
                return "7";
            case SIX:
                return "6";
            case FIVE:
                return "5";
            case FOUR:
                return "4";
            case THREE:
                return "3";
            case TWO:
                return "2";
        }
        return "help";
    }

    /**
     * Sorts by suit first (whatever order they are in the enums)
     * Otherwise, sorts by the card value (smallest to largest)
     */
    public static int compareCards(CardView c1, CardView c2) {
        int stCompare = c1.getSuit().compareTo(c2.getSuit());
        if (stCompare != 0) {
            return stCompare;
        }
        return c1.getRank().compareTo(c2.getRank());// TODO change this to enum to sort properly. Otherwise Ace is first right now
    }
}
