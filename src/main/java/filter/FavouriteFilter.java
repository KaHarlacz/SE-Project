package filter;

import model.data.Dish;

import java.util.Set;
import java.util.stream.Collectors;

public class FavouriteFilter implements DishesFilterStrategy {
    private boolean isFavourite;

    public FavouriteFilter(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> dish.isFavourite() == this.isFavourite)
                .collect(Collectors.toSet());
    }
}
