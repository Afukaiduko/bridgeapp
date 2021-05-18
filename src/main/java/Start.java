import com.formdev.flatlaf.FlatLightLaf;
import controller.CounterController;
import controller.FastCounterController;
import controller.MainWindowController;
import io.IOManager;
import model.CounterModel;
import view.CounterView;
import view.FastCounterView;
import view.MainWindow;

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


            controller.registerPanel(CounterView.class, counterView);
            controller.registerPanel(FastCounterView.class, fastCounterView);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                // Do stuff before the app shuts down

                // Save the counter model to disk before shutting down.
                ioManager.saveCounterModel(counterModel);
            }));


            // Display the window
            controller.switchPanels(CounterView.class);
            window.setVisible(true);
        });
    }
}
