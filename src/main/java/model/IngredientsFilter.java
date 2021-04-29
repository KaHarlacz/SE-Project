package model;

import model.exception.NotImplementedException;

import java.util.List;

public class IngredientsFilter implements Filter {
    List<Ingredient> ingredients;

    public IngredientsFilter(List<Ingredient> ingredients) {
        throw new NotImplementedException();
    }

    @Override
    public List<Dish> filter(List<Dish> dishes) {
        throw new NotImplementedException();
    }
}
