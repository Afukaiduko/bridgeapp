package utils;

import javax.swing.*;
import java.awt.*;

public class CompUtils {

    /**
     * Like add but w/ default padding of 0
     */
    public static void add(JComponent toAdd, JComponent addTo, int x, int y, int width, int height,
                           double weightx, double weighty, int fill, int anchor) {
        add(toAdd, addTo, x, y, width, height, weightx, weighty, fill, anchor, 0, 0, 0, 0);
    }

    /**
     * Adds comp toAdd into addTo using GridBagLayout given the following Constraints
     */
    public static void add(JComponent toAdd, JComponent addTo, int x, int y, int width, int height,
                           double weightx, double weighty, int fill, int anchor, int top, int left, int bottom, int right) {
        if (!(addTo.getLayout() instanceof GridBagLayout)) {
            addTo.setLayout(new GridBagLayout());
        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = width;
        c.gridheight = height;
        c.fill = fill;
        c.anchor = anchor;
        c.gridx = x;
        c.gridy = y;
        c.weightx = weightx;
        c.weighty = weighty;
        Insets in = new Insets(top, left, bottom, right);
        c.insets = in;
        addTo.add(toAdd, c);
    }
}
