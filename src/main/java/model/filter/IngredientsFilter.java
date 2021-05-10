package model.filter;

import model.data.Dish;
import model.data.Ingredient;
import model.enumerative.DishCategory;
import model.exception.NotImplementedException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class IngredientsFilter implements Filter {
    Set<Ingredient> allowedIngredients;

    public IngredientsFilter(Set<Ingredient> ingredients) {
        this.allowedIngredients = ingredients;
    }

    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> containsAtLeastOneAllowedIngredient(dish.getIngredientsSet()))
                .collect(Collectors.toSet());
    }

    private boolean containsAtLeastOneAllowedIngredient(Set<Ingredient> ingredients) {
        for (var allowed : allowedIngredients) {
            if(ingredients.contains(allowed))
                return true;
        }

        return false;
    }
}
