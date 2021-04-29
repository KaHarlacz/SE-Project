package model;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CookBookTest {
    private CookBook book;

    @BeforeEach
    public void setUp() {
        List<Dish> dishes = List.of(mock(Dish.class), mock(Dish.class), mock(Dish.class));
        when(dishes.get(0).getIngredients()).thenReturn(Map.<Ingredient, Double>of(mock(Ingredient.class), 10.));
        book = new CookBook(dishes);
    }
}