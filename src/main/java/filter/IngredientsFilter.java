package filter;

import model.data.Dish;
import model.data.Ingredient;

import java.util.Set;
import java.util.stream.Collectors;

public class IngredientsFilter implements DishesFilterStrategy {
    Set<String> allowedIngredients;

    public IngredientsFilter(Set<String> names) {
        this.allowedIngredients = names;
    }

    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> atLeastOneAllowedIngredient(ingredientNamesOf(dish)))
                .collect(Collectors.toSet());
    }

    private Set<String> ingredientNamesOf(Dish dish) {
        return dish.getIngredients()
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toSet());
    }

    private boolean atLeastOneAllowedIngredient(Set<String> ingredients) {
        return allowedIngredients.stream()
                .anyMatch(ingredients::contains);
    }
}
