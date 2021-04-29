package model;

import model.exception.NotImplementedException;

import java.util.List;

public class CategoriesFilter implements Filter {
    private DishCategory allowedCategories;

    public CategoriesFilter(List<DishCategory> categories) {
        throw new NotImplementedException();
    }

    @Override
    public List<Dish> filter(List<Dish> dishes) {
        throw new NotImplementedException();
    }
}
