package model.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {
    private ShoppingList list;
    private Map<Dish, Double> dishes;
    private Map<Ingredient, Double> ingredients;

    /*
    @BeforeEach
    public void setUp(){
        dishes = new HashMap<>();
        ingredients = new HashMap<>();

        dishes.put(mock(Dish.class), 2.0);
        dishes.put(mock(Dish.class), 4.0);

        list = new ShoppingList();
    }*/

    @Test
    public void addDishCheckIfAdded() {
        var dishToAdd = mock(Dish.class);
        list.addDish(dishToAdd, 2);
        assertTrue(list.getChosenDishes().containsKey(dishToAdd));
    }

    @Test
    public void addIngredientCheckIfAdded() {
        var ingredientToAdd = mock(Ingredient.class);
        list.addIngredient(ingredientToAdd, 2);
        assertTrue(list.getRequiredIngredients().containsKey(ingredientToAdd));      
    }

    @Test
    public void deleteDishCheckIfDeleted() {

    }

    

    @Test
    public void deleteIngredientCheckIfDeleted() {

    }

}