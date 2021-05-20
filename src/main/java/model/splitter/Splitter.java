package model.splitter;

import model.data.Ingredient;

import java.util.List;

public interface Splitter {
    List<List<Ingredient>> split(List<Ingredient> ingredients, int lists);
}
