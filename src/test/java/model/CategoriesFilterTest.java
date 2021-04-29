package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoriesFilterTest {
    // Five mock-dishes, every has only one category and there are only two categories
    // It check if each of returned dishes has

    @Test
    public void filterRegularTwoCategories() {
        List<Dish> mocks = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            mocks.add(mock(Dish.class));
        DishCategory category1 = mock(DishCategory.class);
        DishCategory category2 = mock(DishCategory.class);
        when(mocks.get(0).getCategories()).thenReturn(Collections.singletonList(category1));
        when(mocks.get(1).getCategories()).thenReturn(Collections.singletonList(category2));
        when(mocks.get(2).getCategories()).thenReturn(Collections.singletonList(category2));
        when(mocks.get(3).getCategories()).thenReturn(Collections.singletonList(category1));
        when(mocks.get(4).getCategories()).thenReturn(Collections.singletonList(category2));

        Filter filter = new CategoriesFilter(List.of(category1));
        List<Dish> filtered = filter.filter(mocks);
        assertTrue(filtered.contains(mocks.get(0))
                && !filtered.contains(mocks.get(1))
                && !filtered.contains(mocks.get(2))
                && filtered.contains(mocks.get(3))
                && !filtered.contains(mocks.get(4)));
    }

    @Test
    public void throwExceptionWhenNoCategoriesToFilter() {
        assertThrows(IllegalArgumentException.class, () -> new CategoriesFilter(new ArrayList<>()));
    }

    @Test
    public void throwExceptionWhenEmptyDishList() {
        List<DishCategory> categories = List.of(mock(DishCategory.class));
        Filter filter = new CategoriesFilter(categories);
        assertThrows(IllegalArgumentException.class, () -> filter.filter(new ArrayList<>()));
    }
}