package model;

import java.util.List;

public interface Filter {
    List<Dish> filter(List<Dish> dishes);
}
