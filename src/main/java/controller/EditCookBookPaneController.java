package controller;

import files_management.Paths;
import files_management.export.SerializableObjectsExporter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.builder.DishBuilderImpl;
import model.data.CookBook;
import model.data.Dish;
import model.data.Ingredient;

import java.time.Duration;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EditCookBookPaneController extends ViewController {
    private CookBook cookBook;

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

    @Override
    public void init() {
        loadCookBook();
        showDishesOnList(cookBook.getDishes());
        setDishesListOnAction();
        setNavigationButtonsOnAction();
        setDishesTabOnAction();
        setFavouriteButtonOnAction();
        setUndoChangesButtonOnAction();
        setDeleteDishButtonOnAction();
        setConfirmButtonOnAction();
    }

    @Override
    public void refresh() {
        showDishesOnList(cookBook.getDishes());
    }

    private void setDishesListOnAction() {
        dishListView.setOnMouseClicked(e -> showSelectedDish());
    }

    private void setDishesTabOnAction() {
        dishesTab.setOnSelectionChanged(e -> {
            if (dishesTab.isSelected())
                showDishesOnList(cookBook.filterDishesUsing(filtersPaneController.getFilters()));
        });
    }

    private void setConfirmButtonOnAction() {
        confirmChangesButton.setOnAction(e -> {
            var modifiedDish = createDishFromInputData();
            var selectedDish = getSelectedDish();
            selectedDish.ifPresent(cookBook::deleteDish);
            modifiedDish.ifPresent(cookBook::addDish);
        });
    }

    private void loadCookBook() {
        cookBook = CookBook.getInstance();
    }

    private void showDishesOnList(Set<Dish> dishes) {
        dishListView.getItems().clear();
        dishListView.getItems().addAll(
                dishes.stream()
                        .map(Dish::getName)
                        .collect(Collectors.toSet())
        );
    }

    private Optional<Dish> createDishFromInputData() {
        // TODO: Way to add categories and image needed
        var builder = DishBuilderImpl.builder()
                .withName(dishNameText.getText())
                .withDescription(dishDescriptionText.getText())
                .withIngredients(getInputIngredients())
                .withRecipe(recipeTextArea.getText());

        getInputDuration().ifPresent(builder::withDuration);
        getInputServings().ifPresent(builder::withServings);

        return Optional.of(builder.build());
    }

    private void setDeleteDishButtonOnAction() {
        deleteDishButton.setOnAction(e -> {
            var selectedDish = getSelectedDish();
            selectedDish.ifPresent(cookBook::deleteDish);
            showDishesOnList(cookBook.getDishes());
            saveCookBook();
        });
    }

    private void saveCookBook() {
        var exporter = new SerializableObjectsExporter<CookBook>(Paths.COOK_BOOK);
        exporter.export(cookBook);
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
        addNewDishButton.setOnAction(e -> parent.goToSceneOf(next));
        toMainMenuButton.setOnAction(e -> parent.goToSceneOf(prev));
    }

    private void showDish(Dish dish) {
        showNameOf(dish);
        showRecipeOf(dish);
        showImageOf(dish);
        showServingsOf(dish);
        showDurationOf(dish);
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

    private Optional<Dish> getSelectedDish() {
        var selectedDishName = dishListView.getSelectionModel().getSelectedItem();
        return cookBook.getDishes()
                .stream()
                .filter(d -> d.getName().equals(selectedDishName))
                .findFirst();
    }

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

    private void showDurationOf(Dish dish) {
        neededTimeTextField.setText(String.valueOf(dish.getDuration().toMinutes()));
    }

    private void showServingsOf(Dish dish) {
        numberOfServingsTextField.setText(String.valueOf(dish.getServings()));
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
}
