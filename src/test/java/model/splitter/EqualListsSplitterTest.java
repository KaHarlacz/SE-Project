package model.splitter;

import model.data.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class EqualListsSplitterTest {
    @Test
    public void splitTwoIngredientsIntoThreeLists() {
        var ingredients = List.of(mock(Ingredient.class), mock(Ingredient.class));
        var splitter = new EqualListsSplitter();
        assertThrows(IllegalArgumentException.class, () -> splitter.split(ingredients, 3));
    }

    @Test
    public void splitIntoOneList() {
        var ingredients = List.of(mock(Ingredient.class), mock(Ingredient.class));
        var splitter = new EqualListsSplitter();
        var returned = splitter.split(ingredients, 1);
        assertEquals(ingredients, returned.get(0));
    }
}
