package controller;

import javafx.stage.Stage;

public abstract class ApplicationController extends Controller {
    protected Stage stage;
    protected ViewController currentController;

    public abstract void start();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    void goToSceneOf(ViewController controller) {
        if (stage == null)
            throw new IllegalStateException("Stage not initialized");
        if (controller == null)
            throw new IllegalStateException("Cannot go to scene of null controller");

        currentController.exit();
        currentController = controller;
        currentController.refresh();
        currentController.show(stage);
    }
}
