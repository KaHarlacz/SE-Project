package splitter;

import model.data.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class EqualListsSplitterTest {
    @Test
    public void splitTwoIngredientsIntoThreeLists() {
        var ingredients = List.of(mock(Ingredient.class), mock(Ingredient.class));
        var split = new EqualListsSplitter().split(ingredients, 3);
        assertTrue(split.get(0).size() == 1 && split.get(1).size() == 1);
    }

    @Test
    public void splitIntoOneList() {
        var ingredients = List.of(mock(Ingredient.class), mock(Ingredient.class));
        var splitter = new EqualListsSplitter();
        var returned = splitter.split(ingredients, 1);
        assertEquals(ingredients, returned.get(0));
    }
}
