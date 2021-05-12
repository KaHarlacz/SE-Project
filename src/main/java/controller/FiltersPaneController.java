package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FiltersPaneController {

    @FXML
    private TextField nameFilterTextField;

    @FXML
    private CheckBox allFavouriteCheckbox;

    @FXML
    private CheckBox onlyFavouriteCheckbox;

    @FXML
    private CheckBox onlyNotFavouriteCheckbox;

    @FXML
    private CheckBox allCategoriesCheckbox;

    @FXML
    private CheckBox breakfastCheckbox;

    @FXML
    private CheckBox brunchCheckbox;

    @FXML
    private CheckBox dinnerCheckbox;

    @FXML
    private CheckBox dessertCheckbox;

    @FXML
    private CheckBox teaCheckbox;

    @FXML
    private CheckBox supperCheckbox;

    @FXML
    private CheckBox snackCheckbox;

    @FXML
    private CheckBox otherCheckbox;

    @FXML
    private ListView<?> ingredientsListView;

    @FXML
    private Button addFilterIngredientButton;


    public TextField getNameFilterTextField() {
        return nameFilterTextField;
    }

    public CheckBox getAllFavouriteCheckbox() {
        return allFavouriteCheckbox;
    }

    public CheckBox getOnlyFavouriteCheckbox() {
        return onlyFavouriteCheckbox;
    }

    public CheckBox getOnlyNotFavouriteCheckbox() {
        return onlyNotFavouriteCheckbox;
    }

    public CheckBox getAllCategoriesCheckbox() {
        return allCategoriesCheckbox;
    }

    public CheckBox getBreakfastCheckbox() {
        return breakfastCheckbox;
    }

    public CheckBox getBrunchCheckbox() {
        return brunchCheckbox;
    }

    public CheckBox getDinnerCheckbox() {
        return dinnerCheckbox;
    }

    public CheckBox getDessertCheckbox() {
        return dessertCheckbox;
    }

    public CheckBox getTeaCheckbox() {
        return teaCheckbox;
    }

    public CheckBox getSupperCheckbox() {
        return supperCheckbox;
    }

    public CheckBox getSnackCheckbox() {
        return snackCheckbox;
    }

    public CheckBox getOtherCheckbox() {
        return otherCheckbox;
    }

    public ListView<?> getIngredientsListView() {
        return ingredientsListView;
    }

    public Button getAddFilterIngredientButton() {
        return addFilterIngredientButton;
    }
}
