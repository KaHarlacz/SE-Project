package model;

import model.exception.NotImplementedException;

import java.util.List;

public class FavouriteFilter implements Filter{
    @Override
    public List<Dish> filter(List<Dish> dishes) {
        throw new NotImplementedException();
    }
}
