package model.filter;

import model.data.Dish;
import model.data.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IngredientsFilterTest {
    private final int DISH_NUMBER = 4;
    private final int INGREDIENT_NUMBER = 3;

    private Set<Dish> mocks = new HashSet<>();
    private Set<Ingredient> ingredients = new HashSet<>();

    @BeforeEach
    public void setUp() {
        for(int i = 0; i < DISH_NUMBER; i++)
            mocks.add(mock(Dish.class));

        for (int i = 0; i < INGREDIENT_NUMBER; i++)
            ingredients.add(mock(Ingredient.class));

        var mockDishes = mocks.toArray(Dish[]::new);
        var mockIngredients = ingredients.toArray(Ingredient[]::new);

        var set1 = Set.of(mockIngredients[0], mockIngredients[1]);
        var set2 = Set.of(mockIngredients[2], mockIngredients[0], mockIngredients[1]);
        var set3 = Set.of(mockIngredients[1]);

        when(mockDishes[0].getIngredients()).thenReturn(set1);
        when(mockDishes[1].getIngredients()).thenReturn(set2);
        when(mockDishes[2].getIngredients()).thenReturn(set3);
    }

    @Test
    public void mixedIngredientsCheckOutput() {
        // Ingredient maps for mock-dishes
        var mockDishes = mocks.toArray(Dish[]::new);
        var mockIngredients = ingredients.toArray(Ingredient[]::new);

        var filterIngredient = mockIngredients[2];
        var filter = new IngredientsFilter(Set.of(filterIngredient));

        var expected = Set.of(mockDishes[1]);
        var filtered = filter.filter(mocks);

        assertEquals(expected, filtered);
    }

    @Test
    public void emptyIngredientsCheckIfEmptyOutputSet() {
        var filter = new IngredientsFilter(ingredients);
        assertEquals(Set.of(), filter.filter(Set.of()));
    }

    @Test
    public void emptyIngredientsSetCheckIfEmptyOutputSet() {
        var filter = new IngredientsFilter(Set.of());
        assertEquals(Set.of(), filter.filter(mocks));
    }
}