package splitter;

import model.data.Ingredient;
import model.enumerative.IngredientCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoriesSplitter implements SplitStrategy {
    @Override
    public List<List<Ingredient>> split(List<Ingredient> ingredients, int listsCount) {
        var ingredientsByCategories = makeListOfIngredientsByCategories(ingredients);
        var result = initializeResultList(listsCount);
        splitIngredientsBetweenLists(result, ingredientsByCategories);
        return result;
    }

    private HashMap<IngredientCategory, List<Ingredient>> makeListOfIngredientsByCategories(List<Ingredient> ingredients) {
        var ingredientsByCategories = new HashMap<IngredientCategory, List<Ingredient>>();

        for (var ingredient : ingredients) {
            var category = ingredient.getCategory();

            if (ingredientsByCategories.containsKey(category)) {
                ingredientsByCategories.get(category).add(ingredient);
            } else {
                var categoryIngredients = new ArrayList<Ingredient>();
                categoryIngredients.add(ingredient);
                ingredientsByCategories.put(category, categoryIngredients);
            }
        }

        return ingredientsByCategories;
    }

    private void splitIngredientsBetweenLists(
            List<List<Ingredient>> result,
            HashMap<IngredientCategory, List<Ingredient>> ingredientsByCategories
    ) {
        int index = 0;
        for (var ingredientsOfCategory : ingredientsByCategories.values()) {
            var list = result.get(index % result.size());
            list.addAll(ingredientsOfCategory);
            index++;
        }
    }

    private List<List<Ingredient>> initializeResultList(int listsCount) {
        var list = new ArrayList<List<Ingredient>>();
        for (int i = 0; i < listsCount; i++)
            list.add(new ArrayList<>());
        return list;
    }
}
