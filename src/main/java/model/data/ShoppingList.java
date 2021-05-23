package model.data;

import java.io.Serializable;
import java.util.*;

public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Dish, Integer> selectedDishes = new HashMap<>();
    private Map<Ingredient, Quantity> ingredients = new HashMap<>();

    public ShoppingList() { }

    public Map<Dish, Integer> getSelectedDishes() {
        return selectedDishes;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients.keySet();
    }

    public void addIngredientsFrom(Dish dish) {
        addDishToSelected(dish);
        dish.getIngredients().forEach(this::addIngredient);
    }

    public void deleteIngredientsFrom(Dish dish) {
        removeDishFromSelected(dish);
        dish.getIngredients().forEach(this::deleteIngredient);
    }

    private void removeDishFromSelected(Dish dish) {
        if(selectedDishes.containsKey(dish)) {
            var currentChosen = selectedDishes.get(dish);

            if(currentChosen == 1)
                selectedDishes.remove(dish);
            else
                selectedDishes.put(dish, currentChosen - 1);
        }
    }

    private void addDishToSelected(Dish dish) {
        if(selectedDishes.containsKey(dish)) {
            var scaleFactor = selectedDishes.get(dish);
            selectedDishes.put(dish, scaleFactor + 1);
        } else {
            selectedDishes.put(dish, 1);
        }
    }

    public void addIngredient(Ingredient ingredient) {
        if(ingredients.containsKey(ingredient)) {
            var currentQuantity = ingredients.get(ingredient);
            currentQuantity.addValue(ingredient.getQuantity());
        } else {
            ingredients.put(ingredient, ingredient.getQuantity());
        }
    }

    public void deleteIngredient(Ingredient ingredient) {
        if(ingredients.containsKey(ingredient)) {
            var currentQuantity = ingredients.get(ingredient);

            if(currentQuantity.getValue() <= ingredient.getQuantity().getValue())
                ingredients.remove(ingredient);
            else
                currentQuantity.subtractValue(ingredient.getQuantity().getValue());
        }
    }
}
