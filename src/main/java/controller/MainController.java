package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {

    private Stage stage;
    private Scene mainMenu;
    private Scene choosingDishes;
    private Scene summary;
    private Scene cookBookEdit;

    // Children controllers
    private MainMenuPaneController mainMenuController;
    private ChoosingDishesPaneController choosingDishesController;
    private SummaryPaneController summaryController;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        loadScenes();
        setSwitchingScenes();
        setUpStage();
    }

    private void loadScenes() throws IOException {
        loadMainMenu();
        loadChoosingDishes();
        loadSummary();
    }

    private void setSwitchingScenes() {
        setControllersParent();
        initControllers();
    }

    public void goToMainMenu() {
        setScene(mainMenu);
    }

    public void goToSummary() {
        setScene(summary);
    }

    public void goToCookBookEdit() {
        setScene(cookBookEdit);
    }

    public void goToChoosingDishes() {
        setScene(choosingDishes);
    }

    private void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public void exit() {
        choosingDishesController.exit();
        // TODO
        stage.close();
    }

    private void setUpStage() {
        stage.setOnCloseRequest(e -> this.exit());
        stage.setTitle("Shopping Organiser");
        stage.setScene(mainMenu);
        stage.show();
    }

    private void initControllers() {
        mainMenuController.init();
        choosingDishesController.init();
        summaryController.init();
    }

    private void setControllersParent() {
        mainMenuController.setParent(this);
        choosingDishesController.setParent(this);
        summaryController.setParent(this);
    }

    private void loadSummary() throws IOException {
        var summaryLoader = new FXMLLoader(getClass().getResource("/fxml/summary/summaryPane.fxml"));
        summary = new Scene(summaryLoader.load());
        summaryController = summaryLoader.getController();
    }

    private void loadChoosingDishes() throws IOException {
        var choosingDishesLoader = new FXMLLoader(getClass().getResource("/fxml/choosing_dishes/choosingDishesPane.fxml"));
        choosingDishes = new Scene(choosingDishesLoader.load());
        choosingDishesController = choosingDishesLoader.getController();
    }

    private void loadMainMenu() throws IOException {
        var mainMenuLoader = new FXMLLoader(getClass().getResource("/fxml/main_menu/mainMenuPane.fxml"));
        mainMenu = new Scene(mainMenuLoader.load());
        mainMenuController = mainMenuLoader.getController();
    }
}
