package model.data;

import model.exception.NotImplementedException;
import model.splitter.Splitter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Dish, Double> selectedDishes;
    private Map<Ingredient, Double> selectedIngredients;

    public Map<Dish, Double> getChosenDishes(){
        throw new NotImplementedException();   
    }

    public Map<Ingredient, Double> getRequiredIngredients(){
        throw new NotImplementedException();
    }

    public void splitRequiredIngredients(Splitter splitter){
        throw new NotImplementedException();
    }

    public boolean addDish(Dish dish, double quantity){
        throw new NotImplementedException();
    }

    public boolean deleteDish(Dish dish){
        throw new NotImplementedException();
    }

    public boolean addIngredient(Ingredient ingredient, double quantity){
        throw new NotImplementedException();
    }

    public boolean deleteIngredient(Ingredient ingredient){
        throw new NotImplementedException();
    }

    public void exportRequiredIngredients(){
        throw new NotImplementedException();
    }

}
