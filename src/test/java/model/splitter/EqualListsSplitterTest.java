package model.splitter;

import model.data.Ingredient;
import model.splitter.EqualListsSplitter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class EqualListsSplitterTest {
    private Map<Ingredient, Double> mapInstance;
    private Ingredient[] ingredient;
    private EqualListsSplitter instance;

    @Test
    public void splitTwoIngredientsIntoThreeLists() {
        ingredient = new Ingredient[]{mock(Ingredient.class), mock(Ingredient.class)};
        mapInstance = Map.of(ingredient[0], 10.0, ingredient[1], 0.1);
        instance = new EqualListsSplitter();
        assertThrows(IllegalArgumentException.class, () -> instance.split(mapInstance, 3));
    }

    @Test
    public void splitIntoOneList() {
        ingredient = new Ingredient[]{mock(Ingredient.class), mock(Ingredient.class)};
        mapInstance = Map.of(ingredient[0], 10.0, ingredient[1], 0.1);
        instance = new EqualListsSplitter();
        var returned = instance.split(mapInstance, 1);
        assertEquals(mapInstance, returned);
    }
}
