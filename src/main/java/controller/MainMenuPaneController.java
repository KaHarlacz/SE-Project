package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuPaneController {

    @FXML
    private Button toMenuPane;

    @FXML
    private Button toCookBookEdit;

    @FXML
    private Button toLeave;

//    @FXML
//    public void changeViewToEditCookBook() {
//
//    }

//    @FXML
//    public void changeViewToShoppingList() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dish_list_creation/choosingDishesPane.fxml"));
//        Stage stage = (Stage) toMenuPane.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        stage.setScene(scene);
//    }

//    @FXML
//    public void exitProgram() {
//        Stage stage = (Stage) toLeave.getScene().getWindow();
//        stage.close();
//    }

    public Button getToMenuPane() {
        return toMenuPane;
    }

    public Button getToCookBookEdit() {
        return toCookBookEdit;
    }

    public Button getToLeave() {
        return toLeave;
    }
}
