package app;

import controller.ChoosingDishesPaneController;
import controller.MainMenuPaneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    private Pane mainMenu;
    private Pane choosingDishes;
    // private Pane summary;

    private MainMenuPaneController mainMenuController;
    private ChoosingDishesPaneController choosingDishesController;

    public static void main(String[] args) {
        launch();
    }

    private void setSwitchingScenes(Stage stage) {
        mainMenuController.getToLeave().setOnAction(e -> this.exit(stage));
        mainMenuController.getToMenuPane().setOnAction(e -> stage.setScene(new Scene(choosingDishes)));
    }

    private void exit(Stage stage) {
        //mainMenuController.exit();
        choosingDishesController.exit();
    }

    private void loadScenes() throws IOException {
        var mainMenuLoader = new FXMLLoader(getClass().getResource("/fxml/main_menu/mainMenuPane.fxml"));
        mainMenu = mainMenuLoader.load();
        mainMenuController = mainMenuLoader.getController();

        var choosingDishesLoader = new FXMLLoader(getClass().getResource("/fxml/choosing_dishes/choosingDishesPane.fxml"));
        choosingDishes = choosingDishesLoader.load();
        choosingDishesController = choosingDishesLoader.getController();
    }

    private void setStage(Stage stage) {
        stage.setTitle("Shopping Organiser");
        stage.setScene(new Scene(mainMenu));
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadScenes();
        setSwitchingScenes(stage);
        setStage(stage);
        stage.setOnCloseRequest(e -> exit(stage));
    }
}
