package model;

import model.exception.NotImplementedException;

import java.awt.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String recipe;
    private Map<Ingredient, Double> ingredients;
    private List<DishCategory> categories;
    private Image image;
    private Duration duration;
    private int servings;
    private boolean favourite;

    public Dish(String name,
                String recipe,
                Map<Ingredient, Double> ingredients,
                List<DishCategory> categories,
                Image image,
                Duration duration,
                int servings) {
        throw new NotImplementedException();
    }

    public String getName() {
        throw new NotImplementedException();
    }

    public String getRecipe() {
        throw new NotImplementedException();
    }

    public Image getImage() {
        throw new NotImplementedException();
    }

    public Duration getDuration() {
        throw new NotImplementedException();
    }

    public int getServings() {
        throw new NotImplementedException();
    }

    public boolean isFavourite() {
        throw new NotImplementedException();
    }

    public void setFavourite(boolean favourite) {
        throw new NotImplementedException();
    }

    public List<DishCategory> getCategories() {
        throw new NotImplementedException();
    }

    public Map<Ingredient, Double> getIngredients() {
        throw new NotImplementedException();
    }

    public boolean addIngredient(Ingredient ingredient, double quantity) {
        throw new NotImplementedException();
    }

    public boolean deleteIngredient() {
        throw new NotImplementedException();
    }
}
