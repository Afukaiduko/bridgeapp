package constants;

import view.CardView;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

public class Constants {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final String SAVE_DIRECTORY = "bridgeapp/data";
    public static final String COUNTER_FILE = "counter.json";

    public static final String PLAYERS_SAVE_DIRECTORY = "bridgeapp/data";
    public static final String PLAYERS_FILE = "players.json";


    public static final String FONT_FAMILY = "Times New Roman";
    public static final Font CARD_FONT = new Font(FONT_FAMILY, Font.BOLD, 24);
    public static final Color CARD_FONT_COLOUR = Color.BLACK;
    public static final Color CARD_BACKGROUND_COLOUR = Color.WHITE;
    public static final int CARD_WIDTH = 60;
    public static final int CARD_HEIGHT = 100;

    public static final DataFlavor CARD_FLAVOR = new DataFlavor(CardView.class, CardView.class.getSimpleName());


}
