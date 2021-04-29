package model;

import model.exception.NotImplementedException;

import java.util.List;

public class CookBook {
    private List<Dish> dishes;
    private List<Ingredient> availableIngredients;

    public CookBook(List<Dish> dishes, List<Ingredient> availableIngredients) {
        throw new NotImplementedException();
    }

    public CookBook(List<Dish> dishes) {
        throw new NotImplementedException();
    }

    public List<Dish> filterDishes(Filter filter) {
        throw new NotImplementedException();
    }

    public boolean addDish(Dish dish) {
        throw new NotImplementedException();
    }

    public boolean deleteDish(Dish dish) {
        throw new NotImplementedException();
    }

    public List<Dish> getDishes() {
        throw new NotImplementedException();
    }

    public List<Ingredient> getAvailableIngredients() {
        throw new NotImplementedException();
    }
}
