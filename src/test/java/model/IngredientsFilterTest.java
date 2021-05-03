package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IngredientsFilterTest {
    private List<Dish> mocks;
    private Ingredient ingredient1 = mock(Ingredient.class);
    private Ingredient ingredient2 = mock(Ingredient.class);
    private Ingredient ingredient3 = mock(Ingredient.class);

    @BeforeEach
    public void setUp() {
        mocks = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            mocks.add(mock(Dish.class));
        ingredient1 = mock(Ingredient.class);
        ingredient2 = mock(Ingredient.class);
        ingredient3 = mock(Ingredient.class);
    }

    @Test
    public void regularListFilter() {
        // Ingredient maps for mock-dishes
        Map<Ingredient, Double> map1 = Map.of(
                ingredient1, 10.,
                ingredient3, 0.5
        );

        Map<Ingredient, Double> map2 = Map.of(
                ingredient2, 7.3,
                ingredient3, 0.2,
                ingredient1, 0.1
        );

        Map<Ingredient, Double> map3 = Map.of(
                ingredient1, 0.8
        );

        when(mocks.get(0).getIngredients()).thenReturn(map1);
        when(mocks.get(1).getIngredients()).thenReturn(map2);
        when(mocks.get(2).getIngredients()).thenReturn(map3);

        Filter filter = new IngredientsFilter(List.of(ingredient3));
        List<Dish> filtered = filter.filter(mocks);

        assertTrue(
                filtered.contains(mocks.get(0))
                        && filtered.contains(mocks.get(1))
                        && !filtered.contains(mocks.get(2))
        );
    }

    @Test
    public void throwExceptionWhenEmptyListPassed() {
        mocks.clear();
        Filter filter = new IngredientsFilter(List.of(ingredient1, ingredient2));
        assertThrows(IllegalArgumentException.class, () -> filter.filter(mocks));
    }
}