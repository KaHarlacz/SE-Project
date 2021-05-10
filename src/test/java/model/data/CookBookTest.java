package model.data;

import model.filter.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CookBookTest {
    private CookBook book;
    private Set<Dish> dishes;

    @BeforeEach
    public void setUp() {
        // Creation of list of three mock-dishes consisting of one mock-ingredient each
        var random = new Random();

        dishes = Set.of(mock(Dish.class), mock(Dish.class), mock(Dish.class));

        for (Dish dish : dishes) {
            // Nonzero quantity
            var randomQuantity = (random.nextDouble() * 10) + 1;
            var mockIngredient = mock(Ingredient.class);
            var mockMap = Map.of(mockIngredient, randomQuantity);

            when(dish.getIngredientsMap()).thenReturn(mockMap);
        }

        // Adding dishes to CookBook
        book = new CookBook(dishes);
    }

    // CookBook filter dishes using Filter object so it's enough to check if
    // filterDishes() returns filtered list
    @Test
    public void filterDishesReturnFavourite() {
        var mocks = Set.of(mock(Dish.class), mock(Dish.class));
        Filter stub = dishes -> mocks;
        assertEquals(mocks, book.filterDishes(List.of(stub)));
    }

    @Test
    public void addDishCheckIfAdded() {
        var newDish = mock(Dish.class);
        book.addDish(newDish);
        assertTrue(book.getDishes().contains(newDish));
    }

    @Test
    public void tryAddDishAlreadyInCookBook() {
        // Method getRandomDish returns one of dish already in "dishes"
        // so return value should be false
        var duplicate = getRandomDish(dishes);
        assertFalse(book.addDish(duplicate));
    }

    // CookBook when only list of dishes passed to constructor should
    // on its own create list of available ingredients
    @Test
    public void availableIngredientsCheck() {
        Set<Ingredient> ingredients = dishes.stream()
                .map(Dish::getIngredientsMap)
                .map(Map::keySet)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        assertEquals(ingredients, book.getAvailableIngredients());
    }

    @Test
    public void deleteDishCheckIfDeleted() {
        var randomDish = getRandomDish(dishes);
        book.deleteDish(randomDish);
        assertEquals(dishes, book.getDishes());
    }

    private Dish getRandomDish(Set<Dish> dishes) {
        var random = new Random();
        var randomIndex = random.nextInt(dishes.size());
        var dishArray = dishes.toArray(Dish[]::new);

        return dishArray[randomIndex];
    }
}