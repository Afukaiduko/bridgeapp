package view;

import model.GameSelectModel;
import model.GamesDatabase;
import model.SaveGame;
import utils.CompUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameSelectView extends BaseView{

    private final GamesDatabase games;
    private final GameSelectModel model;

    private JLabel titleLabel;
    private JLabel numGamesLabel;

    private Map<JButton, SaveGame> savedGamesMap;

    public GameSelectView(GameSelectModel model, GamesDatabase games){
        this.model = model;
        this.games = games;

        this.savedGamesMap = new HashMap<JButton, SaveGame>();

        initializeView();
    }

    @Override
    public void refresh(){

    }

    @Override
    public void initializeView(){

        JPanel innerPanel = new JPanel();

        titleLabel = new JLabel("Select A Game:");
        numGamesLabel = new JLabel("Number of Games: " + games.getNumGames());
        CompUtils.add(titleLabel,innerPanel,0,0,10,1,1,1, GridBagConstraints.VERTICAL,GridBagConstraints.CENTER);
        CompUtils.add(innerPanel,this,0,0,1,1,1,1,GridBagConstraints.BOTH,GridBagConstraints.CENTER,100,100,100,100);

        createButtons();

        int i = 0;
        for (Map.Entry<JButton, SaveGame> entry : savedGamesMap.entrySet()) {
            CompUtils.add(entry.getKey(), innerPanel, i % 10, i / 10 + 1, 1, 1, 1, 1, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER);
        }
    }

    public void createButtons(){
        int i = 1;
        for(SaveGame sg: games.getGames()){
            savedGamesMap.put(new JButton(""+i),sg);
            i++;
        }
    }


    @Override
    public void onLoadedView(){

    }
}
