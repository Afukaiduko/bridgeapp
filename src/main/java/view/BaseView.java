package view;

import javax.swing.*;

public abstract class BaseView extends JPanel {
    /**
     * Run when the panel is loaded.
     */
    abstract void onLoad();

    /**
     * Initializes the JPanel the first time
     */
    abstract void initializeView();
}
