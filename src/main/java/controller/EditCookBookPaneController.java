package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.builder.DishBuilderImpl;
import model.data.CookBook;
import model.data.Dish;
import model.data.Ingredient;
import model.files_management.Paths;
import model.files_management.SerialObjectLoader;

import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EditCookBookPaneController {
    @FXML
    private Button addNewDishButton;
    @FXML
    private Button confirmChangesButton;
    @FXML
    private Button deleteDishButton;
    @FXML
    private Button isFavouriteButton;
    @FXML
    private Button toMainMenuButton;
    @FXML
    private Button undoChangesButton;
    @FXML
    private FiltersPaneController filtersPaneController;
    @FXML
    private ImageView dishImage;
    @FXML
    private ListView<String> dishListView;
    @FXML
    private ListView<String> ingredientsNeededListView;
    @FXML
    private Tab dishesTab;
    @FXML
    private TextArea recipeTextArea;
    @FXML
    private TextField dishDescriptionText;
    @FXML
    private TextField dishNameText;
    @FXML
    private TextField neededTimeTextField;
    @FXML
    private TextField numberOfServingsTextField;

    private CookBook cookBook;
    private MainController parent;

    // Methods for controller set up
    public void init() throws IOException {
        loadCookBook();
        putDishesOnList(cookBook.getDishes());
        setDishesListOnAction();
        setNavigationButtonsOnAction();
        setDishesTabOnAction();
        setFavouriteButtonOnAction();
        setUndoChangesButtonOnAction();
        setDeleteDishButtonOnAction();
        setConfirmButtonOnAction();
    }

    private void loadCookBook() {
        var loader = new SerialObjectLoader();
        var loaded = loader.load(Paths.COOK_BOOK_PATH);
        cookBook = loaded.map(o -> (CookBook) o).orElseGet(() -> new CookBook(Set.of()));
    }

    public void exit() {
        new SerialObjectLoader().save(cookBook, Paths.COOK_BOOK_PATH);
    }

    private void putDishesOnList(Set<Dish> dishes) {
        dishListView.getItems().clear();
        dishListView.getItems().addAll(
                dishes.stream()
                        .map(Dish::getName)
                        .collect(Collectors.toSet())
        );
    }

    public void setParent(MainController main) {
        parent = main;
    }

    // Methods for button functionality
    private void setConfirmButtonOnAction() throws IOException {
        confirmChangesButton.setOnAction(e -> { try {
                var modifiedDish = createDishFromInputData();
                var selectedDish = getSelectedDish();
                selectedDish.ifPresent(cookBook::deleteDish);
                modifiedDish.ifPresent(cookBook::addDish);
            } catch (IOException f){
                f.printStackTrace();
            }
        });
    }

    private Optional<Dish> createDishFromInputData() throws IOException {
        // TODO: Way to add categories and image needed
        var builder = DishBuilderImpl.builder()
                .withName(dishNameText.getText())
                .withDescription(dishDescriptionText.getText())
                .withIngredients(getInputIngredients())
                .withRecipe(recipeTextArea.getText());

        getInputDuration().ifPresent(builder::withDuration);
        getInputServings().ifPresent(builder::withServings);

        return Optional.of(builder.get());
    }

    private void setDeleteDishButtonOnAction() {
        deleteDishButton.setOnAction(e -> {
            var selectedDish = getSelectedDish();
            selectedDish.ifPresent(cookBook::deleteDish);
        });
    }

    private void setUndoChangesButtonOnAction() {
        undoChangesButton.setOnAction(e ->
            getSelectedDish().ifPresent(this::showDish)
        );
    }

    private void setFavouriteButtonOnAction() {
        isFavouriteButton.setOnAction(e -> changeFavouriteStatus());
    }

    private void changeFavouriteStatus() {
        getSelectedDish().ifPresent(dish -> {
            dish.setFavourite(!dish.isFavourite());
            showFavouriteStatus(dish);
        });
    }

    private void setNavigationButtonsOnAction() {
        addNewDishButton.setOnAction(e -> parent.goToAddNewDish());
        toMainMenuButton.setOnAction(e -> parent.goToMainMenu());
    }

    // Methods for display dish data
    private void showDish(Dish dish) {
        showNameOf(dish);
        showRecipeOf(dish);
        showImageOf(dish);
        showIngredientsOf(dish);
        showDescriptionOf(dish);
        showFavouriteStatus(dish);
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

    private void showNameOf(Dish dish) {
        dishNameText.setText(dish.getName());
    }

    private void showRecipeOf(Dish dish) {
        recipeTextArea.setText(dish.getRecipe());
    }

    private void showImageOf(Dish dish) {
        dishImage.setImage(dish.getImage());
    }

    private void showDescriptionOf(Dish dish) {
        dishDescriptionText.setText(dish.getDescription());
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

    private Optional<Dish> getSelectedDish() {
        var selectedDishName = dishListView.getSelectionModel().getSelectedItem();
        return cookBook.getDishes()
                .stream()
                .filter(d -> d.getName().equals(selectedDishName))
                .findFirst();
    }

    // Methods to get new data of dish
    private Optional<Integer> getInputServings() {
        return Optional.of(Integer.parseInt(numberOfServingsTextField.getText()));
    }

    private Optional<Duration> getInputDuration() {
        return Optional.of(Duration.ofMinutes(Long.parseLong(neededTimeTextField.getText())));
    }

    private Set<Ingredient> getInputIngredients() {
        var result = new HashSet<Ingredient>();
        var optionals = ingredientsNeededListView.getItems()
                .stream()
                .map(this::getIngredientFromName)
                .collect(Collectors.toSet());
        optionals.forEach(optional -> optional.ifPresent(result::add));
        return result;
    }

    private Optional<Ingredient> getIngredientFromName(String name) {
        return cookBook.getAvailableIngredients().stream()
                .filter(ingredient -> ingredient.getName().equals(name))
                .findFirst();
    }

    // Methods for tabPane functionality
    private void setDishesListOnAction() {
        dishListView.setOnMouseClicked(e -> showSelectedDish());
    }

    private void setDishesTabOnAction() {
        dishesTab.setOnSelectionChanged(e -> {
            if (dishesTab.isSelected())
                putDishesOnList(cookBook.filterDishesUsing(filtersPaneController.getFilters()));
        });
    }
}
