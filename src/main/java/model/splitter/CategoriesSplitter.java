package model.splitter;

import model.data.Ingredient;
import model.exception.NotImplementedException;

import java.util.List;
import java.util.Map;

public class CategoriesSplitter implements Splitter {
    @Override
    public List<Map<Ingredient, Double>> split(Map<Ingredient, Double> ingredients, int lists) {
        throw new NotImplementedException();
    }
}
