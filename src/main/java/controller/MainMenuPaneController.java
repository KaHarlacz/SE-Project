package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MainMenuPaneController {

    @FXML
    private Button toCookBookEdit;
    @FXML
    private Button toLeave;
    @FXML
    private Button toMenuPane;
    @FXML
    private Text messagePromptText;

    private MainController parent;

    public void init() {
        setNavigationButtonsOnAction();
    }

    private void setNavigationButtonsOnAction() {
        toLeave.setOnAction(e -> parent.exit());
        toCookBookEdit.setOnAction(e -> parent.goToCookBookEdit());
        toMenuPane.setOnAction(e -> parent.goToChoosingDishes());
    }

    public void setParent(MainController mainController) {
        parent = mainController;
    }

    public void displaySuccessfulShoppingListExport() {
        messagePromptText.setText("Pomyślnie wyeksportowano listę zakupów!");
    }

    public void displaySuccessfulAddOfNewDish() throws IOException {
        messagePromptText.setText("Pomyślenie dodano nowy przepis!");
    }
}
