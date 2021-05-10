package model.filter;

import model.data.Dish;

import java.util.Collection;
import java.util.Set;

public interface Filter {
    Set<Dish> filter(Set<Dish> dishes);
}
