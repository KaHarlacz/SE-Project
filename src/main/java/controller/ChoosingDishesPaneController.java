package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.data.CookBook;

public class ChoosingDishesPaneController {

    @FXML
    private FiltersPaneController filtersPaneController;

    @FXML
    private Button toSummaryButton;

    @FXML
    private Button toMainMenuButton;

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

    private CookBook cookBook;

    public void initialize() {

    }
}
