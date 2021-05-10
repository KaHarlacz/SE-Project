package model.data;

import model.enumerative.DishCategory;
import model.exception.NotImplementedException;

import java.awt.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class Dish implements Serializable, Comparable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String recipe;
    private Map<Ingredient, Double> ingredients;
    private Set<DishCategory> categories;
    private Image image;
    private Duration duration;
    private int servings;
    private boolean favourite;

    public Dish(String name,
                String recipe,
                Map<Ingredient, Double> ingredients,
                Set<DishCategory> categories,
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
            categories = new HashSet<>();
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

    public Set<DishCategory> getCategories() {
        return categories;
    }

    public Map<Ingredient, Double> getIngredientsMap() {
        return ingredients;
    }

    public Set<Ingredient> getIngredientsSet() {
        return ingredients.keySet();
    }

    @Override
    public int compareTo(Object o) {
        throw new NotImplementedException();
    }
}
