package model.data;

import javafx.scene.image.Image;
import model.enumerative.DishCategory;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

        if (name.equals(""))
            throw new IllegalArgumentException();

        if (servings <= 0)
            throw new IllegalArgumentException();

        if (ingredients == null || ingredients.isEmpty())
            throw new IllegalArgumentException();

        if (categories == null || categories.isEmpty()) {
            categories = new HashSet<>();
            categories.add(DishCategory.OTHER);
        }

        this.name = name;
        this.recipe = recipe;
        this.description = description;
        this.ingredients = ingredients;
        this.categories = categories;
        this.image = image;
        this.duration = duration;
        this.servings = servings;
    }

    public boolean addIngredient(Ingredient ingredient) {
        if (ingredient.getQuantity().getValue() <= 0 || ingredients.contains(ingredient))
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

    public String getRecipe() {
        return recipe;
    }

    public String getDescription(){
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
}
