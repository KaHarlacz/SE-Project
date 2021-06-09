package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.builder.DishBuilderImpl;
import model.data.Dish;
import model.enumerative.DishCategory;

import java.time.Duration;
import java.util.InputMismatchException;
import java.util.Set;

public class DishFieldsPaneController extends ViewController implements InputController<Dish> {
    @FXML
    private ChoiceBox<DishCategory> categoriesChoiceBox;

    @FXML
    private TextArea nameTextArea;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField durationTextField;

    @FXML
    private TextField servingsTextField;

    @FXML
    private TextArea recipeTextArea;

    @Override
    void init() {
        setUpDishCategoriesChoiceBox();
    }

    @Override
    void refresh() {
        categoriesChoiceBox.setValue(DishCategory.OTHER);
        nameTextArea.clear();
        descriptionTextArea.clear();
        durationTextField.clear();
        servingsTextField.clear();
        recipeTextArea.clear();
    }

    @Override
    public Dish getInput() {
        try {
            return buildDish();
        } catch (Exception e) {
            throw new InputMismatchException("Incorrect dish data input");
        }
    }

    private Dish buildDish() {
        return DishBuilderImpl.builder()
                .name(getInputName())
                .recipe(getInputRecipe())
                .description(getInputDescription())
                .categories(getInputCategories())
                .duration(getInputDuration())
                .servings(getInputServings())
                .build();
    }

    private void setUpDishCategoriesChoiceBox() {
        categoriesChoiceBox.getItems().setAll(DishCategory.values());
        categoriesChoiceBox.setValue(DishCategory.OTHER);
    }

    private String getInputName() {
        return nameTextArea.getText();
    }

    private String getInputDescription() {
        return descriptionTextArea.getText();
    }

    private String getInputRecipe() {
        return recipeTextArea.getText();
    }

    private Set<DishCategory> getInputCategories() {
        return Set.of(categoriesChoiceBox.getValue());
    }

    private Duration getInputDuration() {
        return Duration.ofMinutes(Integer.parseInt(durationTextField.getText()));
    }

    private int getInputServings() {
        return Integer.parseInt(servingsTextField.getText());
    }
}
