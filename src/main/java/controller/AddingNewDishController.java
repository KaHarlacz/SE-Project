package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.builder.DishBuilderImpl;
import model.data.CookBook;
import model.data.Dish;
import model.data.Ingredient;
import model.data.Quantity;
import model.enumerative.DishCategory;
import model.enumerative.IngredientCategory;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class AddingNewDishController extends ViewController {
    private CookBook cookBook;
    private Set<Ingredient> ingredientsOfNewDish = new HashSet<>();

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
    @FXML
    private Text promptText;

    @Override
    public void init() {
        loadCookBook();
        setUpDishCreation();
        setNavigationOnAction();
        setUpIngredientCategoriesChoiceBox();
        setUpDishCategoriesChoiceBox();
    }

    @Override
    public void refresh() {
        clearDishFields();
        clearIngredientFields();
        clearIngredientsList();
        ingredientsOfNewDish.clear();
    }

    private void setUpDishCreation() {
        setSaveNewDishButtonOnAction();
        setAddIngredientButtonOnAction();
        setIngredientsOfNewDishTabOnAction();
    }

    private void setNavigationOnAction() {
        toEditCookBook.setOnAction(e -> parent.goToSceneOf(prev));
    }

    private void setIngredientsOfNewDishTabOnAction() {
        addedIngredientsTab.setOnSelectionChanged(e -> {
            if (addedIngredientsTab.isSelected())
                showIngredientsOnList(ingredientsOfNewDish);
        });
    }

    private void setSaveNewDishButtonOnAction() {
        saveNewDishButton.setOnAction(e -> {
            try {
                tryAddDishToCookBook(getInputDish());
                showSuccessMessageIf(true);
                refresh();
            } catch (IllegalArgumentException ex) {
                showSuccessMessageIf(false);
            }
        });
    }

    private void showSuccessMessageIf(boolean success) {
        var timer = new Timer();
        var eraseTask = getEraseTask(timer);

        if (success)
            promptText.setText("Przepis został zapisany :D");
        else
            promptText.setText("Niepoprawne dane, popraw je :(");

        timer.schedule(eraseTask, 2000L);
    }

    private TimerTask getEraseTask(Timer timer) {
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> promptText.setText(""));
                timer.cancel();
            }
        };
    }

    private void setAddIngredientButtonOnAction() {
        addIngredientsButton.setOnAction(e -> {
            try {
                addToIngredientsOfDish(getInputIngredient());
            } catch (IllegalArgumentException ex) {
                showSuccessMessageIf(false);
            }
        });
    }

    private void tryAddDishToCookBook(Dish dish) {
        try {
            cookBook.addDish(dish);
        } catch (IllegalArgumentException e) {
            showSuccessMessageIf(false);
        }
    }

    private void addToIngredientsOfDish(Ingredient inputIngredient) {
        ingredientsOfNewDish.add(inputIngredient);
        showIngredientsOnList(ingredientsOfNewDish);
        clearIngredientFields();
    }

    private void clearDishFields() {
        dishNameTextField.clear();
        dishDescriptionTextField.clear();
        numberOfServingsTextField.clear();
        neededTimeTextField.clear();
        recipeTextField.clear();
    }

    private void clearIngredientsList() {
        showIngredientsOnList(Set.of());
    }

    private void clearIngredientFields() {
        ingredientNameTextField.clear();
        ingredientQuantityTextArea.clear();
        ingredientUnitTextArea.clear();
    }

    private void showIngredientsOnList(Set<Ingredient> newDishIngredients) {
        addedIngredientsListView.getItems().clear();
        addedIngredientsListView.getItems().addAll(
                newDishIngredients.stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toSet())
        );
    }

    private void loadCookBook() {
        cookBook = CookBook.getInstance();
    }

    private void setUpIngredientCategoriesChoiceBox() {
        ingredientCategoryChoiceBox.getItems().setAll(IngredientCategory.values());
        ingredientCategoryChoiceBox.setValue(IngredientCategory.OTHER);
    }

    private void setUpDishCategoriesChoiceBox() {
        dishCategoryChoiceBox.getItems().setAll(DishCategory.values());
        dishCategoryChoiceBox.setValue(DishCategory.OTHER);
    }

    private Dish getInputDish() {
        return DishBuilderImpl.builder()
                .withName(getInputName())
                .withRecipe(getInputRecipe())
                .withDescription(getInputDescription())
                .withIngredients(getInputIngredients())
                .withCategories(getInputCategories())
                .withDuration(getInputDuration())
                .withServings(getInputServings())
                .build();
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

    private java.time.Duration getInputDuration() {
        return java.time.Duration.ofMinutes(Integer.parseInt(neededTimeTextField.getText()));
    }

    private int getInputServings() {
        return Integer.parseInt(numberOfServingsTextField.getText());
    }

    private Set<Ingredient> getInputIngredients() {
        return ingredientsOfNewDish;
    }

    private Ingredient getInputIngredient() {
        return Ingredient.builder()
                .name(getInputIngredientName())
                .category(getInputIngredientCategory())
                .quantity(getInputIngredientQuantity())
                .build();
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

    private double getInputQuantityValue() {
        return Integer.parseInt(ingredientQuantityTextArea.getText());
    }

    private String getInputQuantityUnit() {
        return ingredientUnitTextArea.getText();
    }
}
