package model.data;

import model.filter.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CookBookTest {
    private CookBook book;
    private Set<Dish> dishes;

    @BeforeEach
    public void setUp() {
        // Creation of list of three mock-dishes consisting of one mock-ingredient each
        dishes = new HashSet<>();
        dishes.addAll(Set.of(mock(Dish.class), mock(Dish.class), mock(Dish.class)));

        // Adding dishes to CookBook
        book = new CookBook(dishes);
    }

    @Test
    public void filterFavouriteDishes() {
        // Adding favourite dishes to cookBook
        var favouriteDishes = List.of(mock(Dish.class), mock(Dish.class));
        book.addDish(favouriteDishes.get(0));
        book.addDish(favouriteDishes.get(1));

        // Filter stub
        Filter filter = dishes -> Set.copyOf(favouriteDishes);
        var filters = List.of(filter);

        var expected = Set.copyOf(favouriteDishes);
        var actual = book.filterDishesUsing(filters);

        assertEquals(expected, actual);
    }

    @Test
    public void filterDishesUsingTwoFilters() {
        // Adding favourite dishes to cookBook
        var favouriteDishes = new ArrayList<>(List.of(mock(Dish.class), mock(Dish.class)));
        book.addDish(favouriteDishes.get(0));
        book.addDish(favouriteDishes.get(1));

        // Using same dish - it will be only expected one
        var dishesOfCategory = List.of(favouriteDishes.get(0));
        book.addDish(dishesOfCategory.get(0));

        // Filter stubs
        Filter favouriteFilter = dishes -> Set.copyOf(favouriteDishes);
        Filter categoryFilter = dishes -> Set.copyOf(dishesOfCategory);
        var filters = List.of(favouriteFilter, categoryFilter);

        // Intersection of two sets is expected result
        favouriteDishes.retainAll(dishesOfCategory);

        var expected = Set.copyOf(favouriteDishes);
        var actual = book.filterDishesUsing(filters);

        assertEquals(expected, actual);
    }

    // Intended behaviour is when filter reference is null it has no effect on
    // filtered set
    @Test
    public void passNullFiltersCheckIfCorrectOutput() {
        // Adding favourite dishes to cookBook
        var favouriteDishes = new ArrayList<>(List.of(mock(Dish.class), mock(Dish.class)));
        book.addDish(favouriteDishes.get(0));
        book.addDish(favouriteDishes.get(1));

        // Using same dish - it will be only expected one
        var dishesOfCategory = List.of(favouriteDishes.get(0));
        book.addDish(dishesOfCategory.get(0));

        // Filter stubs
        Filter favouriteFilter = dishes -> Set.copyOf(favouriteDishes);
        Filter categoryFilter = dishes -> Set.copyOf(dishesOfCategory);
        Filter nullFilter = null;

        // Adding also nulls
        var filters = new ArrayList<Filter>();
        filters.add(favouriteFilter);
        filters.add(nullFilter);
        filters.add(categoryFilter);

        // Intersection of two sets is expected result
        favouriteDishes.retainAll(dishesOfCategory);

        var expected = Set.copyOf(favouriteDishes);
        var actual = book.filterDishesUsing(filters);

        assertEquals(expected, actual);
    }

    @Test
    public void addDishCheckIfAdded() {
        var dishToAdd = mock(Dish.class);
        book.addDish(dishToAdd);
        assertTrue(book.getDishes().contains(dishToAdd));
    }

    @Test
    public void tryAddDishAlreadyInCookBook() {
        // Method getRandomDish returns one of dish already in "dishes"
        // so return value should be false
        var duplicate = List.copyOf(dishes).get(0);
        assertFalse(book.addDish(duplicate));
    }

    // CookBook when only list of dishes passed to constructor should
    // on its own create list of available ingredients
    @Test
    public void availableIngredientsCheck() {
        var expectedIngredients = dishes.stream()
                .map(Dish::getIngredients)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        assertEquals(expectedIngredients, book.getAvailableIngredients());
    }

    @Test
    public void deleteDishCheckIfDeleted() {
        var dishToDelete = List.copyOf(dishes).get(0);
        book.deleteDish(dishToDelete);
        assertEquals(dishes, book.getDishes());
    }
}