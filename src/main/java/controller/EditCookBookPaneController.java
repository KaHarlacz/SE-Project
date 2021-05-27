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

    private CookBook cookBook;

    private MainController parent;

    public void setParent(MainController main){
        parent = main;
    }

    public void init() {
        loadCookBook();
        putDishesOnList();
        setNavigationButtonsOnAction();
    }

    private void setNavigationButtonsOnAction() {
        addNewDishButton.setOnAction(e -> parent.goToAddNewDish());
        toMainMenuButton.setOnAction(e -> parent.goToMainMenu());
    }

    private void putDishesOnList() {
        //cookBook.getDishes().forEach(d -> dishListView.getItems().add(d.getName()));
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
