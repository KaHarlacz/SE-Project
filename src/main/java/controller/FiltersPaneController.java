package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.data.CookBook;
import model.data.Ingredient;
import model.enumerative.DishCategory;
import model.filter.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
    private ListView<String> ingredientsListView;

    @FXML
    private Button addFilterIngredientButton;

    private List<CheckBox> favouriteCheckboxes;
    // It does not include allCategories checkbox
    private Map<CheckBox, DishCategory> categoriesCheckboxes = new HashMap<>();

    public void initialize() {
        wrapCategoriesCheckboxesWithEnums();
        wrapFavouriteCheckboxesIntoList();
        setDefaultCheckboxesSelected();
        setCategoriesCheckboxesOnAction();
        setFavouriteCheckboxesOnAction();
        // TODO: setAddFilterIngredientButtonOnAction();
    }

    // Same filters may be null - if so they will have no
    // effect on filtered set
    public List<Filter> getFilters() {
        return List.of(
                getNameFilter(),
                getFavouriteFilter(),
                getCategoriesFilter(),
                getIngredientsFilter()
        );
    }

    private void setFavouriteCheckboxesOnAction() {
        allCategoriesCheckbox.setOnAction(e -> {
            if(!isAnyCategoryCheckboxSelected() && !allCategoriesCheckbox.isSelected())
                allCategoriesCheckbox.fire();
        });

        for (var checkbox : favouriteCheckboxes) {
            checkbox.setOnAction(e -> {
                if(checkbox.isSelected())
                    uncheckOtherFavouriteCheckboxesBut(checkbox);
                else
                    ifNoneFavouriteIsSelectedSelectDefault();
            });
        }
    }

    private void setCategoriesCheckboxesOnAction() {
        var checkboxes = categoriesCheckboxes.keySet();

        for (var checkbox : checkboxes) {
            // This code is called after changing the state of the checkbox
            checkbox.setOnAction(e -> {
                if (checkbox.isSelected())
                    uncheckAllCategoriesCheckbox();
                else
                    checkAllCategoriesCheckboxIfNoneIsSelected();
            });
        }
    }

    private Filter getNameFilter() {
        var filterText = nameFilterTextField.getText();
        return new NameFilter(filterText);
    }

    private Filter getIngredientsFilter() {
        var filterIngredients = new HashSet<Ingredient>();

        var items = ingredientsListView.getItems();

        // Null filter will have no effect on filter process
        if (items.size() == 0)
            return null;

        for (var ingredientName : items) {
            filterIngredients.add(new Ingredient(ingredientName));
        }

        return new IngredientsFilter(filterIngredients);
    }

    private Filter getCategoriesFilter() {
        var filterCategories = new HashSet<DishCategory>();

        for (var entry : categoriesCheckboxes.entrySet()) {
            var checkbox = entry.getKey();
            var category = entry.getValue();

            if (checkbox.isSelected())
                filterCategories.add(category);
        }

        return new CategoriesFilter(filterCategories);
    }

    private Filter getFavouriteFilter() {
        Filter filter = null;

        if (onlyFavouriteCheckbox.isSelected())
            filter = new FavouriteFilter(true);

        if (onlyNotFavouriteCheckbox.isSelected())
            filter = new FavouriteFilter(false);

        return filter;
    }

    private void setDefaultCheckboxesSelected() {
        allCategoriesCheckbox.fire();
        allFavouriteCheckbox.fire();
    }

    private void uncheckAllCategoriesCheckbox() {
        if (allCategoriesCheckbox.isSelected())
            allCategoriesCheckbox.fire();
    }

    private void checkAllCategoriesCheckboxIfNoneIsSelected() {
        var isAnySelected = isAnyCategoryCheckboxSelected();

        if (!isAnySelected)
            allCategoriesCheckbox.fire();
    }

    private boolean isAnyCategoryCheckboxSelected() {
        var checkboxes = categoriesCheckboxes.keySet();

        return checkboxes.stream()
                .anyMatch(CheckBox::isSelected);
    }

    private void uncheckOtherFavouriteCheckboxesBut(CheckBox checkbox) {
        favouriteCheckboxes.stream()
                .filter(c -> !c.equals(checkbox))
                .forEach(this::uncheckIfSelected);
    }

    private void ifNoneFavouriteIsSelectedSelectDefault() {
        if(!onlyNotFavouriteCheckbox.isSelected() && !onlyFavouriteCheckbox.isSelected())
            allFavouriteCheckbox.fire();
    }

    private void uncheckIfSelected(CheckBox checkBox) {
        if(checkBox.isSelected())
            checkBox.fire();
    }

    private void wrapFavouriteCheckboxesIntoList() {
        favouriteCheckboxes = List.of(
                allFavouriteCheckbox,
                onlyFavouriteCheckbox,
                onlyNotFavouriteCheckbox
        );
    }

    private void wrapCategoriesCheckboxesWithEnums() {
        categoriesCheckboxes = Map.of(
                breakfastCheckbox, DishCategory.BREAKFAST,
                brunchCheckbox, DishCategory.BRUNCH,
                dinnerCheckbox, DishCategory.DINNER,
                dessertCheckbox, DishCategory.DESSERT,
                teaCheckbox, DishCategory.TEA,
                supperCheckbox, DishCategory.SUPPER,
                snackCheckbox, DishCategory.SNACK,
                otherCheckbox, DishCategory.OTHER
        );
    }
}
