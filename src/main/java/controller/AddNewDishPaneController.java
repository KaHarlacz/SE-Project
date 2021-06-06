package controller;

import files_management.Paths;
import files_management.load.SerializableObjectsLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.builder.DishBuilderImpl;
import model.data.CookBook;
import model.data.Ingredient;
import model.data.Quantity;
import model.enumerative.DishCategory;
import model.enumerative.IngredientCategory;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AddNewDishPaneController {

    @FXML
    private Button addIngredientsButton;
    @FXML
    private Button toEditCookBook;
    @FXML
    private Button saveNewDishButton;
    @FXML
    private Button undoAddIngredientsButton;
    @FXML
    private ChoiceBox<DishCategory> dishCategoryChoiceBox;
    @FXML
    private ChoiceBox<IngredientCategory> ingredientCategoryChoiceBox;
    @FXML
    private ListView<String> addedIngredientsListView;
    @FXML
    private Tab addedIngredientsTab;
    @FXML
    private TextArea dishDescriptionTextField;
    @FXML
    private TextArea dishNameTextField;
    @FXML
    private TextArea ingredientNameTextField;
    @FXML
    private TextArea ingredientQuantityTextArea;
    @FXML
    private TextArea ingredientUnitTextArea;
    @FXML
    private TextArea recipeTextField;
    @FXML
    private TextField neededTimeTextField;
    @FXML
    private TextField numberOfServingsTextField;

    private CookBook cookBook;
    private MainController parent;
    private Set<Ingredient> ingredientsOfNewDish = new HashSet<>();

    // Methods for controller set up 
    public void init() {
        loadCookBook();
        setToEditCookBookButtonOnAction();
        setSaveNewDishButtonOnAction();
        populateIngredientCategoryChoiceBox();
        populateDishCategoryChoiceBox();
        setIngredientsOfNewDishTabOnAction();
    }

    public void setParent(MainController mainController) {
        parent = mainController;
    }

    private void addInputDishToCookBook() {
        cookBook.addDish(DishBuilderImpl.builder()
                .withName(getInputName())
                .withRecipe(getInputRecipe())
                .withDescription(getInputDescription())
                .withIngredients(getInputIngredients())
                .withCategories(getInputCategories())
                .withDuration(getInputDuration())
                .withServings(getInputServings())
                .build()
        );
    }

    private void addToIngredientsOfDish() {
        ingredientsOfNewDish.add(Ingredient.builder()
                .name(getInputIngredientName())
                .category(getInputIngredientCategory())
                .quantity(getInputIngredientQuantity())
                .build()
        );
        setIngredientFieldsEmpty();
    }

    private void setSaveNewDishButtonOnAction() {
        saveNewDishButton.setOnAction(e -> {
            addInputDishToCookBook();
            parent.goToMainMenu();
            showAddSuccess();
        });
    }

    private void setIngredientFieldsEmpty() {
        ingredientNameTextField.setText("");
        ingredientQuantityTextArea.setText("");
        ingredientUnitTextArea.setText("");
    }

    private void putIngredientsOnList(Set<Ingredient> newDishIngredients) {
        addedIngredientsListView.getItems().clear();
        addedIngredientsListView.getItems().addAll(
                newDishIngredients.stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toSet())
        );
    }

    private void setIngredientsOfNewDishTabOnAction() {
        addedIngredientsTab.setOnSelectionChanged(e -> {
            if (addedIngredientsTab.isSelected())
                putIngredientsOnList(ingredientsOfNewDish);
        });
    }

    private void loadCookBook() {
        var loader = new SerializableObjectsLoader<CookBook>(Paths.COOK_BOOK_PATH);
        loader.load().ifPresent(loaded -> cookBook = loaded);
    }

    private void populateIngredientCategoryChoiceBox() {
        ingredientCategoryChoiceBox.getItems().setAll(IngredientCategory.values());
    }

    private void populateDishCategoryChoiceBox() {
        dishCategoryChoiceBox.getItems().setAll(DishCategory.values());
    }

    private void setToEditCookBookButtonOnAction() {
        toEditCookBook.setOnAction(e -> parent.goToCookBookEdit());
    }

    private void setAddIngredientButtonOnAction() {
        addIngredientsButton.setOnAction(e -> addToIngredientsOfDish());
    }

    public void showAddSuccess() {
        MainMenuPaneController mainMenuPane = parent.getMainMenuPaneController();
        mainMenuPane.displaySuccessfulAddOfNewDish();
    }

    private Set<Ingredient> getInputIngredients() {
        return ingredientsOfNewDish;
    }

    private String getInputName() {
        return dishNameTextField.getText();
    }

    private String getInputDescription() {
        return dishDescriptionTextField.getText();
    }

    private String getInputRecipe() {
        return recipeTextField.getText();
    }

    private Set<DishCategory> getInputCategories() {
        return Set.of(dishCategoryChoiceBox.getValue());
    }

    private Duration getInputDuration() {
        return Duration.ofMinutes(Integer.parseInt(neededTimeTextField.getText()));
    }

    private int getInputServings() {
        return Integer.parseInt(numberOfServingsTextField.getText());
    }

    private String getInputIngredientName() {
        return ingredientNameTextField.getText();
    }

    private IngredientCategory getInputIngredientCategory() {
        return ingredientCategoryChoiceBox.getValue();
    }

    private Quantity getInputIngredientQuantity() {
        return new Quantity(getInputQuantityValue(), getInputQuantityUnit());
    }

    // TODO: Should inform user that it is not correct data
    private double getInputQuantityValue() {
        return Integer.parseInt(ingredientQuantityTextArea.getText());
    }

    private String getInputQuantityUnit() {
        return ingredientUnitTextArea.getText();
    }
}
