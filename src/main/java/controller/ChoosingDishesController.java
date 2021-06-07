package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.data.CookBook;
import model.data.Dish;
import model.data.Ingredient;
import model.data.ShoppingList;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ChoosingDishesController extends ViewController {
    private ShoppingList shoppingList = ShoppingList.getInstance();
    private CookBook cookBook;

    @FXML
    private Button addDishButton;
    @FXML
    private Button deleteDishButton;
    @FXML
    private Button isFavouriteButton;
    @FXML
    private Button toSummaryButton;
    @FXML
    private Button toMainMenuButton;
    @FXML
    private FiltersPaneController filtersPaneController;
    @FXML
    private ImageView recipeImage;
    @FXML
    private ListView<String> dishesListView;
    @FXML
    private ListView<String> ingredientsNeededListView;
    @FXML
    private ListView<String> previewDishesListView;
    @FXML
    private Tab dishesTab;
    @FXML
    private Tab previewTab;
    @FXML
    private Text numberOfServingsText;
    @FXML
    private Text neededTimeText;
    @FXML
    private Text recipeDescriptionText;
    @FXML
    private Text recipeNameText;
    @FXML
    private Text selectedQuantityText;
    @FXML
    private TextArea recipeTextArea;

    @Override
    public void init() {
        initCookBook();
        putDishesOnList(cookBook.getDishes());
        setDishesListOnAction();
        setNavigationButtonsOnAction();
        setDishesTabOnAction();
        setQuantityButtonsOnAction();
        setPreviewTabOnAction();
        setFavouriteButtonOnAction();
    }

    @Override
    public void refresh() {
        putDishesOnList(cookBook.getDishes());
    }

    private void showDish(Dish dish) {
        showNameOf(dish);
        showRecipeOf(dish);
        showImageOf(dish);
        showSelectedQuantityOf(dish);
        showIngredientsOf(dish);
        showDescriptionOf(dish);
        showFavouriteStatusOf(dish);
        showServingsOf(dish);
        showDurationOf(dish);
    }

    private void showNameOf(Dish dish) {
        recipeNameText.setText(dish.toString());
    }

    private void showRecipeOf(Dish dish) {
        recipeTextArea.setText(dish.getRecipe());
    }

    private void showImageOf(Dish dish) {
        recipeImage.setImage(dish.getImage());
    }

    private void showSelectedQuantityOf(Dish dish) {
        var quantity = shoppingList.getSelectedDishesWithTimesSelected().get(dish);
        if (quantity == null)
            quantity = 0;
        selectedQuantityText.setText("Wybrano: " + quantity.toString() + " ");
    }

    private void showIngredientsOf(Dish dish) {
        ingredientsNeededListView.getItems().clear();
        ingredientsNeededListView.getItems().addAll(dish
                .getIngredients()
                .stream()
                .map(Ingredient::toString)
                .toArray(String[]::new)
        );
    }

    private void showDurationOf(Dish dish) {
        numberOfServingsText.setText("Liczba porcji: " + dish.getServings());
    }

    private void showDescriptionOf(Dish dish) {
        recipeDescriptionText.setText(dish.getDescription());
    }

    private void showFavouriteStatusOf(Dish dish) {
        if (!dish.isFavourite())
            isFavouriteButton.setText("Dodaj do ulubionych");
        else
            isFavouriteButton.setText("Usuń z ulubionych");
    }

    private void showServingsOf(Dish dish) {
        neededTimeText.setText("Czas przygotowania: " + dish.getDuration().toMinutes() + " min");
    }

    private void setFavouriteButtonOnAction() {
        isFavouriteButton.setOnAction(e -> changeFavouriteStatus());
    }

    private void changeFavouriteStatus() {
        selectedDish().ifPresent(dish -> {
            dish.setFavourite(!dish.isFavourite());
            showFavouriteStatusOf(dish);
        });
    }

    private void setQuantityButtonsOnAction() {
        addDishButton.setOnAction(e -> addSelectedDishToShoppingList());
        deleteDishButton.setOnAction(e -> deleteSelectedDishFromShoppingDish());
    }

    private void addSelectedDishToShoppingList() {
        selectedDish().ifPresent(dish -> {
            shoppingList.addIngredientsFrom(dish);
            showSelectedQuantityOf(dish);
        });
    }

    private void deleteSelectedDishFromShoppingDish() {
        selectedDish().ifPresent(dish -> {
            shoppingList.deleteIngredientsFrom(dish);
            showSelectedQuantityOf(dish);
        });
    }

    private void setNavigationButtonsOnAction() {
        toSummaryButton.setOnAction(e -> parent.goToSceneOf(next));
        toMainMenuButton.setOnAction(e -> parent.goToSceneOf(prev));
    }

    private void setDishesTabOnAction() {
        dishesTab.setOnSelectionChanged(e -> {
            if (dishesTab.isSelected())
                putDishesOnList(cookBook.filterDishesUsing(filtersPaneController.getFilters()));
        });
    }

    private void putDishesOnList(Set<Dish> dishes) {
        dishesListView.getItems().clear();
        dishesListView.getItems().addAll(
                dishes.stream()
                        .map(Dish::getName)
                        .collect(Collectors.toSet())
        );
    }

    private void setPreviewTabOnAction() {
        previewTab.setOnSelectionChanged(e -> {
            if (previewTab.isSelected())
                showPreviewOfSelectedDishes();
        });
    }

    private void showPreviewOfSelectedDishes() {
        previewDishesListView.getItems().clear();
        previewDishesListView.getItems().addAll(dishesNamesWithSelectedQuantities());
    }

    private List<String> dishesNamesWithSelectedQuantities() {
        return shoppingList.getSelectedDishesWithTimesSelected()
                .entrySet()
                .stream()
                .map(e -> e.getKey().getName() + " (" + e.getValue() + ")")
                .collect(Collectors.toList());
    }

    private Optional<Dish> selectedDish() {
        var selectedDishName = dishesListView.getSelectionModel().getSelectedItem();
        return cookBook.getDishes()
                .stream()
                .filter(d -> d.getName().equals(selectedDishName))
                .findFirst();
    }

    private void showSelectedDish() {
        selectedDish().ifPresent(this::showDish);
    }

    private void setDishesListOnAction() {
        dishesListView.setOnMouseClicked(e -> showSelectedDish());
    }

    private void initCookBook() {
        cookBook = CookBook.getInstance();
    }
}