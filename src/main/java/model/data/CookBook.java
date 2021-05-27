package model.data;

import model.filter.Filter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CookBook implements Serializable {
    private final static long serialVersionUID = 1L;

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

    public Set<Dish> filterDishesUsing(List<Filter> filterStrategies) {
        var result = dishes;

        for (var f : filterStrategies) {
            if (f != null)
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

    public void deleteDish(Dish dish) {
        dishes.remove(dish);
    }

    private Set<Ingredient> extractIngredients(Set<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getIngredients)
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
