package view;

import model.Player;
import model.PlayerDatabase;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SeatingSetupView extends BaseView {
    private final PlayerDatabase playerDatabase;

    private JLabel titleLabel;
    private JLabel north;
    private JLabel east;
    private JLabel south;
    private JLabel west;

    private JComboBox cbxNorth;
    private JComboBox cbxEast;
    private JComboBox cbxSouth;
    private JComboBox cbxWest;

    private ButtonGroup group;
    private JRadioButton northButton;
    private JRadioButton eastButton;
    private JRadioButton southButton;
    private JRadioButton westButton;

    private JLabel registerNewPlayerLabel;
    private JTextField newPlayerTF;
    private JButton addPlayerButton;

    private JButton nextButton;

    public SeatingSetupView(PlayerDatabase players) {
        this.playerDatabase = players;
        initializeView();
    }

    @Override
    public void initializeView() {

        titleLabel = new JLabel("Seating Order");

        nextButton = new JButton("Next");

        north = new JLabel("North");
        east = new JLabel("East");
        south = new JLabel("South");
        west = new JLabel("West");

        cbxNorth = new JComboBox();
        cbxEast = new JComboBox();
        cbxSouth = new JComboBox();
        cbxWest = new JComboBox();

        northButton = new JRadioButton("");
        eastButton = new JRadioButton("");
        southButton = new JRadioButton("");
        westButton = new JRadioButton("");

        group = new ButtonGroup();
        group.add(northButton);
        group.add(eastButton);
        group.add(southButton);
        group.add(westButton);

        JPanel innerPanel = new JPanel();

        registerNewPlayerLabel = new JLabel("Register New Player:");
        newPlayerTF = new JTextField();
        addPlayerButton = new JButton("Enter");

        CompUtils.add(titleLabel, this, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(nextButton, this, 1, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(north, innerPanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(east, innerPanel, 1, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(south, innerPanel, 1, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(west, innerPanel, 1, 3, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(northButton, innerPanel, 3, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(eastButton, innerPanel, 3, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(southButton, innerPanel, 3, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(westButton, innerPanel, 3, 3, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(cbxNorth, innerPanel, 2, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(cbxEast, innerPanel, 2, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(cbxSouth, innerPanel, 2, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(cbxWest, innerPanel, 2, 3, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(innerPanel, this, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 20, 20, 20, 20);

        CompUtils.add(registerNewPlayerLabel, this, 0, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(newPlayerTF, this, 1, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(addPlayerButton, this, 2, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        initializePlayerComboBox();
    }

    @Override
    public void onLoadedView() {
        if (northButton.isSelected()) {
            eastButton.setSelected(true);
        } else if (eastButton.isSelected()) {
            southButton.setSelected(true);
        } else if (southButton.isSelected()) {
            westButton.setSelected(true);
        } else {
            northButton.setSelected(true);
        }
    }

    private void initializePlayerComboBox() {
        List<Player> players = playerDatabase.getPlayers();
        for (Player player : players) {
            cbxNorth.addItem(player);
            cbxEast.addItem(player);
            cbxSouth.addItem(player);
            cbxWest.addItem(player);
        }
        if (players.size() > 1) {
            cbxEast.setSelectedItem(players.get(1));
        }
        if (players.size() > 2) {
            cbxSouth.setSelectedItem(players.get(2));
        }
        if (players.size() > 3) {
            cbxWest.setSelectedItem(players.get(3));
        }
    }

    public void addLatestPlayerToComboBox() {
        cbxNorth.addItem(playerDatabase.getLatestPlayer());
        cbxEast.addItem(playerDatabase.getLatestPlayer());
        cbxSouth.addItem(playerDatabase.getLatestPlayer());
        cbxWest.addItem(playerDatabase.getLatestPlayer());
    }

    public JRadioButton getNorthButton() {
        return northButton;
    }

    public JRadioButton getEastButton() {
        return eastButton;
    }

    public JRadioButton getSouthButton() {
        return southButton;
    }

    public JRadioButton getWestButton() {
        return westButton;
    }

    public JTextField getNewPlayerTF() {
        return newPlayerTF;
    }

    public JButton getAddPlayerButton() {
        return addPlayerButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JComboBox getCbxNorth() {
        return cbxNorth;
    }

    public JComboBox getCbxEast() {
        return cbxEast;
    }

    public JComboBox getCbxSouth() {
        return cbxSouth;
    }

    public JComboBox getCbxWest() {
        return cbxWest;
    }
}
