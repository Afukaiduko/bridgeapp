package view;

import model.Player;
import model.SeatingOrderModel;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;

public class SeatingSetupView extends BaseView {
    private SeatingOrderModel model;

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

    public SeatingSetupView(/*SeatingOrderModel model*/){
        this.model = model;
        initializeView();
    }

    public void initializeView(){

        north = new JLabel("North");
        east = new JLabel("East");
        south = new JLabel("South");
        west = new JLabel("West");

        cbxNorth = new JComboBox();
        //cbxNorth.addItem(new Player()); //not sure if this works
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

        CompUtils.add(innerPanel,this,0,0,1,1,1,1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 20, 20, 20, 20);


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
}
