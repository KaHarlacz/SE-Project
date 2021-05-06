package model;

import model.exception.NotImplementedException;

import java.awt.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
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

        if (name.equals(""))
            name = "No name specified";

        if(servings <= 0)
            servings = 1;

        if(ingredients == null || ingredients.isEmpty())
            ingredients = new HashMap<>();

        if (categories == null || categories.isEmpty()) {
            categories = new ArrayList<>();
            categories.add(DishCategory.OTHER);
        }

        this.name = name;
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.categories = categories;
        this.image = image;
        this.duration = duration;
        this.servings = servings;
    }

    public boolean addIngredient(Ingredient ingredient, double quantity) {
        var keySet = ingredients.keySet();

        if(quantity <= 0 || keySet.contains(ingredient))
            return false;

        ingredients.put(ingredient, quantity);

        return true;
    }

    public boolean deleteIngredient(Ingredient ingredient) {
        var keySet = ingredients.keySet();

        if(!keySet.contains(ingredient))
            return false;

        ingredients.remove(ingredient);

        return true;
    }

    public String getName() {
        return name;
    }

    public String getRecipe() {
        return recipe;
    }

    public Image getImage() {
        return image;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getServings() {
        return servings;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public List<DishCategory> getCategories() {
        return categories;
    }

    public Map<Ingredient, Double> getIngredients() {
        return ingredients;
    }
}
