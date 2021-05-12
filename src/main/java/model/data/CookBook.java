package model.data;

import model.filter.Filter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CookBook {
    private Set<Dish> dishes;
    private Set<Ingredient> availableIngredients;

    public CookBook(Set<Dish> dishes, Set<Ingredient> availableIngredients) {
        this.dishes = dishes;
        this.availableIngredients = availableIngredients;
    }

    public CookBook(Set<Dish> dishes) {
        this.dishes = dishes;
        availableIngredients = extractIngredients(dishes);
    }

    public Set<Dish> filterDishesUsing(List<Filter> filters) {
        var result = dishes;

        for (var f : filters) {
            result = f.filter(result);
        }

        return result;
    }

    public boolean addDish(Dish dish) {
        if (dishes.contains(dish))
            return false;

        dishes.add(dish);

        return true;
    }

    public boolean deleteDish(Dish dish) {
        if (!dishes.contains(dish))
            return false;

        dishes.remove(dish);

        return true;
    }

    private Set<Ingredient> extractIngredients(Set<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getIngredientsSet)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public Set<Ingredient> getAvailableIngredients() {
        return availableIngredients;
    }
}