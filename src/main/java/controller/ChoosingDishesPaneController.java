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
import model.files_management.Paths;
import model.files_management.SerialObjectLoader;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ChoosingDishesPaneController {
    @FXML
    private FiltersPaneController filtersPaneController;
    @FXML
    private Button toSummaryButton;
    @FXML
    private Button toMainMenuButton;
    @FXML
    private ListView<String> dishesListView;
    @FXML
    private ListView<String> ingredientsNeededListView;
    @FXML
    private TextArea recipeTextArea;
    @FXML
    private Button addDishButton;
    @FXML
    private Button deleteDishButton;
    @FXML
    private Text selectedQuantityText;
    @FXML
    private Text recipeNameText;
    @FXML
    private Text recipeDescriptionText;
    @FXML
    private ImageView recipeImage;
    @FXML
    private Tab dishesTab;
    @FXML
    private ListView<String> previewDishesListView;

    private CookBook cookBook;
    private ShoppingList shoppingList = ShoppingList.getInstance();

    private MainController parent;

    public void init() {
        loadCookBook();
        putDishesOnList(cookBook.getDishes());
        setDishesListOnAction();
        setNavigationButtonsOnAction();
        setDishesTabOnAction();
        setQuantityButtonsOnAction();
        setPreviewOnAction();
    }

    private void setPreviewOnAction() {
        previewDishesListView.getItems().addAll(dishesNamesWithSelectedQuantities());
    }

    private List<String> dishesNamesWithSelectedQuantities() {
        return shoppingList.getSelectedDishes()
                .entrySet()
                .stream()
                .map(e -> e.getKey().getName() + " (" + e.getValue() + ")")
                .collect(Collectors.toList());
    }

    private void setQuantityButtonsOnAction() {
        addDishButton.setOnAction(e -> addSelectedDishToShoppingList());
        deleteDishButton.setOnAction(e -> deleteSelectedDishFromShoppingDish());
    }

    private void addSelectedDishToShoppingList() {
        getSelectedDish().ifPresent(dish -> {
            System.out.println("Przed: " + shoppingList.getIngredients());
            System.out.println("Składniki: ");
            dish.getIngredients().forEach(System.out::println);
            shoppingList.addIngredientsFrom(dish);
            showSelectedQuantityOf(dish);
            System.out.println("Po: " + shoppingList.getIngredients());
        });
    }

    private void deleteSelectedDishFromShoppingDish() {
        getSelectedDish().ifPresent(dish -> {
            shoppingList.deleteIngredientsFrom(dish);
            showSelectedQuantityOf(dish);
        });
    }

    private void setDishesTabOnAction() {
        dishesTab.setOnSelectionChanged(e -> {
            if (dishesTab.isSelected())
                putDishesOnList(cookBook.filterDishesUsing(filtersPaneController.getFilters()));
        });
    }

    private void showSelectedDish() {
        var selectedDish = getSelectedDish();
        selectedDish.ifPresent(this::showDish);
    }

    private void showDish(Dish dish) {
        showNameOf(dish);
        showRecipeOf(dish);
        showImageOf(dish);
        showSelectedQuantityOf(dish);
        showIngredientsOf(dish);
        showDescriptionOf(dish);
    }

    private void showDescriptionOf(Dish dish) {
        // TODO: Create field "description" in dish
        recipeDescriptionText.setText("");
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

    private void showSelectedQuantityOf(Dish dish) {
        var quantity = shoppingList.getSelectedDishes().get(dish);
        if (quantity == null)
            quantity = 0;
        selectedQuantityText.setText("Wybrano: " + quantity.toString() + " ");
    }

    private void showImageOf(Dish dish) {
        recipeImage.setImage(dish.getImage());
    }

    private void showRecipeOf(Dish dish) {
        recipeTextArea.setText(dish.getRecipe());
    }

    private void showNameOf(Dish dish) {
        recipeNameText.setText(dish.getName());
    }

    private Optional<Dish> getSelectedDish() {
        var selectedDishName = dishesListView.getSelectionModel().getSelectedItem();
        return cookBook.getDishes()
                .stream()
                .filter(d -> d.getName().equals(selectedDishName))
                .findFirst();
    }

    private void setNavigationButtonsOnAction() {
        toSummaryButton.setOnAction(e -> parent.goToSummary());
        toMainMenuButton.setOnAction(e -> parent.goToMainMenu());
    }

    private void setDishesListOnAction() {
        dishesListView.setOnMouseClicked(e -> showSelectedDish());
    }

    private void putDishesOnList(Set<Dish> dishes) {
        dishesListView.getItems().clear();
        dishesListView.getItems().addAll(
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

    public void setParent(MainController main) {
        parent = main;
    }

    public void exit() {
//        var loader = new SerialObjectLoader();
//        loader.save(cookBook, Paths.COOK_BOOK_PATH);
    }
}
