package model.filter;

import model.data.Dish;
import model.data.Ingredient;

import java.util.Set;
import java.util.stream.Collectors;

public class IngredientsNameFilter implements Filter {
    Set<String> allowedIngredients;

    public IngredientsNameFilter(Set<String> names) {
        this.allowedIngredients = names;
    }

    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> containsAtLeastOneAllowedIngredient(dish.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toSet())))
                .collect(Collectors.toSet());
    }

    private boolean containsAtLeastOneAllowedIngredient(Set<String> ingredients) {
        for (var allowed : allowedIngredients) {
            if(ingredients.contains(allowed))
                return true;
        }

        return false;
    }
}
