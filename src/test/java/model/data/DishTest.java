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

    // TODO: Rewrite these tests
//    @BeforeEach
//    public void setUp() {
//        ingredients = new Ingredient[]{mock(Ingredient.class), mock(Ingredient.class)};
//        ingredientsSet = Set.of(ingredients[0], ingredients[1]);
//        dish = new Dish("Zupa ogórkowa", "Przypal wodę", ingredientsSet, null, null, null, 1);
//    }
//
//    @Test
//    public void passEmptyStringNameConstructor() {
//        dish = new Dish("", null, ingredientsSet, null, null, null, 1);
//        assertEquals("No name specified", dish.getName());
//    }
//
//    @Test
//    public void passEmptyIngredientsMapConstructor() {
//        dish = new Dish("Zupa z gwoździa", null, new HashSet<>(), null, null, null, 1);
//        assertTrue(dish.getIngredients().isEmpty());
//    }
    
    @BeforeEach
    public void setUp() {
        ingredients = new Ingredient[]{mock(Ingredient.class), mock(Ingredient.class)};
        ingredientsSet = Set.of(ingredients[0], ingredients[1]);
        dish = new Dish("Zupa ogórkowa", "Przypal wodę", "", ingredientsSet, null, null, null, 1);
    }

    @Test
    public void passEmptyStringNameConstructor() {
        dish = new Dish("", null, "", ingredientsSet, null, null, null, 1);
        assertEquals("No name specified", dish.getName());
    }

    @Test
    public void passEmptyIngredientsMapConstructor() {
        dish = new Dish("Zupa z gwoździa", null, "", new HashSet<>(), null, null, null, 1);
        assertTrue(dish.getIngredients().isEmpty());
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
        when(ingredients[1].compareTo(any())).thenReturn(0);
        assertFalse(dish.addIngredient(ingredients[1]));
    }

    @Test
    public void setDishAsFavourite() {
        dish.setFavourite(true);
        assertTrue(dish.isFavourite());
    }
}