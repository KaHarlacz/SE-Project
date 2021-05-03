package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CookBookTest {
    private CookBook book;
    private List<Dish> dishes;

    @BeforeEach
    public void setUp() {
        // Creation of list of three mock-dishes consisting of one mock-ingredient each
        dishes = List.of(mock(Dish.class), mock(Dish.class), mock(Dish.class));
        when(dishes.get(0).getIngredients()).thenReturn((Map.of(mock(Ingredient.class), 20.1)));
        when(dishes.get(1).getIngredients()).thenReturn((Map.of(mock(Ingredient.class), 4.7)));
        when(dishes.get(2).getIngredients()).thenReturn((Map.of(mock(Ingredient.class), 2.5)));

        // Adding dishes to CookBook attribute
        book = new CookBook(dishes);
    }

    // CookBook filter dishes using Filter object so it's enough to check if
    // filterDishes() returns filtered list
    @Test
    public void filterDishesReturnFavourite() {
        List<Dish> mocks = List.of(mock(Dish.class), mock(Dish.class));
        Filter stub = dishes -> mocks;
        assertEquals(mocks, book.filterDishes(stub));
    }

    @Test
    public void addDishCheckIfAdded() {
        // There are three mocks in CookBook already so new one should be put at third index
        Dish newDish = mock(Dish.class);
        book.addDish(newDish);
        assertEquals(newDish, book.getDishes().get(3));
    }

    @Test
    public void tryAddDishAlreadyInCookBook() {
        Dish duplicate = dishes.get(1);
        assertFalse(book.addDish(duplicate));
    }

    // CookBook when only list of dishes passed to constructor should
    // on its own create list of available ingredients
    @Test
    public void availableIngredientsCheck() {
        List<Ingredient> ingredients = dishes.stream()
                .map(dish -> dish.getIngredients().keySet())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertEquals(ingredients, book.getAvailableIngredients());
    }

    @Test
    public void deleteDishCheckIfDeleted() {
        Dish dishToDelete = dishes.get(0);
        dishes.remove(0);
        book.deleteDish(dishToDelete);
        assertEquals(dishes, book.getDishes());
    }
}