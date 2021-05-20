package model.splitter;

import model.data.Ingredient;
import model.enumerative.IngredientCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoriesSplitterTest {
    private List<Ingredient> ingredients;
    private List<IngredientCategory> categories;

    @BeforeEach
    public void setUp() {
        ingredients = List.of(
                mock(Ingredient.class), mock(Ingredient.class),
                mock(Ingredient.class), mock(Ingredient.class)
        );

        categories = List.of(
                IngredientCategory.SPICE,
                IngredientCategory.VEGETABLE,
                IngredientCategory.FLOUR
        );
    }

    @Test
    public void splitNotEqualTwoCategoriesIngredientsTwoLists() {
        when(ingredients.get(0).getCategory()).thenReturn(categories.get(0));
        when(ingredients.get(1).getCategory()).thenReturn(categories.get(1));
        when(ingredients.get(2).getCategory()).thenReturn(categories.get(0));
        when(ingredients.get(3).getCategory()).thenReturn(categories.get(0));

        // Expected sets of categories in output maps
        var zeroCategoryIngredients = Set.of(ingredients.get(0), ingredients.get(2), ingredients.get(3));
        var firstCategoryIngredients = Set.of(ingredients.get(1));
        var expectedSets = Set.of(zeroCategoryIngredients, firstCategoryIngredients);

        // For testing purpose it is better to compare sets of ingredients
        var split = new CategoriesSplitter().split(ingredients, 2);
        var actualIngredientsSets = Set.of(Set.copyOf(split.get(0)), Set.copyOf(split.get(1)));

        assertEquals(expectedSets, actualIngredientsSets);
    }

    @Test
    public void splitNotEqualThreeCategoriesIngredientsThreeLists() {
        when(ingredients.get(0).getCategory()).thenReturn(categories.get(1));
        when(ingredients.get(1).getCategory()).thenReturn(categories.get(0));
        when(ingredients.get(2).getCategory()).thenReturn(categories.get(2));
        when(ingredients.get(3).getCategory()).thenReturn(categories.get(0));

        var setOfZeroCategory = Set.of(ingredients.get(1), ingredients.get(3));
        var setOfFirstCategory = Set.of(ingredients.get(0));
        var setOfSecondCategory = Set.of(ingredients.get(2));
        var expectedIngredientsSets = Set.of(setOfZeroCategory, setOfFirstCategory, setOfSecondCategory);

        var split = new CategoriesSplitter().split(ingredients, 3);
        var actualIngredientsSets = Set.of(Set.copyOf(split.get(0)), Set.copyOf(split.get(1)), Set.copyOf(split.get(2)));

        assertEquals(expectedIngredientsSets, actualIngredientsSets);
    }
}
