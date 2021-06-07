package app;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        var controller = new MainController();
        controller.setStage(stage);
        controller.init();
        controller.start();
    }
}
