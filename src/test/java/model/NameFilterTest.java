package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NameFilterTest {
    private List<Dish> mocks;

    @BeforeEach
    public void setUp() {
        mocks = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            mocks.add(mock(Dish.class));
    }

    @Test
    public void returnEmptyListIfNoneMatch() {
        when(mocks.get(0).getName()).thenReturn("Zupa ogórkowa");
        when(mocks.get(1).getName()).thenReturn("Pemikan");
        when(mocks.get(2).getName()).thenReturn("Szarlotka");
        when(mocks.get(3).getName()).thenReturn("Pomidorowa zupa");

        Filter filter = new NameFilter("piernik");
        List<Dish> filtered = filter.filter(mocks);
        assertTrue(filtered.isEmpty());
    }

    @Test
    public void filterRegularList() {
        when(mocks.get(0).getName()).thenReturn("Zupa ogórkowa");
        when(mocks.get(1).getName()).thenReturn("Pemikan");
        when(mocks.get(2).getName()).thenReturn("Szarlotka");
        when(mocks.get(3).getName()).thenReturn("Pomidorowa zupa");

        Filter filter = new NameFilter(" zUpA  ");
        List<Dish> filtered = filter.filter(mocks);
        assertTrue(
                filtered.contains(mocks.get(0))
                && !filtered.contains(mocks.get(1))
                && !filtered.contains(mocks.get(2))
                && filtered.contains(mocks.get(3))
        );
    }

    @Test
    public void throwExceptionWhenEmptyStringPassed() {
        assertThrows(IllegalArgumentException.class,() -> new NameFilter(""));
    }
}