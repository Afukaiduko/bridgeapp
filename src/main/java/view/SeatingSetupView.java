package view;

import model.Player;
import model.PlayerDatabase;
import model.SeatingOrderModel;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SeatingSetupView extends BaseView {
    private SeatingOrderModel model;
    private PlayerDatabase playerDatabase;

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

    public SeatingSetupView(SeatingOrderModel model, PlayerDatabase players){
        this.model = model;
        this.playerDatabase = players;
        initializeView();
    }

    public void initializeView(){

        titleLabel = new JLabel("Seating Order");

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

        CompUtils.add(titleLabel, this,0,0,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(north,innerPanel,1,0,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(east,innerPanel,1,1,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(south,innerPanel,1,2,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(west,innerPanel,1,3,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(northButton,innerPanel,3,0,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(eastButton,innerPanel,3,1,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(southButton,innerPanel,3,2,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(westButton,innerPanel,3,3,1,1,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(cbxNorth, innerPanel,2,0,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(cbxEast, innerPanel,2,1,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(cbxSouth, innerPanel,2,2,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(cbxWest, innerPanel,2,3,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        CompUtils.add(innerPanel,this,0,1,1,1,1,1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 20, 20, 20, 20);

        CompUtils.add(registerNewPlayerLabel, this,0,2,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(newPlayerTF, this,1,2,1,1,3,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        CompUtils.add(addPlayerButton, this,2,2,1,1,1,1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        initializePlayerComboBox();
    }

    public void initializePlayerComboBox() {
        ArrayList<Player> players = playerDatabase.getPlayers();
        for (Player player : players) {
            cbxNorth.addItem(player.getName());
            cbxEast.addItem(player.getName());
            cbxSouth.addItem(player.getName());
            cbxWest.addItem(player.getName());
        }
    }

    public void addLatestPlayerToComboBox(){
        cbxNorth.addItem(playerDatabase.getLatestPlayer().getName());
        cbxEast.addItem(playerDatabase.getLatestPlayer().getName());
        cbxSouth.addItem(playerDatabase.getLatestPlayer().getName());
        cbxWest.addItem(playerDatabase.getLatestPlayer().getName());
    }

    public JRadioButton getNorthButton(){
        return northButton;
    }

    public JRadioButton getEastButton(){
        return eastButton;
    }

    public JRadioButton getSouthButton(){
        return southButton;
    }

    public JRadioButton getWestButton(){
        return westButton;
    }

    public JTextField getNewPlayerTF(){
        return newPlayerTF;
    }

    public JButton getAddPlayerButton(){
        return addPlayerButton;
    }
}
