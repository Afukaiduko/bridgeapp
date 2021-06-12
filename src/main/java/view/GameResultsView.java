package view;

import model.InGameModel;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;

public class GameResultsView extends BaseView {
    private final InGameModel inGameModel;

    private JLabel titleLabel;
    private JLabel contractResultLabel;
    private JLabel winnerLabel;
    private JLabel saveThisGameLabel;

    private JButton saveGameYesButton;
    private JButton saveGameNoButton;
    private JButton newGameButton;
    private JButton reviewGameButton;

    private boolean answeredSaveGame;

    public GameResultsView(InGameModel inGameModel) {
        this.inGameModel = inGameModel;
        this.answeredSaveGame = false;
        initializeView();
    }

    @Override
    public void initializeView() {
        titleLabel = new JLabel("Game Results");
        saveThisGameLabel = new JLabel("Save this game?");
        contractResultLabel = new JLabel();
        winnerLabel = new JLabel();
        saveGameYesButton = new JButton("Yes");
        saveGameNoButton = new JButton("No");
        newGameButton = new JButton("New Game");
        reviewGameButton = new JButton("Review Game");

        JPanel innerPanel = new JPanel();

        CompUtils.add(titleLabel, innerPanel, 1, 0, 3, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(contractResultLabel, innerPanel, 1, 1, 3, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(winnerLabel, innerPanel, 1, 2, 3, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(saveThisGameLabel, innerPanel, 1, 3, 3, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(saveGameYesButton, innerPanel, 0, 4, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(saveGameNoButton, innerPanel, 2, 4, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(innerPanel, this, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 50, 50, 50, 50);
    }

    @Override
    public void onLoadedView() {
        contractResultLabel.setText("" + inGameModel.getGame().getContract() + inGameModel.getGame().getOverUnderContract() + " " + inGameModel.getGame().getContractors());
        winnerLabel.setText("" + inGameModel.getGame().getWinner() + " Wins!");
    }

    @Override
    public void refresh() {
        super.refresh();
        updateButtons();
        SwingUtilities.invokeLater(() -> {
            repaint();
            revalidate();
        });
    }

    private void updateButtons() {
        if (answeredSaveGame) {

        }
    }

    public JButton getSaveGameYesButton() {
        return saveGameYesButton;
    }

    public JButton getSaveGameNoButton() {
        return saveGameNoButton;
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getReviewGameButton() {
        return reviewGameButton;
    }

    public void setAnsweredSaveGame(boolean answeredSaveGame) {
        this.answeredSaveGame = answeredSaveGame;
    }
}
