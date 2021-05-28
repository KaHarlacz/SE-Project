package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuPaneController {

    @FXML
    private Button toMenuPane;
    @FXML
    private Button toCookBookEdit;
    @FXML
    private Button toLeave;
    @FXML
    private Text messegePromptText;

    private MainController parent;

    public void init() {
        toLeave.setOnAction(e -> parent.exit());
        toCookBookEdit.setOnAction(e -> parent.goToCookBookEdit());
        toMenuPane.setOnAction(e -> parent.goToChoosingDishes());
    }

    public void setParent(MainController mainController) {
        parent = mainController;
    }

    public void displaySuccessfulShoppingListExport() {
        messegePromptText.setText("Pomyślnie wyeksportowano listę zakupów!");
    }

    public void displaySuccessfulAddOfNewDish() throws IOException {
        messegePromptText.setText("Pomyślenie dodano nowy przepis!");
    }
}
