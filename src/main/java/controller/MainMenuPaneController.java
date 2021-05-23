package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuPaneController {

    @FXML
    private Button toMenuPane;

    @FXML
    private Button toCookBookEdit;

    @FXML
    private Button toLeave;

    @FXML
    public void changeViewToEditCookBook() {

    }

    @FXML
    public void changeViewToShoppingList() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dish_list_creation/menuPane.fxml"));
        Stage stage = (Stage) toMenuPane.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    public void exitProgram() {
        Stage stage = (Stage) toLeave.getScene().getWindow();
        stage.close();
    }

}
