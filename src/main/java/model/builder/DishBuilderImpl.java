package model.builder;

import javafx.scene.image.Image;
import model.data.Dish;
import model.data.Ingredient;
import model.enumerative.DishCategory;

import java.io.FileInputStream;
import java.io.IOException;
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
    public DishBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public DishBuilder recipe(String recipe) {
        this.recipe = recipe;
        return this;
    }

    @Override
    public DishBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public DishBuilder ingredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Override
    public DishBuilder categories(Set<DishCategory> categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public DishBuilder image(String imagePath) {
        try {
            this.image = new Image(new FileInputStream(imagePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot load dish image");
        }
        return this;
    }

    @Override
    public DishBuilder image(Image image) {
        this.image = image;
        return this;
    }

    @Override
    public DishBuilder duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public DishBuilder servings(int servings) {
        this.servings = servings;
        return this;
    }

    @Override
    public DishBuilder favourite(boolean favourite) {
        this.favourite = favourite;
        return this;
    }

    @Override
    public Dish build() {
        if(name.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        if(recipe.isEmpty())
            throw new IllegalArgumentException("Recipe cannot be empty");

        return new Dish(name, recipe, description, ingredients, categories, image, duration, servings);
    }
}
