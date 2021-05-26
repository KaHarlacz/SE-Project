package model.splitter;

import model.data.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class EqualListsSplitter implements SplitStrategy {
    @Override
    public List<List<Ingredient>> split(List<Ingredient> ingredients, int listsCount) {
        checkIfValidArguments(ingredients, listsCount);
        var result = initResultList(listsCount);
        splitIngredientsBetweenLists(result, ingredients);
        return result;
    }

    private void checkIfValidArguments(List<Ingredient> ingredients, int listsCount) {
        if(ingredients.size() < listsCount)
            throw new IllegalArgumentException();
    }

    private void splitIngredientsBetweenLists(List<List<Ingredient>> result, List<Ingredient> ingredients) {
        for(int i = 0; i < ingredients.size(); i++)
            result.get(i % result.size()).add(ingredients.get(i));
    }

    private List<List<Ingredient>> initResultList(int listsCount) {
        var result = new ArrayList<List<Ingredient>>();
        for(int i = 0; i < listsCount; i++)
            result.add(new ArrayList<>());
        return result;
    }
}
