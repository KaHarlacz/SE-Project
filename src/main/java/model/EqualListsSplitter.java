package model;

import model.exception.NotImplementedException;

import java.util.List;
import java.util.Map;

public class EqualListsSplitter implements Splitter {
    @Override
    public List<Map<Ingredient, Double>> split(Map<Ingredient, Double> ingredients, int lists) {
        throw new NotImplementedException();
    }
}
