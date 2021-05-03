package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishTest {
    private Dish dish;
    private Ingredient[] ingredients;
    private Map<Ingredient, Double> ingredientQuantityMap;

    @BeforeEach
    public void setUp() {
        ingredients = new Ingredient[]{mock(Ingredient.class), mock(Ingredient.class)};
        ingredientQuantityMap = Map.of(
                ingredients[0], 10.0,
                ingredients[1], 0.1
        );
        dish = new Dish("Zupa ogórkowa", "Przypal wodę", ingredientQuantityMap, null, null, null, 1);
    }

    @Test
    public void passEmptyStringNameConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Dish("", null, ingredientQuantityMap, null, null, null, 1)
        );
    }

    @Test
    public void passEmptyIngredientsMapConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Dish("Zupa z gwoździa", null, new HashMap<>(), null, null, null, 1)
        );
    }

    @Test
    public void passNullCategoriesCheckIfSetOther() {
        dish = new Dish("Zupa z gwoździa", null, ingredientQuantityMap, null, null, null, 1);
        assertEquals(DishCategory.OTHER, dish.getCategories().get(0));
    }

    @Test
    public void passEmptyCategoriesCheckIfSetOther() {
        dish = new Dish("Zupa z gwoździa", null, ingredientQuantityMap, new LinkedList<>(), null, null, 1);
        assertEquals(DishCategory.OTHER, dish.getCategories().get(0));
    }

    @Test
    public void addNegativeIngredientQuantity() {
        assertThrows(IllegalArgumentException.class, () -> dish.addIngredient(mock(Ingredient.class), -1.0));
    }

    @Test
    public void addZeroIngredientQuantity() {
        assertThrows(IllegalArgumentException.class, () -> dish.addIngredient(mock(Ingredient.class), 0));
    }

    // FIXME: There is some problem with usage of when method
    @Test
    public void deleteNotExistingIngredient() {
        when(ingredients[0].equals(any())).thenReturn(false);
        when(ingredients[1].equals(any())).thenReturn(false);
        assertFalse(dish.deleteIngredient(mock(Ingredient.class)));
    }

    // FIXME: There is some problem with usage of when method
    @Test
    public void tryAddAlreadyExistingIngredient() {
        Ingredient newIngredient = mock(Ingredient.class);
        when(ingredients[1].equals(newIngredient)).thenReturn(true);
        assertFalse(dish.addIngredient(newIngredient, 5.0));
    }

    @Test
    public void setDishAsFavourite() {
        dish.setFavourite(true);
        assertTrue(dish.isFavourite());
    }
}