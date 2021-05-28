package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.builder.DishBuilderImpl;
import model.data.CookBook;
import model.data.Ingredient;
import model.data.Quantity;
import model.enumerative.DishCategory;
import model.enumerative.IngredientCategory;
import model.files_management.Paths;
import model.files_management.load.SerializableObjectsLoader;

import java.io.IOException;
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
    private MainMenuPaneController mainMenuPaneController;
    private Set<Ingredient> ingredientsOfNewDish;

    // Methods for controller set up 
    public void init() {
        setToEditCookBookButtonOnAction();
        setSaveNewDishButtonOnAction();
        loadCookBook();
        ingredientsOfNewDish = new HashSet<>();
        populateIngredientCategoryChoiceBox();
        populateDishCategoryChoiceBox();
        setIngredientsOfNewDishTabOnAction();
    }

    public void setParent(MainController mainController) {
        parent = mainController;
    }

    private void loadCookBook() {
        var loader = new SerializableObjectsLoader<CookBook>(Paths.COOK_BOOK_PATH);
        loader.load().ifPresent(loaded -> cookBook = loaded);
    }

    public void populateIngredientCategoryChoiceBox() {
        ingredientCategoryChoiceBox.getItems().setAll(IngredientCategory.values());
    }

    public void populateDishCategoryChoiceBox() {
        dishCategoryChoiceBox.getItems().setAll(DishCategory.values());
    }

    // Methods for button functionality
    public void setToEditCookBookButtonOnAction() {
        toEditCookBook.setOnAction(e -> parent.goToCookBookEdit());
    }

    public void setSaveNewDishButtonOnAction() {
        saveNewDishButton.setOnAction(e -> {
            try {
                addNewDishToCookBook();
                parent.goToMainMenu();
                mainMenuPaneController.displaySuccessfulAddOfNewDish();
            } catch (IOException f) {
                f.printStackTrace();
            }

        });
    }

    public void setAddIngredientButtonOnAction() {
        addIngredientsButton.setOnAction(e -> addIngredientToListOfIngredientToAdd());
    }

    public void addNewDishToCookBook() {
        cookBook.addDish(DishBuilderImpl.builder()
                .withName(inputName())
                .withRecipe(inputRecipe())
                .withDescription(inputDescription())
                .withIngredients(ingredientsOfNewDish)
                .withCategories(Set.of(inputCategory()))
                .withDuration(Duration.ofMinutes(inputTime()))
                .withServings(inputServings())
                .build()
        );
    }

    public void addIngredientToListOfIngredientToAdd() {
        ingredientsOfNewDish.add(new Ingredient(
                getIngredientNameFromTextField(),
                getIngredientCategoryFromChoiceBox(),
                new Quantity(getIngredientQuantityFromTextField(), getIngredientUnitFromTextAreaField())
        ));
        setIngredientTextFieldsToEmpty();
    }

    // Methods for get dish data 
    public String inputName() {
        return dishNameTextField.getText();
    }

    public String inputDescription() {
        return dishDescriptionTextField.getText();
    }

    public String inputRecipe() {
        return recipeTextField.getText();
    }

    public DishCategory inputCategory() {
        return dishCategoryChoiceBox.getValue();
    }

    public int inputTime() {
        return Integer.parseInt(neededTimeTextField.getText());
    }

    public int inputServings() {
        return Integer.parseInt(numberOfServingsTextField.getText());
    }

    // Methods for get ingredient data 
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

    // Methods for tabPane functionality
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
                putIngredientsOnList(ingredientsOfNewDish);
        });
    }
}
