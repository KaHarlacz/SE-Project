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
        Ingredient testIngredient = mock(Ingredient.class);
        when(testIngredient.compareTo(ingredients[0])).thenReturn(1);
        when(testIngredient.compareTo(ingredients[1])).thenReturn(0);
        assertFalse(dish.deleteIngredient(testIngredient));
    }

    @Test
    public void deleteNotExistingIngredient() {
        Ingredient testIngredient = mock(Ingredient.class);
        when(testIngredient.compareTo(any())).thenReturn(1);
        assertFalse(dish.deleteIngredient(testIngredient));
    }

    @Test
    public void tryAddAlreadyExistingIngredient() {
        var quantity = mock(Quantity.class);
        when(ingredients[1].compareTo(any())).thenReturn(0);
        when(ingredients[1].getQuantity()).thenReturn(quantity);
        when(quantity.getValue()).thenReturn(1.);

        // This ingredient is already added
        assertFalse(dish.addIngredient(ingredients[1]));
    }

    @Test
    public void setDishAsFavourite() {
        dish.setFavourite(true);
        assertTrue(dish.isFavourite());
    }
}