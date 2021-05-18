package view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseView extends JPanel {
    protected List<BaseView> subViews;

    public BaseView(){
        subViews = new ArrayList<>();
    }

    protected void registerSubView(BaseView view) {
        subViews.add(view);
    }

    /**
     * Run when the panel is loaded or when it should have it's view refreshed.
     */
    public void refresh() {
        subViews.forEach(view -> view.refresh()); // Call all subpanels to refresh as well.
    }

    /**
     * Initializes the JPanel the first time
     */
    abstract void initializeView();
}
