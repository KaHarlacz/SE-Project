package model.builder;

import javafx.scene.image.Image;
import model.data.Dish;
import model.data.Ingredient;
import model.enumerative.DishCategory;

import java.time.Duration;
import java.util.Set;

public interface DishBuilder {
    DishBuilder name(String name);

    DishBuilder recipe(String recipe);

    DishBuilder description(String description);

    DishBuilder ingredients(Set<Ingredient> ingredients);

    DishBuilder categories(Set<DishCategory> categories);

    DishBuilder image(Image image);

    DishBuilder image(String imagePath);

    DishBuilder duration(Duration duration);

    DishBuilder servings(int servings);

    DishBuilder favourite(boolean favourite);

    Dish build();
}
