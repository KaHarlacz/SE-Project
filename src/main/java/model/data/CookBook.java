package model.data;

import files_management.Paths;
import files_management.load.SerializableObjectsLoader;
import filter.DishesFilterStrategy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CookBook implements Serializable {
    private final static long serialVersionUID = 1L;

    private static CookBook instance;

    private Set<Dish> dishes;
    private Set<Ingredient> availableIngredients;

    public static CookBook getInstance() {
        synchronized (CookBook.class) {
            if (instance == null) {
                var optional = new SerializableObjectsLoader<>(Paths.COOK_BOOK).load();
                optional.ifPresent(loaded -> instance = (CookBook) loaded);
            }
        }
        return instance;
    }

    public Set<Dish> filterDishesUsing(List<DishesFilterStrategy> filterStrategies) {
        var result = dishes;
        for (var f : filterStrategies) {
            if (f != null)
                result = f.filter(result);
        }
        return result;
    }

    public void addDish(Dish dish) {
        if (dishes.contains(dish))
            throw new IllegalArgumentException("Dish already in cook book");

        dishes = new HashSet<>(dishes);
        dishes.add(dish);
        availableIngredients.addAll(dish.getIngredients());
    }

    public void deleteDish(Dish dish) {
        dishes.remove(dish);
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = new HashSet<>(dishes);
        this.availableIngredients = extractIngredients(dishes);
    }

    public Set<Ingredient> getAvailableIngredients() {
        return availableIngredients;
    }

    private Set<Ingredient> extractIngredients(Set<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getIngredients)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookBook cookBook = (CookBook) o;
        return Objects.equals(dishes, cookBook.dishes)
                && Objects.equals(availableIngredients, cookBook.availableIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishes, availableIngredients);
    }
}
