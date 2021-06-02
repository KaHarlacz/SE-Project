package filter;

import model.data.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FavouriteFilterTest {
    private final int DISH_NUMBER = 4;

    private DishesFilterStrategy filter;
    private Set<Dish> mocks;

    @BeforeEach
    public void setUp() {
        filter = new FavouriteFilter(true);
        mocks = new HashSet<>();

        for(int i = 0; i < DISH_NUMBER; i++)
            mocks.add(mock(Dish.class));
    }

    @Test
    public void noneIsFavouriteCheckEmptyOutputSet() {
        for (var mock : mocks)
            when(mock.isFavourite()).thenReturn(false);

        assertEquals(0, filter.filter(mocks).size());
    }

    @Test
    public void mixedFilterFavourite() {
        var mockList = List.of(mocks.toArray(Dish[]::new));

        when(mockList.get(0).isFavourite()).thenReturn(false);
        when(mockList.get(1).isFavourite()).thenReturn(true);
        when(mockList.get(2).isFavourite()).thenReturn(false);
        when(mockList.get(3).isFavourite()).thenReturn(true);

        var expected = Set.of(mockList.get(1), mockList.get(3));
        var filtered = filter.filter(mocks);

        assertEquals(expected, filtered);
    }

    @Test
    public void mixedFilterNotFavourite() {
        var mockList = List.of(mocks.toArray(Dish[]::new));

        when(mockList.get(0).isFavourite()).thenReturn(false);
        when(mockList.get(1).isFavourite()).thenReturn(true);
        when(mockList.get(2).isFavourite()).thenReturn(false);
        when(mockList.get(3).isFavourite()).thenReturn(true);

        var expected = Set.of(mockList.get(1), mockList.get(3));
        var filtered = filter.filter(mocks);

        assertEquals(expected, filtered);
    }

    @Test
    public void passEmptyDishSetCheckEmptyOutputSet() {
        mocks.clear();
        assertEquals(Set.of(), filter.filter(mocks));
    }
}