package model.data;

import splitter.SplitStrategy;

import java.io.Serializable;
import java.util.*;

public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 1L;
    private static ShoppingList instance;
    private Map<Dish, Integer> dishCount = new HashMap<>();
    private Map<String, Ingredient> ingredientNameMap = new HashMap<>();

    private ShoppingList() {}

    public static ShoppingList getInstance() {
        synchronized (ShoppingList.class) {
            if (instance == null)
                instance = new ShoppingList();
        }
        return instance;
    }

    public Map<Dish, Integer> getSelectedDishesWithTimesSelected() {
        return dishCount;
    }

    public Set<Ingredient> getIngredients() {
        return Set.copyOf(ingredientNameMap.values());
    }

    public void addIngredientsFrom(Dish dish) {
        addToSelected(dish);
        dish.getIngredients().forEach(this::addIngredient);
    }

    public void deleteIngredientsFrom(Dish dish) {
        removeDishFromSelected(dish);
        dish.getIngredients().forEach(this::deleteIngredient);
    }

    public List<List<Ingredient>> splitIngredientListUsing(SplitStrategy splitter, int count) {
        return splitter.split(List.copyOf(ingredientNameMap.values()), count);
    }

    public void clear() {
        dishCount = new HashMap<>();
        ingredientNameMap = new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient) {
        if(ingredientNameMap.containsKey(ingredient.getName())) {
            var ingredientFromMap = ingredientNameMap.get(ingredient.getName());
            var ingredientQuantity = ingredientFromMap.getQuantity();
            ingredientQuantity.add(ingredient.getQuantity());
        } else {
            // CopyOf to avoid later changes in ingredient object stored in some dish
            ingredientNameMap.put(ingredient.getName(), Ingredient.copyOf(ingredient));
        }
    }

    public void deleteIngredient(Ingredient ingredientToDelete) {
        var ingredientName = ingredientToDelete.getName();
        if (ingredientNameMap.containsKey(ingredientName)) {
            var currentQuantity = ingredientNameMap.get(ingredientName).getQuantity();
            var quantityToDelete = ingredientToDelete.getQuantity();
            if (currentQuantity.getValue() <= quantityToDelete.getValue())
                ingredientNameMap.remove(ingredientToDelete.getName());
            else
                currentQuantity.subtract(quantityToDelete);
        }
    }

    private void removeDishFromSelected(Dish dish) {
        if (dishCount.containsKey(dish)) {
            var timesSelected = dishCount.get(dish);
            if (timesSelected == 1)
                dishCount.remove(dish);
            else
                dishCount.put(dish, timesSelected - 1);
        }
    }

    private void addToSelected(Dish dish) {
        if (dishCount.containsKey(dish)) {
            var timesSelected = dishCount.get(dish);
            dishCount.put(dish, timesSelected + 1);
        } else {
            dishCount.put(dish, 1);
        }
    }
}
