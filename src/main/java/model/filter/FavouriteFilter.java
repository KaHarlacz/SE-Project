package model.filter;

import model.data.Dish;
import model.exception.NotImplementedException;

import java.util.Set;
import java.util.stream.Collectors;

public class FavouriteFilter implements Filter {
    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        return dishes.stream()
                .filter(Dish::isFavourite)
                .collect(Collectors.toSet());
    }
}
