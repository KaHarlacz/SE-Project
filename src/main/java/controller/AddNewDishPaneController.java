package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;
import model.files_management.Paths;
import model.files_management.SerialObjectLoader;

import model.data.CookBook;
import model.data.Dish;
import model.data.Ingredient;
import model.data.Quantity;
import model.enumerative.DishCategory;
import model.enumerative.IngredientCategory;

import java.util.HashSet;
import java.util.Set;
import java.time.Duration;
import java.util.stream.Collectors;

public class AddNewDishPaneController {

    @FXML
    private Button toEditCookBook;
    @FXML
    private Button saveNewDishButton;
    @FXML
    private ListView<String> addedIngredientsListView;
    @FXML
    private Button addIngredientsButton;
    @FXML
    private Button undoAddIngredientsButton;
    @FXML
    private TextArea dishNameTextField;
    @FXML
    private TextArea dishDescriptionTextField;
    @FXML
    private TextField neededTimeTextField;
    @FXML
    private TextField numberOfServingsTextField;
    @FXML
    private ChoiceBox<DishCategory> dishCategoryChoiceBox;
    @FXML
    private TextArea recipeTextField;
    @FXML
    private TextArea ingredientNameTextField;
    @FXML
    private Tab addedIngredientsTab;
    @FXML
    private ChoiceBox<IngredientCategory> ingredientCategoryChoiceBox;
    @FXML
    private TextArea ingredientQuantityTextArea;
    @FXML
    private TextArea ingredientUnitTextArea;

    private MainController parent;
    private CookBook cookBook;
    private Set<Ingredient> IngredientsOfNewDish;


    public void init() {
        toEditCookBook.setOnAction(e -> {
            parent.goToCookBookEdit();
            addNewDish();
        });
        loadCookBook();
        IngredientsOfNewDish = new HashSet<>();
        populateIngredientCategoryChoiceBox();
        populateDishCategoryChoiceBox();
        setIngredientsOfNewDishTabOnAction();
    }

    public void setParent(MainController mainController) {
        parent = mainController;
    }

    public void setSaveNewDishButtonOnAction() {
        saveNewDishButton.setOnAction(e -> addNewDish());
    }

    public void setAddIngredientButtonOnAction() {
        addIngredientsButton.setOnAction(e -> addIngredient());
    }

    public void addNewDish(){
        Dish newDish = new Dish(
            getDishNameFromTextField(),
            getRecipeFromTextField(),
            getDishDescriptionFromTextField(),
            IngredientsOfNewDish,
            Set.of(getDishCategoryFromChoiceBox()),
            null,
            Duration.ofMinutes(getNeededTimeFromTextField()),
            getNumberOfServingsFromTextField()
        );
        cookBook.addDish(newDish);
    }

    public void addIngredient() {
        IngredientsOfNewDish.add(new Ingredient(
            getIngredientNameFromTextField(),
            getIngredientCategoryFromChoiceBox(),
            new Quantity(getIngredientQuantityFromTextField(), getIngredientUnitFromTextAreaField())
        ));
        setIngredientTextFieldsToEmpty();
    }

    public String getDishNameFromTextField() {
        return dishNameTextField.getText();
    }

    public String getDishDescriptionFromTextField() {
        return dishDescriptionTextField.getText();
    }

    public String getRecipeFromTextField() {
        return recipeTextField.getText();
    }

    public DishCategory getDishCategoryFromChoiceBox() {
        return dishCategoryChoiceBox.getValue();
    }

    public int getNeededTimeFromTextField() {
        return Integer.parseInt(neededTimeTextField.getText());
    }

    public int getNumberOfServingsFromTextField() {
        return Integer.parseInt(numberOfServingsTextField.getText());
    }

    public String getIngredientNameFromTextField() {
        return ingredientNameTextField.getText();
    }

    public IngredientCategory getIngredientCategoryFromChoiceBox() {
        return ingredientCategoryChoiceBox.getValue();
    }

    public double getIngredientQuantityFromTextField() {
        return Integer.parseInt(ingredientQuantityTextArea.getText());
    }

    public String getIngredientUnitFromTextAreaField() {
        return ingredientUnitTextArea.getText();
    }

    public void setIngredientTextFieldsToEmpty() {
        ingredientNameTextField.setText("");
        ingredientQuantityTextArea.setText("");
        ingredientUnitTextArea.setText("");
    }

    public void populateIngredientCategoryChoiceBox() {
        ingredientCategoryChoiceBox.getItems().setAll(IngredientCategory.values());
    }

    public void populateDishCategoryChoiceBox() {
        dishCategoryChoiceBox.getItems().setAll(DishCategory.values());
    }

    private void loadCookBook() {
        var loader = new SerialObjectLoader();
        var loaded = loader.load(Paths.COOK_BOOK_PATH);
        cookBook = loaded.map(o -> (CookBook) o).orElseGet(() -> new CookBook(Set.of()));
    }

    private void putIngredientsOnList(Set<Ingredient> IngredientsOfNewDish) {
        addedIngredientsListView.getItems().clear();
        addedIngredientsListView.getItems().addAll(
                IngredientsOfNewDish.stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toSet())
        );
    }

    private void setIngredientsOfNewDishTabOnAction() {
        addedIngredientsTab.setOnSelectionChanged(e -> {
            if (addedIngredientsTab.isSelected())
                putIngredientsOnList(IngredientsOfNewDish);
        });
    }
}
