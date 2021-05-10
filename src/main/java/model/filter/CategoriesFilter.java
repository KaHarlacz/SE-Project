package model.filter;

import model.data.Dish;
import model.enumerative.DishCategory;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoriesFilter implements Filter {
    private Set<DishCategory> allowedCategories;

    public CategoriesFilter(Set<DishCategory> categories) {
        this.allowedCategories = categories;
    }

    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> containsAtLeastOneAllowedCategory(dish.getCategories()))
                .collect(Collectors.toSet());
    }

    private boolean containsAtLeastOneAllowedCategory(Set<DishCategory> categories) {
        for (var allowed : allowedCategories) {
            if(categories.contains(allowed))
                return true;
        }

        return false;
    }
}
