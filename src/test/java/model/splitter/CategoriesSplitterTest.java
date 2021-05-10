package model.splitter;

import model.data.Ingredient;
import model.enumerative.IngredientCategory;
import model.splitter.CategoriesSplitter;
import model.splitter.Splitter;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoriesSplitterTest {
    private List<Ingredient> ingredients;
    private IngredientCategory category1;
    private IngredientCategory category2;


    @BeforeEach
    public void setUp() {
        for(int i = 0; i < 4; i++)
            ingredients.add(mock(Ingredient.class));

        category1 = mock(IngredientCategory.class);
        category2 = mock(IngredientCategory.class);
    }

    @Test
    public void regularListSplit() {
        when(ingredients.get(0).getCategory()).thenReturn(category1);
        when(ingredients.get(1).getCategory()).thenReturn(category2);
        when(ingredients.get(2).getCategory()).thenReturn(category1);
        when(ingredients.get(3).getCategory()).thenReturn(category1);

        Map<Ingredient, Double> map = Map.of(
                ingredients.get(0), 10.,
                ingredients.get(1), 1.12,
                ingredients.get(2), 9.43,
                ingredients.get(3), 0.12
        );

        Splitter splitter = new CategoriesSplitter();
        var split = splitter.split(map, 2);
        var map1 = split.get(0);
        var map2 = split.get(1);
        assertTrue(
                (map1.size() == 3 && map2.size() == 1)
                || (map1.size() == 1 && map2.size() == 3)
        );
    }
}
