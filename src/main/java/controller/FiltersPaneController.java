package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.data.Ingredient;
import model.enumerative.DishCategory;
import model.filter.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FiltersPaneController {
    @FXML
    private Button addFilterIngredientButton;
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
    private TextField nameFilterTextField;

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
        return Stream.of(
                getNameFilter(),
                getFavouriteFilter(),
                getCategoriesFilter(),
                getIngredientsFilter()
        )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void setCategoriesCheckboxesOnAction() {
        setAllCategoriesCheckboxOnAction();
        setOtherCategoriesCheckboxesOnAction();
    }

    private void setFavouriteCheckboxesOnAction() {
        for (var checkbox : favouriteCheckboxes) {
            checkbox.setOnAction(e -> {
                if (checkbox.isSelected())
                    uncheckOtherFavouriteCheckboxesBut(checkbox);
                else
                    ifNoneFavouriteIsSelectedSelectDefault();
            });
        }
    }

    private void setAllCategoriesCheckboxOnAction() {
        allCategoriesCheckbox.setOnAction(e -> {
            if (allCategoriesCheckbox.isSelected())
                uncheckAllCategoriesCheckboxesBut(allCategoriesCheckbox);
            else if (isNoneCategoryCheckboxSelected())
                allCategoriesCheckbox.setSelected(true);
        });
    }

    private void setOtherCategoriesCheckboxesOnAction() {
        for (var checkbox : categoriesCheckboxesWithoutAllCategoriesCheckbox()) {
            checkbox.setOnAction(e -> {
                if (checkbox.isSelected())
                    uncheckAllCategoriesCheckbox();
                else if (isNoneCategoryCheckboxSelected())
                    allCategoriesCheckbox.setSelected(true);
            });
        }
    }

    private Set<CheckBox> categoriesCheckboxesWithoutAllCategoriesCheckbox() {
        return categoriesCheckboxes.keySet().stream()
                .filter(chb -> !chb.equals(allCategoriesCheckbox))
                .collect(Collectors.toSet());
    }

    private Filter getNameFilter() {
        var filterText = nameFilterTextField.getText();
        return new NameFilter(filterText);
    }

    private Filter getIngredientsFilter() {
        var selectedIngredientsNames = ingredientsListView.getItems();

        // Null filter will have no effect on filter process
        if (selectedIngredientsNames.size() == 0)
            return null;

        var filterIngredientsNames = new HashSet<>(selectedIngredientsNames);

        return new IngredientsNameFilter(filterIngredientsNames);
    }

    private Filter getCategoriesFilter() {
        if(allCategoriesCheckbox.isSelected())
            return null;

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

    private boolean isNoneCategoryCheckboxSelected() {
        var checkboxes = categoriesCheckboxes.keySet();
        return checkboxes.stream().noneMatch(CheckBox::isSelected);
    }

    private void uncheckOtherFavouriteCheckboxesBut(CheckBox checkbox) {
        favouriteCheckboxes.stream()
                .filter(c -> !c.equals(checkbox))
                .forEach(chb -> chb.setSelected(false));
    }

    private void ifNoneFavouriteIsSelectedSelectDefault() {
        if (!onlyNotFavouriteCheckbox.isSelected() && !onlyFavouriteCheckbox.isSelected())
            allFavouriteCheckbox.fire();
    }

    private void uncheckAllCategoriesCheckboxesBut(CheckBox checkBox) {
        categoriesCheckboxes.forEach((checkBx, category) -> checkBx.setSelected(false));
        checkBox.setSelected(true);
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
                allCategoriesCheckbox, DishCategory.ALL,
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
