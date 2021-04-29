package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FavouriteFilterTest {
    private Filter filter;
    private List<Dish> mocks;

    @BeforeEach
    public void setUp() {
        filter = new FavouriteFilter();
        mocks = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            mocks.add(mock(Dish.class));
    }

    @Test
    public void returnEmptyListIfNoneIsFavourite() {
        for(int i = 0; i < 4; i++)
            when(mocks.get(i).isFavourite()).thenReturn(false);
        assertEquals(0, filter.filter(mocks).size());
    }

    @Test
    public void regularListFilter() {
        when(mocks.get(0).isFavourite()).thenReturn(true);
        when(mocks.get(1).isFavourite()).thenReturn(false);
        when(mocks.get(2).isFavourite()).thenReturn(false);
        when(mocks.get(3).isFavourite()).thenReturn(true);
        List<Dish> filtered = filter.filter(mocks);
        assertTrue(
                filtered.contains(mocks.get(0))
                && !filtered.contains(mocks.get(1))
                && !filtered.contains(mocks.get(2))
                && filtered.contains(mocks.get(3))
        );
    }

    @Test
    public void throwExceptionWhenEmptyListPassed() {
        mocks.clear();
        assertThrows(IllegalArgumentException.class, () -> filter.filter(mocks));
    }
}