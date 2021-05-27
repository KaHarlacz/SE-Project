package model.data;

import model.splitter.SplitStrategy;

import java.io.Serializable;
import java.util.*;

public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Dish, Integer> selectedDishes = new HashMap<>();
    private Map<Ingredient, Quantity> ingredients = new HashMap<>();

    private static ShoppingList instance;

    private ShoppingList() {}

    public static ShoppingList getInstance() {
        if(instance == null)
            instance = new ShoppingList();

        return instance;
    }

    public Map<Dish, Integer> getSelectedDishes() {
        return selectedDishes;
    }

    public Set<Ingredient> getIngredients() {
        return getIngredientsWithSetQuantities();
    }

    public void addIngredientsFrom(Dish dish) {
        addToSelected(dish);
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

    private void addToSelected(Dish dish) {
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
            System.out.println("Aktualna ilość: " + currentQuantity);
            currentQuantity.addValue(ingredient.getQuantity());
        } else {
            ingredients.put(ingredient, new Quantity(ingredient.getQuantity().getValue(), ingredient.getQuantity().getUnit()));
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

    private Set<Ingredient> getIngredientsWithSetQuantities() {
        var copyIngredients= new HashSet<Ingredient>();
        ingredients.keySet().forEach(i -> copyIngredients.add(Ingredient.copyOf(i)));
        copyIngredients.forEach(i -> i.setQuantity(ingredients.get(i)));
        return copyIngredients;
    }

    public List<List<Ingredient>> splitIngredientListUsing(SplitStrategy splitter, int count) {
        return splitter.split(List.copyOf(getIngredientsWithSetQuantities()), count);
    }
}
