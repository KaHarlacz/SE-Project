package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    public void changeViewToShoppingList() {

    }

    @FXML
    public void exitProgram() {
        Stage stage = (Stage) toLeave.getScene().getWindow();
        stage.close();
    }

}
