package model.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShoppingListTest {
    private ShoppingList shoppingList;
    private Dish mockDish;
    private Set<Ingredient> mockIngredients;

    @BeforeEach
    public void setUp() {
        shoppingList = ShoppingList.getInstance();
        mockDish = mock(Dish.class);
        mockIngredients = Set.of(mock(Ingredient.class), mock(Ingredient.class));
    }

    @Test
    public void addDishIngredientsCheckIfAdded() {
        when(mockDish.getIngredients()).thenReturn(mockIngredients);
        for (Ingredient mockIngredient : mockIngredients) {
            var mockQuantity = mock(Quantity.class);
            when(mockIngredient.getQuantity()).thenReturn(mockQuantity);
            when(mockQuantity.getUnit()).thenReturn("szt");
            when(mockQuantity.getValue()).thenReturn(1.);
        }

        shoppingList.addIngredientsFrom(mockDish);

        assertEquals(mockIngredients, shoppingList.getIngredients());
        assertEquals(Map.of(mockDish, 1), shoppingList.getSelectedDishes());
    }

    @Test
    public void addDishIngredientsTwiceCheckIfAdded() {
        when(mockDish.getIngredients()).thenReturn(mockIngredients);
        for (var mockIngredient : mockIngredients) {
            var mockQuantity = mock(Quantity.class);
            when(mockIngredient.getQuantity()).thenReturn(mockQuantity);
            when(mockQuantity.getUnit()).thenReturn("szt");
            when(mockQuantity.getValue()).thenReturn(1.);
        }

        shoppingList.addIngredientsFrom(mockDish);
        shoppingList.addIngredientsFrom(mockDish);

        assertEquals(mockIngredients, shoppingList.getIngredients());
        assertEquals(Map.of(mockDish, 2), shoppingList.getSelectedDishes());
    }

    @Test
    public void deleteDishCheckIfDeleted() {
        var ingredients = List.copyOf(mockIngredients);
        var quantities = List.of(mock(Quantity.class), mock(Quantity.class));

        when(mockDish.getIngredients()).thenReturn(mockIngredients);
        when(ingredients.get(0).getQuantity()).thenReturn(quantities.get(0));
        when(ingredients.get(1).getQuantity()).thenReturn(quantities.get(1));
        when(quantities.get(0).getValue()).thenReturn(1.);
        when(quantities.get(0).getUnit()).thenReturn("szt");
        when(quantities.get(1).getValue()).thenReturn(1.);
        when(quantities.get(1).getUnit()).thenReturn("szt");

        shoppingList.addIngredientsFrom(mockDish);
        shoppingList.deleteIngredientsFrom(mockDish);

        assertEquals(Set.of(), shoppingList.getIngredients());
        assertEquals(Map.of(), shoppingList.getSelectedDishes());
    }

    @Test
    public void deleteIngredientCompletelyCheckIfDeleted() {
        var ingredients = List.copyOf(mockIngredients);
        var quantities = List.of(mock(Quantity.class), mock(Quantity.class));

        when(mockDish.getIngredients()).thenReturn(mockIngredients);
        when(ingredients.get(0).getQuantity()).thenReturn(quantities.get(0));
        when(ingredients.get(1).getQuantity()).thenReturn(quantities.get(1));
        when(quantities.get(0).getValue()).thenReturn(1.);
        when(quantities.get(1).getValue()).thenReturn(1.);

        shoppingList.addIngredientsFrom(mockDish);
        shoppingList.deleteIngredient(ingredients.get(0));

        assertEquals(Set.of(ingredients.get(1)), shoppingList.getIngredients());
    }

/*
    @Test
    public void deleteIngredientNotCompletelyCheckIfNotDeleted() {
        var ingredients = List.copyOf(mockIngredients);
        var quantities = List.of(mock(Quantity.class), mock(Quantity.class), mock(Quantity.class));
        var ingredientToDelete = ingredients.get(0);

        when(mockDish.getIngredients()).thenReturn(mockIngredients);
        when(ingredients.get(0).getQuantity()).thenReturn(quantities.get(0));
        when(ingredients.get(1).getQuantity()).thenReturn(quantities.get(1));
        when(quantities.get(0).getValue()).thenReturn(1.);
        when(quantities.get(1).getValue()).thenReturn(1.);

        shoppingList.addIngredientsFrom(mockDish);
        when(ingredientToDelete.getQuantity()).thenReturn(quantities.get(2));
        when(quantities.get(2).getValue()).thenReturn(0.7);

        shoppingList.deleteIngredient(ingredientToDelete);

        assertEquals(mockIngredients, shoppingList.getIngredients());
        //shoppingList.getIngredients().
    }
 */
}