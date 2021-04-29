package model;

import model.exception.NotImplementedException;

import java.util.List;

public class NameFilter implements Filter {
    private String text;

    public NameFilter(String text) {
        throw new NotImplementedException();
    }

    @Override
    public List<Dish> filter(List<Dish> dishes) {
        throw new NotImplementedException();
    }
}
