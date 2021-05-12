package model.data;

import model.data.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientTest {

    @Test
    public void getIngredientName() {
        Ingredient ingredientInstance = new Ingredient("jabłko", "kg");
        ingredientInstance.setName("pomarańcza");
        assertEquals("pomarańcza", ingredientInstance.getName());
    }

    @Test
    public void changingIngredientName() {
        Ingredient ingredientInstance = new Ingredient("jabłko", "kg");
        ingredientInstance.setName("pomarańcza");
        assertEquals("pomarańcza", ingredientInstance.getName());
    }

    @Test
    public void changingUnitName() {
        Ingredient instance = new Ingredient("jabłko", "kg");
        instance.setUnit("dekagram");
        assertEquals("jabłko", instance.getName());
    }
}
