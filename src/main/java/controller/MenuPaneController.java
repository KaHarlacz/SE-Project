package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuPaneController {

    @FXML
    private FiltersPaneController filtersPaneController;

    @FXML
    private Button toSummaryButton;

    @FXML
    private ListView<?> dishesListView;

    @FXML
    private ListView<?> ingredientsNeededListView;

    @FXML
    private TextArea recipeTextArea;

    @FXML
    private Button addDishButton;

    @FXML
    private Button deleteDishButton;

    @FXML
    private Text selectedQuantityText;

    public void initialize() {

    }
}
