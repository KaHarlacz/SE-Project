package model.builder;

import javafx.scene.image.Image;
import model.data.Dish;
import model.data.Ingredient;
import model.enumerative.DishCategory;

import java.time.Duration;
import java.util.Set;

public interface DishBuilder {
    DishBuilder withName(String name);

    DishBuilder withRecipe(String recipe);

    DishBuilder withDescription(String description);

    DishBuilder withIngredients(Set<Ingredient> ingredients);

    DishBuilder withCategories(Set<DishCategory> categories);

    DishBuilder withImage(Image image);

    DishBuilder withImage(String imagePath);

    DishBuilder withDuration(Duration duration);

    DishBuilder withServings(int servings);

    DishBuilder asFavourite(boolean favourite);

    Dish build();
}
