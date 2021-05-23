package model.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class IngredientTest {

    @Test
    public void getIngredientName() {
        Ingredient ingredientInstance = new Ingredient("jabłko", null, mock(Quantity.class));
        ingredientInstance.setName("pomarańcza");
        assertEquals("pomarańcza", ingredientInstance.getName());
    }

    @Test
    public void changingIngredientName() {
        Ingredient ingredientInstance = new Ingredient("jabłko", null, mock(Quantity.class));
        ingredientInstance.setName("pomarańcza");
        assertEquals("pomarańcza", ingredientInstance.getName());
    }
}
