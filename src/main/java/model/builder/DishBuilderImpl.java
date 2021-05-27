package model.builder;

import javafx.scene.image.Image;
import model.data.Dish;
import model.data.Ingredient;
import model.enumerative.DishCategory;

import java.time.Duration;
import java.util.Set;

public class DishBuilderImpl implements DishBuilder {
    private String name;
    private String recipe;
    private String description;
    private Set<Ingredient> ingredients;
    private Set<DishCategory> categories;
    private Image image;
    private Duration duration;
    private int servings = 1;
    private boolean favourite = false;

    public static DishBuilder builder() {
        return new DishBuilderImpl();
    }

    @Override
    public DishBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public DishBuilder withRecipe(String recipe) {
        this.recipe = recipe;
        return this;
    }

    @Override
    public DishBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public DishBuilder withIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Override
    public DishBuilder withCategories(Set<DishCategory> categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public DishBuilder withImage(Image image) {
        this.image = image;
        return this;
    }

    @Override
    public DishBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public DishBuilder withServings(int servings) {
        this.servings = servings;
        return this;
    }

    @Override
    public DishBuilder asFavourite(boolean favourite) {
        this.favourite = favourite;
        return this;
    }

    @Override
    public Dish get() {
        if(name.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        if(recipe.isEmpty())
            throw new IllegalArgumentException("Recipe cannot be empty");
        if(ingredients == null || ingredients.isEmpty())
            throw new IllegalArgumentException("Ingredients list cannot be empty");

        return new Dish(name, recipe, description, ingredients, categories, image, duration, servings);
    }
}
