package model.data;

import model.enumerative.DishCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
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
        dish = new Dish("", null, ingredientQuantityMap, null, null, null, 1);
        assertEquals("No name specified", dish.getName());
    }

    @Test
    public void passEmptyIngredientsMapConstructor() {
        dish = new Dish("Zupa z gwoździa", null, new HashMap<>(), null, null, null, 1);
        assertEquals(true, dish.getIngredientsMap().isEmpty());
    }

//    @Test
//    public void passNullCategoriesCheckIfSetOther() {
//        dish = new Dish("Zupa z gwoździa", null, ingredientQuantityMap, null, null, null, 1);
//        assertEquals(DishCategory.OTHER, dish.getCategories().get(0));
//    }
//
//    @Test
//    public void passEmptyCategoriesCheckIfSetOther() {
//        dish = new Dish("Zupa z gwoździa", null, ingredientQuantityMap, new HashSet<>(), null, null, 1);
//        assertEquals(DishCategory.OTHER, dish.getCategories());
//    }

    @Test
    public void addNegativeIngredientQuantity() {
        assertFalse(dish.addIngredient(mock(Ingredient.class), -1.0));
    }

    @Test
    public void addZeroIngredientQuantity() {
        assertFalse(dish.addIngredient(mock(Ingredient.class), 0));
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
        when(ingredients[1].compareTo(any())).thenReturn(0);
        assertFalse(dish.addIngredient(ingredients[1], 5.0));
    }

    @Test
    public void setDishAsFavourite() {
        dish.setFavourite(true);
        assertTrue(dish.isFavourite());
    }
}