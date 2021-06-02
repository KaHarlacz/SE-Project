package model.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishTest {
    private Dish dish;
    private Ingredient[] ingredients;
    private Set<Ingredient> ingredientsSet;

    @BeforeEach
    public void setUp() {
        ingredients = new Ingredient[]{mock(Ingredient.class), mock(Ingredient.class)};
        ingredientsSet = Set.of(ingredients[0], ingredients[1]);
        dish = new Dish("Zupa ogórkowa", "Przypal wodę", "Jakiś tam przepis", ingredientsSet, null, null, null, 1);
    }

    @Test
    public void passEmptyStringNameConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Dish("", "Coś tam zrób", "ABC", ingredientsSet, null, null, null, 1)
        );
    }

    @Test
    public void passEmptyIngredientsMapConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Dish("Zupa z gwoździa", "ABC", "ABC", new HashSet<>(), null, null, null, 1)
        );
    }

    @Test
    public void addNegativeIngredientQuantity() {
        var ingredient = mock(Ingredient.class);
        var quantity = mock(Quantity.class);
        when(ingredient.getQuantity()).thenReturn(quantity);
        when(quantity.getValue()).thenReturn(-1.);
        assertFalse(dish.addIngredient(ingredient));
    }

    @Test
    public void addZeroIngredientQuantity() {
        var ingredient = mock(Ingredient.class);
        var quantity = mock(Quantity.class);
        when(ingredient.getQuantity()).thenReturn(quantity);
        when(quantity.getValue()).thenReturn(0.);
        assertFalse(dish.addIngredient(ingredient));
    }

    @Test
    public void deleteExistingIngredient() {
        var testIngredient = ingredients[0];
        dish.deleteIngredient(testIngredient);
        assertFalse(dish.getIngredients().contains(testIngredient));
    }

    @Test
    public void deleteNotExistingIngredient() {
        Ingredient testIngredient = mock(Ingredient.class);
        assertFalse(dish.deleteIngredient(testIngredient));
    }

    @Test
    public void tryAddAlreadyExistingIngredient() {
        // This ingredient is already added
        assertFalse(dish.addIngredient(ingredients[1]));
    }

    @Test
    public void setDishAsFavourite() {
        dish.setFavourite(true);
        assertTrue(dish.isFavourite());
    }
}