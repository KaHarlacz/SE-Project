package filter;

import model.data.Dish;

import java.util.Set;

public interface DishesFilterStrategy {
    Set<Dish> filter(Set<Dish> dishes);
}
