package controller;

import model.GameSelectModel;
import model.GamesDatabase;
import view.GameSelectView;

public class GameSelectController extends BaseController{

    private final GameSelectView view;
    private final GameSelectModel model;
    private final GamesDatabase games;

    public GameSelectController(MainWindowController controller, GameSelectView view, GameSelectModel model, GamesDatabase games){
        super(controller);
        this.view = view;
        this.model = model;
        this.games = games;
    }

}
