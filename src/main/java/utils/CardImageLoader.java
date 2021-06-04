package utils;

import enums.Suit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class uses the singleton pattern in order to not reload the cards more than once (this class is only ever created
 * once). Honestly the singleton pattern is a bit outdated, but it's the easiest pattern to use here to get the idea across
 * so I'm still using it here as a demo.
 */
public class CardImageLoader {

    private static final String IMAGES_ROOT = "/images";
    public static final String SPADE_FILE = IMAGES_ROOT + "/SPADES.jpg";
    public static final String HEART_FILE = IMAGES_ROOT + "/HEARTS.jpg";
    public static final String DIAMOND_FILE = IMAGES_ROOT + "/DIAMONDS.jpg";
    public static final String CLUB_FILE = IMAGES_ROOT + "/CLUBS.jpg";
    private static CardImageLoader instance;
    private final Map<Suit, Image> suitImgs;

    private CardImageLoader() {
        suitImgs = new HashMap<>();
        try {
            Image clubImg = ImageIO.read(getClass().getResourceAsStream(CLUB_FILE))
                    .getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
            Image diamondImg = ImageIO.read(getClass().getResourceAsStream(DIAMOND_FILE))
                    .getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
            Image heartImg = ImageIO.read(getClass().getResourceAsStream(HEART_FILE))
                    .getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
            Image spadeImg = ImageIO.read(getClass().getResourceAsStream(SPADE_FILE))
                    .getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);

            suitImgs.put(Suit.CLUB, clubImg);
            suitImgs.put(Suit.DIAMOND, diamondImg);
            suitImgs.put(Suit.HEART, heartImg);
            suitImgs.put(Suit.SPADE, spadeImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CardImageLoader getInstance() {
        if (instance == null) {
            instance = new CardImageLoader();
        }
        return instance;
    }

    public Image getSuitImg(Suit suit) {
        return suitImgs.get(suit);
    }
}
