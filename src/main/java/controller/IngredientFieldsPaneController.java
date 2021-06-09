package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import model.data.Ingredient;
import model.data.Quantity;
import model.enumerative.IngredientCategory;

import java.util.InputMismatchException;

public class IngredientFieldsPaneController extends ViewController implements InputController<Ingredient> {
    @FXML
    private TextArea nameTextArea;

    @FXML
    private ChoiceBox<IngredientCategory> categoriesChoiceBox;

    @FXML
    private TextArea quantityValueTextArea;

    @FXML
    private TextArea quantityUnitTextArea;

    @Override
    public Ingredient getInput() {
        try {
            return Ingredient.builder()
                    .name(getInputIngredientName())
                    .category(getInputIngredientCategory())
                    .quantity(getInputIngredientQuantity())
                    .build();
        } catch (Exception e) {
            throw new InputMismatchException("Input data is not correct");
        }
    }

    @Override
    void init() {
        setUpCategoriesCheckBox();
    }

    @Override
    void refresh() {
        nameTextArea.clear();
        quantityValueTextArea.clear();
        quantityUnitTextArea.clear();
        categoriesChoiceBox.setValue(IngredientCategory.OTHER);
    }

    private String getInputIngredientName() {
        return nameTextArea.getText();
    }

    private IngredientCategory getInputIngredientCategory() {
        return categoriesChoiceBox.getValue();
    }

    private Quantity getInputIngredientQuantity() {
        return new Quantity(getInputQuantityValue(), getInputQuantityUnit());
    }

    private double getInputQuantityValue() {
        return Integer.parseInt(quantityValueTextArea.getText());
    }

    private String getInputQuantityUnit() {
        return quantityUnitTextArea.getText();
    }

    private void setUpCategoriesCheckBox() {
        categoriesChoiceBox.getItems().addAll(IngredientCategory.values());
        categoriesChoiceBox.setValue(IngredientCategory.OTHER);
    }
}
