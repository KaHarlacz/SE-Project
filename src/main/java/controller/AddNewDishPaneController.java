package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class AddNewDishPaneController {

    @FXML
    private Button toEditCookBook;

    @FXML
    private Button saveNewDishButton;

    @FXML
    private ListView<?> addedIngredientsListView;

    @FXML
    private Button addIngredientsButton;

    @FXML
    private Button undoAddIngredientsButton;

    @FXML
    private TextArea dishNameTextField;

    @FXML
    private TextArea dishDescriptionTextField;

    @FXML
    private ChoiceBox<?> dishCategoryChoiceBox;

    @FXML
    private TextArea recipeTextField;

    @FXML
    private TextArea ingredientNameTextField;

    @FXML
    private ChoiceBox<?> ingredientCategoryChoiceBox;

    @FXML
    private TextArea ingredientQuantityTextArea;

    @FXML
    private TextArea ingredientUnitTextArea;

}
