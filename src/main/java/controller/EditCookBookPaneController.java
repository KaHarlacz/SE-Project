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
import model.files_management.Paths;
import model.files_management.SerialObjectLoader;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EditCookBookPaneController {
    @FXML
    private FiltersPaneController filtersPaneController;
    @FXML
    private Button confirmChangesButton;
    @FXML
    private Button toMainMenuButton;
    @FXML
    private Button undoChangesButton;
    @FXML
    private Button deleteDishButton;
    @FXML
    private Button addNewDishButton;
    @FXML
    private Button isFavouriteButton;
    @FXML
    private ImageView dishImage;
    @FXML
    private ListView<String> dishListView;
    @FXML
    private ListView<String> ingredientsNeededListView;
    @FXML
    private Text dishDescriptionText;
    @FXML
    private Text dishNameText;
    @FXML
    private TextArea recipeTextArea;
    @FXML
    private Tab dishesTab;

    private CookBook cookBook;

    private MainController parent;

    public void init() {
        loadCookBook();
        putDishesOnList(cookBook.getDishes());
        setDishesListOnAction();
        setNavigationButtonsOnAction();
        setDishesTabOnAction();
        setFavouriteButtonOnAction();
    }

    private void showDish(Dish dish) {
        showNameOf(dish);
        showRecipeOf(dish);
        showImageOf(dish);
        showIngredientsOf(dish);
        showDescriptionOf(dish);
        showFavouriteStatus(dish);
    }

    private void setDishesListOnAction() {
        dishListView.setOnMouseClicked(e -> showSelectedDish());
    }

    private void setFavouriteButtonOnAction() {
        isFavouriteButton.setOnAction(e -> changeFavouriteStatus());
    }

    private void setNavigationButtonsOnAction() {
        addNewDishButton.setOnAction(e -> parent.goToAddNewDish());
        toMainMenuButton.setOnAction(e -> parent.goToMainMenu());
    }

    private void setDishesTabOnAction() {
        dishesTab.setOnSelectionChanged(e -> {
            if (dishesTab.isSelected())
                putDishesOnList(cookBook.filterDishesUsing(filtersPaneController.getFilters()));
        });
    }

    private void showIngredientsOf(Dish dish) {
        ingredientsNeededListView.getItems().clear();
        ingredientsNeededListView.getItems().addAll(
                dish.getIngredients()
                        .stream()
                        .map(Ingredient::getName)
                        .toArray(String[]::new)
        );
    }

    private void changeFavouriteStatus() {
        getSelectedDish().ifPresent(dish -> {
            dish.setFavourite(!dish.isFavourite());
            showFavouriteStatus(dish);
        });
    }

    private void showFavouriteStatus(Dish dish) {
        if (!dish.isFavourite()) {
            isFavouriteButton.setText("Dodaj do ulubionych");
        } else {
            isFavouriteButton.setText("Usuń z ulubionych");
        }
    }

    private void showSelectedDish() {
        getSelectedDish().ifPresent(this::showDish);
    }

    private void showDescriptionOf(Dish dish) {
        dishDescriptionText.setText(dish.getDescription());
    }

    private Optional<Dish> getSelectedDish() {
        var selectedDishName = dishListView.getSelectionModel().getSelectedItem();
        return cookBook.getDishes()
                .stream()
                .filter(d -> d.getName().equals(selectedDishName))
                .findFirst();
    }

    private void putDishesOnList(Set<Dish> dishes) {
        dishListView.getItems().clear();
        dishListView.getItems().addAll(
                dishes.stream()
                        .map(Dish::getName)
                        .collect(Collectors.toSet())
        );
    }

    private void loadCookBook() {
        var loader = new SerialObjectLoader();
        var loaded = loader.load(Paths.COOK_BOOK_PATH);
        cookBook = loaded.map(o -> (CookBook) o).orElseGet(() -> new CookBook(Set.of()));
    }

    public void exit() {
        new SerialObjectLoader().save(cookBook, Paths.COOK_BOOK_PATH);
    }

    private void showNameOf(Dish dish) {
        dishNameText.setText(dish.getName());
    }

    private void showRecipeOf(Dish dish) {
        recipeTextArea.setText(dish.getRecipe());
    }

    private void showImageOf(Dish dish) {
        dishImage.setImage(dish.getImage());
    }

    public void setParent(MainController main) {
        parent = main;
    }
}
