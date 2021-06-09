package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;
import model.data.CookBook;
import model.data.Dish;
import model.data.Ingredient;

import java.util.*;
import java.util.stream.Collectors;

public class AddingNewDishPaneController extends ViewController {
    private CookBook cookBook = CookBook.getInstance();
    private Queue<Ingredient> ingredientsOfNewDish = new LinkedList<>();

    @FXML
    private Button addIngredientsButton;
    @FXML
    private Button toEditCookBook;
    @FXML
    private Button saveNewDishButton;
    @FXML
    private Button undoAddIngredientsButton;
    @FXML
    private ListView<String> addedIngredientsListView;
    @FXML
    private Tab addedIngredientsTab;
    @FXML
    private Text promptText;
    @FXML
    private IngredientFieldsPaneController ingredientFieldsPaneController;
    @FXML
    private DishFieldsPaneController dishFieldsPaneController;

    @Override
    public void init() {
        dishFieldsPaneController.init();
        ingredientFieldsPaneController.init();
        setUpDishCreation();
        setNavigationOnAction();
        setUndoButtonOnAction();
    }

    private void setUndoButtonOnAction() {
        undoAddIngredientsButton.setOnAction(e -> {
            ingredientsOfNewDish.poll();
            showIngredientsOnList(Set.copyOf(ingredientsOfNewDish));
        });
    }

    @Override
    public void refresh() {
        dishFieldsPaneController.refresh();
        ingredientFieldsPaneController.refresh();
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
                showIngredientsOnList(Set.copyOf(ingredientsOfNewDish));
        });
    }

    private void setSaveNewDishButtonOnAction() {
        saveNewDishButton.setOnAction(e -> {
            try {
                var inputDish = dishFieldsPaneController.getInput();
                inputDish.setIngredients(Set.copyOf(ingredientsOfNewDish));
                tryAddDishToCookBook(inputDish);
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
                addToIngredientsOfDish(ingredientFieldsPaneController.getInput());
            } catch (Exception ex) {
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
        showIngredientsOnList(Set.copyOf(ingredientsOfNewDish));
        ingredientFieldsPaneController.refresh();
    }

    private void clearIngredientsList() {
        showIngredientsOnList(Set.of());
    }

    private void showIngredientsOnList(Set<Ingredient> newDishIngredients) {
        addedIngredientsListView.getItems().clear();
        addedIngredientsListView.getItems().addAll(
                newDishIngredients.stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toSet())
        );
    }
}
