import com.formdev.flatlaf.FlatLightLaf;
import controller.CounterController;
import controller.FastCounterController;
import controller.HomeController;
import controller.MainWindowController;
import io.IOManager;
import model.CounterModel;
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

            CounterView counterView = new CounterView(counterModel);
            CounterController counterController = new CounterController(controller, counterModel, counterView);

            FastCounterView fastCounterView = new FastCounterView(counterModel);
            FastCounterController fastCounterController = new FastCounterController(controller, counterModel, fastCounterView);


            HomeView homeView = new HomeView();
            HomeController homeController = new HomeController(controller, homeView);

            SeatingSetupView seatingSetupView = new SeatingSetupView();

            controller.registerScene(CounterView.class, counterView);
            controller.registerScene(FastCounterView.class, fastCounterView);
            controller.registerScene(HomeView.class, homeView);
            controller.registerScene(SeatingSetupView.class, seatingSetupView);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                // Do stuff before the app shuts down

                // Save the counter model to disk before shutting down.
                ioManager.saveCounterModel(counterModel);
            }));


            // Display the window
            controller.switchScene(CounterView.class);
            window.setVisible(true);
        });
    }
}
