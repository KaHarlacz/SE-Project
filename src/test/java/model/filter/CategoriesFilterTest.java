package model.filter;

import model.data.Dish;
import model.enumerative.DishCategory;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoriesFilterTest {
    // Five mock-dishes, every has only one category and there are only two categories
    // It check if each of returned dishes has
    @Test
    public void filterOneCategory() {
        final int DISHES_NUMBER = 5;

        List<Dish> mocks = new ArrayList<>();
        List<DishCategory> categories = new ArrayList<>();

        // Cannot mock enum class
        categories.add(DishCategory.TEA);
        categories.add(DishCategory.OTHER);

        var filterCategory = categories.get(0);
        var otherCategory = categories.get(1);
        var expectedResultSet = new HashSet<Dish>();

        for (int i = 0; i < DISHES_NUMBER / 2; i++) {
            var mockDish = mock(Dish.class);
            when(mockDish.getCategories()).thenReturn(Set.of(filterCategory));
            expectedResultSet.add(mockDish);
            mocks.add(mockDish);
        }

        for (int i = DISHES_NUMBER / 2 + 1; i < DISHES_NUMBER; i++) {
            var mockDish = mock(Dish.class);
            when(mockDish.getCategories()).thenReturn(Set.of(otherCategory));
            mocks.add(mockDish);
        }

        var filter = new CategoriesFilter(Set.of(filterCategory));

        var filtered = filter.filter(new HashSet<>(mocks));
        assertEquals(filtered, expectedResultSet);
    }

    @Test
    public void passEmptySetOfCategoriesCheckIfOutputIsEmptySet() {
        var filter = new CategoriesFilter(Set.of());
        var dishes = List.of(mock(Dish.class), mock(Dish.class));

        when(dishes.get(0).getCategories()).thenReturn(Set.of(DishCategory.OTHER));
        when(dishes.get(1).getCategories()).thenReturn(Set.of(DishCategory.OTHER));

        assertEquals(Set.of(), filter.filter(Set.of(dishes.toArray(Dish[]::new))));
    }

    @Test
    public void passEmptyDishSetCheckIfOutputIsEmptySet() {
        var categories = Set.of(DishCategory.OTHER);
        Filter filter = new CategoriesFilter(categories);
        assertEquals(Set.of(), filter.filter(Set.of(mock(Dish.class))));
    }
}