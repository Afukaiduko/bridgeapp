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

        CompUtils.add(titleLabel, innerPanel, 0, 0,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(newGameButton, innerPanel, 0,1,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(replayAGameButton, innerPanel, 0,2,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(innerPanel, this, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 200, 200, 200, 200);
    }

    public JButton getNewGameButton(){
        return newGameButton;
    }

    public JButton getReplayAGameButton(){
        return replayAGameButton;
    }

}
