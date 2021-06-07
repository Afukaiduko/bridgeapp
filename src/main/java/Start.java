import com.formdev.flatlaf.FlatLightLaf;
import controller.*;
import io.IOManager;
import model.*;
import view.*;

import javax.swing.*;

public class Start {
    public static void main(String[] args) {

        // Setup a nicer design lol.
        FlatLightLaf.setup();

        IOManager ioManager = new IOManager();

        SwingUtilities.invokeLater(() -> {
            // The overall JFrame
            MainWindow window = new MainWindow();
            MainWindowController controller = new MainWindowController(window);

            // Setup models, views, and controllers.
            CounterModel counterModel = ioManager.readCounterModel();
            PlayerDatabase playerDatabase = ioManager.readPlayerDatabaseModel();
            GamesDatabase gamesDatabase = ioManager.readGame();

            CounterView counterView = new CounterView(counterModel);
            CounterController counterController = new CounterController(controller, counterModel, counterView);

            FastCounterView fastCounterView = new FastCounterView(counterModel);
            FastCounterController fastCounterController = new FastCounterController(controller, counterModel, fastCounterView);

            HomeView homeView = new HomeView();
            HomeController homeController = new HomeController(controller, homeView);

            SeatingOrderModel seatingOrderModel = new SeatingOrderModel(playerDatabase);
            SeatingSetupView seatingSetupView = new SeatingSetupView(playerDatabase);
            SeatingSetupController seatingSetupController = new SeatingSetupController(controller, playerDatabase, seatingSetupView, seatingOrderModel);

            BiddingModel biddingModel = new BiddingModel();
            BiddingView biddingView = new BiddingView(biddingModel, seatingOrderModel);
            BiddingController biddingController = new BiddingController(controller, biddingModel, biddingView);

            InGameModel inGameModel = new InGameModel(seatingOrderModel, biddingModel);
            InGameView inGameView = new InGameView(inGameModel, seatingOrderModel, biddingModel);
            InGameController inGameController = new InGameController(controller, inGameView, inGameModel);

            controller.registerScene(CounterView.class, counterView);
            controller.registerScene(FastCounterView.class, fastCounterView);
            controller.registerScene(HomeView.class, homeView);
            controller.registerScene(SeatingSetupView.class, seatingSetupView);
            controller.registerScene(BiddingView.class, biddingView);
            controller.registerScene(InGameView.class, inGameView);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                // Do stuff before the app shuts down

                // Save the counter model to disk before shutting down.
                ioManager.saveCounterModel(counterModel);
                ioManager.savePlayerDatabaseModel(playerDatabase);
                ioManager.saveGamesDatabaseModel(gamesDatabase);
            }));


            // Display the window
            controller.switchScene(CounterView.class);
            window.setVisible(true);
        });
    }
}
