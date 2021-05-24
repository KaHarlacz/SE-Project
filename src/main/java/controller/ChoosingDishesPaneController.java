package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.data.CookBook;
import model.data.Dish;
import model.data.Ingredient;
import model.files_management.Paths;
import model.files_management.SerialObjectLoader;

import java.util.Set;

public class ChoosingDishesPaneController {
    @FXML
    private FiltersPaneController filtersPaneController;
    @FXML
    private Button toSummaryButton;
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

    private CookBook cookBook;

    public void initialize() {
        loadCookBook();
        putDishesOnList();
        setDishListItemsOnAction();

    }

    private void setDishListItemsOnAction() {
        dishesListView.setOnMouseClicked(e -> {
            var item = dishesListView.getSelectionModel().getSelectedItem();
            for (var dish : cookBook.getDishes()) {
                if(dish.getName().equals(item)) {
                    recipeTextArea.setText(dish.getRecipe());
                }
            }
        });
    }

    private void putDishesOnList() {
        cookBook.getDishes().forEach(d -> dishesListView.getItems().add(d.getName()));
    }

    private void loadCookBook() {
        var loader = new SerialObjectLoader();
        var loaded = loader.load(Paths.COOK_BOOK_PATH);

        if (loaded.isPresent())
            cookBook = (CookBook) loaded.get();
        else
            cookBook = new CookBook(Set.of(new Dish(
                    "Zupa pomidorowa",
                    "Pokrój pomidory",
                    Set.of(new Ingredient("Pomidor")),
                    null,
                    null,
                    null,
                    2
            )));
    }

    public void exit() {
        var loader = new SerialObjectLoader();
        loader.save(cookBook, Paths.COOK_BOOK_PATH);
    }
}
