package model.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import splitter.SplitStrategy;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingListTest {
    private ShoppingList shoppingList;
    private Dish dish;
    private List<Ingredient> ingredients;

    private static List<Ingredient> getSetUpIngredients() {
        var ingredients = List.of(mock(Ingredient.class), mock(Ingredient.class));
        var quantities = List.of(mock(Quantity.class), mock(Quantity.class));
        when(ingredients.get(0).getQuantity()).thenReturn(quantities.get(0));
        when(ingredients.get(1).getQuantity()).thenReturn(quantities.get(1));
        when(ingredients.get(0).getName()).thenReturn("ABC");
        when(ingredients.get(1).getName()).thenReturn("DEF");
        when(quantities.get(0).getValue()).thenReturn(1.);
        when(quantities.get(0).getUnit()).thenReturn("szt");
        when(quantities.get(1).getValue()).thenReturn(1.);
        when(quantities.get(1).getUnit()).thenReturn("szt");
        return ingredients;
    }

    @BeforeEach
    public void setUp() {
        shoppingList = ShoppingList.getInstance();
        shoppingList.clear();
        dish = mock(Dish.class);
        ingredients = getSetUpIngredients();
        when(dish.getIngredients()).thenReturn(Set.copyOf(ingredients));
    }

    @Test
    public void addDishCheckIfAdded() {
        shoppingList.addIngredientsFrom(dish);
        var addedIngredients = new ArrayList<>(shoppingList.getIngredients());
        addedIngredients.sort(Comparator.comparing(Ingredient::getName));

        assertEquals(Map.of(dish, 1), shoppingList.getSelectedDishesWithTimesSelected());
        assertEquals(ingredients.get(0).getName(), addedIngredients.get(0).getName());
        assertEquals(ingredients.get(1).getName(), addedIngredients.get(1).getName());
        assertEquals(ingredients.get(0).getQuantity().getValue(), addedIngredients.get(0).getQuantity().getValue());
        assertEquals(ingredients.get(1).getQuantity().getValue(), addedIngredients.get(1).getQuantity().getValue());
        assertEquals(ingredients.get(0).getQuantity().getUnit(), addedIngredients.get(0).getQuantity().getUnit());
        assertEquals(ingredients.get(1).getQuantity().getUnit(), addedIngredients.get(1).getQuantity().getUnit());
    }

    @Test
    public void addDishIngredientsFewTimesCheckIfAdded() {
        final var ADD_COUNT = 3;

        for (int i = 0; i < ADD_COUNT; i++)
            shoppingList.addIngredientsFrom(dish);

        var addedIngredients = new ArrayList<>(shoppingList.getIngredients());
        addedIngredients.sort(Comparator.comparing(Ingredient::getName));

        assertEquals(Map.of(dish, ADD_COUNT), shoppingList.getSelectedDishesWithTimesSelected());
        assertEquals(ingredients.get(0).getName(), addedIngredients.get(0).getName());
        assertEquals(ingredients.get(1).getName(), addedIngredients.get(1).getName());
        assertEquals(ADD_COUNT * ingredients.get(0).getQuantity().getValue(), addedIngredients.get(0).getQuantity().getValue());
        assertEquals(ADD_COUNT * ingredients.get(1).getQuantity().getValue(), addedIngredients.get(1).getQuantity().getValue());
        assertEquals(ingredients.get(0).getQuantity().getUnit(), addedIngredients.get(0).getQuantity().getUnit());
        assertEquals(ingredients.get(1).getQuantity().getUnit(), addedIngredients.get(1).getQuantity().getUnit());
    }

    @Test
    public void deleteDishCheckIfDeleted() {
        shoppingList.addIngredientsFrom(dish);
        shoppingList.deleteIngredientsFrom(dish);

        assertEquals(Set.of(), shoppingList.getIngredients());
        assertEquals(Map.of(), shoppingList.getSelectedDishesWithTimesSelected());
    }

    @Test
    public void deleteIngredientCompletelyCheckIfDeleted() {
        final var ADD_COUNT = 3;

        for (int i = 0; i < ADD_COUNT; i++)
            shoppingList.addIngredientsFrom(dish);

        for(int i = 0; i < ADD_COUNT; i++)
            shoppingList.deleteIngredientsFrom(dish);

        assertEquals(Set.of(), shoppingList.getIngredients());
        assertEquals(Map.of(), shoppingList.getSelectedDishesWithTimesSelected());
    }

    @Test
    public void deleteIngredientNotCompletelyCheckIfNotDeleted() {
        final var ADD_COUNT = 3;
        final var DELETE_COUNT = 2;

        for (int i = 0; i < ADD_COUNT; i++)
            shoppingList.addIngredientsFrom(dish);

        for(int i = 0; i < DELETE_COUNT; i++)
            shoppingList.deleteIngredientsFrom(dish);

        var addedIngredients = new ArrayList<>(shoppingList.getIngredients());
        addedIngredients.sort(Comparator.comparing(Ingredient::getName));

        assertEquals(Map.of(dish, (ADD_COUNT - DELETE_COUNT)), shoppingList.getSelectedDishesWithTimesSelected());
        assertEquals(ingredients.get(0).getName(), addedIngredients.get(0).getName());
        assertEquals(ingredients.get(1).getName(), addedIngredients.get(1).getName());
        assertEquals((ADD_COUNT - DELETE_COUNT) * ingredients.get(0).getQuantity().getValue(), addedIngredients.get(0).getQuantity().getValue());
        assertEquals((ADD_COUNT - DELETE_COUNT) * ingredients.get(1).getQuantity().getValue(), addedIngredients.get(1).getQuantity().getValue());
        assertEquals(ingredients.get(0).getQuantity().getUnit(), addedIngredients.get(0).getQuantity().getUnit());
        assertEquals(ingredients.get(1).getQuantity().getUnit(), addedIngredients.get(1).getQuantity().getUnit());
    }

    @Test
    public void splitIngredients() {
        var ingredients = List.copyOf(this.ingredients);
        List<List<Ingredient>> expectedList = List.of(List.of(ingredients.get(0)), List.of(ingredients.get(1)), List.of());
        SplitStrategy splitStrategy = (ingredientList, lists) -> expectedList;

        assertEquals(expectedList, shoppingList.splitIngredientListUsing(splitStrategy, 3));
    }
}