package model.data;

import javafx.scene.image.Image;
import model.enumerative.DishCategory;

import java.io.Serializable;
import java.time.Duration;
import java.util.*;

public class Dish implements Serializable, Comparable<Dish> {
    private static final long serialVersionUID = 1L;

    private String name;
    private String recipe;
    private String description;
    private Set<Ingredient> ingredients;
    private Set<DishCategory> categories;
    private Image image;
    private Duration duration;
    private int servings;
    private boolean favourite;

    public Dish(String name,
                String recipe,
                String description,
                Set<Ingredient> ingredients,
                Set<DishCategory> categories,
                Image image,
                Duration duration,
                int servings) {
        this.name = name;
        this.recipe = recipe;
        this.description = description;
        // new sets to make sure they will be mutable
        if(ingredients == null)
            this.ingredients = new HashSet<>();
        else
            this.ingredients = new HashSet<>(ingredients);
        this.categories = getValidCategories(categories);
        this.image = image;
        this.duration = duration;
        this.servings = servings;
    }

    private Set<DishCategory> getValidCategories(Set<DishCategory> categories) {
        if(categories == null || categories.isEmpty())
            return categories;
        return new HashSet<>(List.of(DishCategory.OTHER));
    }

    public boolean addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient) || ingredient.getQuantity().getValue() <= 0)
            return false;
        ingredients.add(ingredient);
        return true;
    }

    public boolean deleteIngredient(Ingredient ingredient) {
        if (!ingredients.contains(ingredient))
            return false;
        ingredients.remove(ingredient);
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getDescription() {
        return description;
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return servings == dish.servings
                && favourite == dish.favourite
                && Objects.equals(name, dish.name)
                && Objects.equals(recipe, dish.recipe)
                && Objects.equals(ingredients, dish.ingredients)
                && Objects.equals(categories, dish.categories)
                && Objects.equals(image, dish.image)
                && Objects.equals(duration, dish.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, recipe, ingredients, categories, image, duration, servings, favourite);
    }

    @Override
    public int compareTo(Dish other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        var string = name;
        if(isFavourite())
            string += " " + "*";
        return string;
    }

    private void validData(String name, int servings) {
        if (name.equals(""))
            throw new IllegalArgumentException();

        if (servings <= 0)
            throw new IllegalArgumentException();
    }
}
