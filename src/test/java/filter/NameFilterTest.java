package filter;

import model.data.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NameFilterTest {
    private final int DISH_NUMBER = 4;
    private List<Dish> mocks;

    @BeforeEach
    public void setUp() {
        mocks = new ArrayList<>();
        for (int i = 0; i < DISH_NUMBER; i++)
            mocks.add(mock(Dish.class));

        when(mocks.get(0).getName()).thenReturn("Zupa ogórkowa");
        when(mocks.get(1).getName()).thenReturn("Pemikan");
        when(mocks.get(2).getName()).thenReturn("Szarlotka");
        when(mocks.get(3).getName()).thenReturn("Pomidorowa zupa");
    }

    @Test
    public void filterAFewDishes() {
        var filter = new NameFilter("zupa");
        var filtered = filter.filter(Set.copyOf(mocks));
        var expected = Set.of(mocks.get(0), mocks.get(3));

        assertEquals(expected, filtered);
    }

    @Test
    public void filterMixedCaseName() {
        var filter = new NameFilter(" zUpA  ");
        var filtered = filter.filter(Set.copyOf(mocks));
        var expected = Set.of(mocks.get(0), mocks.get(3));

        assertEquals(expected, filtered);
    }

    @Test
    public void filterWarpedName() {
        var filter = new NameFilter(" szArlotkaaa  ");
        var filtered = filter.filter(Set.copyOf(mocks));
        var expected = Set.of();

        assertEquals(expected, filtered);
    }

    @Test
    public void filterNotExistingName() {
        var filter = new NameFilter("pieczarkowa");
        var filtered = filter.filter(Set.copyOf(mocks));
        var expected = Set.of();

        assertEquals(expected, filtered);
    }

    @Test
    public void filterEmptyName() {
        var filter = new NameFilter("");
        var filtered = filter.filter(Set.copyOf(mocks));
        var expected = Set.copyOf(mocks);

        assertEquals(expected, filtered);
    }
}