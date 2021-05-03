package model;

import java.util.List;
import java.util.Map;

public interface Splitter {
    List<Map<Ingredient, Double>> split(Map<Ingredient, Double> ingredients, int lists);
}