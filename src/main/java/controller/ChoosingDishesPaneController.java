package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.data.CookBook;
import model.files_management.Paths;
import model.files_management.SerialObjectLoader;

import java.util.Set;

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
    private Text RecipeDescriptionText;
    @FXML
    private ImageView recipeImage;
    @FXML
    private Button undoChangesButton;
    @FXML
    private Button confirmChangesButton;

    private CookBook cookBook;

    private MainController parent;

    public void setParent(MainController main) {
        parent = main;
    }

    public void init() {
        loadCookBook();
        putDishesOnList();
        setDishListItemsOnAction();
        setNavigationButtonsOnAction();
    }

    private void setNavigationButtonsOnAction() {
        toSummaryButton.setOnAction(e -> parent.goToSummary());
        toMainMenuButton.setOnAction(e -> parent.goToMainMenu());
    }

    private void setDishListItemsOnAction() {
        dishesListView.setOnMouseClicked(e -> {
            var item = dishesListView.getSelectionModel().getSelectedItem();
            for (var dish : cookBook.getDishes()) {

                // TODO
                if (dish.getName().equals(item)) {
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
        cookBook = loaded.map(o -> (CookBook) o).orElseGet(() -> new CookBook(Set.of()));
    }

    public void exit() {
        var loader = new SerialObjectLoader();
        loader.save(cookBook, Paths.COOK_BOOK_PATH);
    }
}
