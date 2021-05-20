package view;

import utils.CompUtils;

import javax.swing.*;
import java.awt.*;

public class HomeView extends BaseView {

    private JLabel titleLabel;
    private JButton newGameButton;
    private JButton replayAGameButton;
    private JButton historyButton;
    private JButton statsButton;

    public HomeView(){
        initializeView();
    }

    protected void initializeView() {
        titleLabel = new JLabel("Duplicate Bridge");
        newGameButton = new JButton("New Game");
        replayAGameButton = new JButton("Replay A Game");

        JPanel innerPanel = new JPanel();

        CompUtils.add(titleLabel, innerPanel, 0, 0,1,2,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    }

}
