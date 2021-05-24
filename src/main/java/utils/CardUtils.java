package utils;

import view.CardView;

public class CardUtils {
    public static String convertNumberToCardText(int number) {
        switch (number) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return String.valueOf(number);
        }
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
        return c1.getNumber() - c2.getNumber();// TODO change this to enum to sort properly. Otherwise Ace is first right now
    }
}
