package model.filter;

import model.data.Dish;
import model.exception.NotImplementedException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class NameFilter implements Filter {
    private String text;

    public NameFilter(String text) {
        this.text = normalizeString(text);
    }

    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        var result = new HashSet<Dish>();

        for (Dish dish : dishes) {
            var name = dish.getName();
            var normalizedName = normalizeString(name);
            if (normalizedName.contains(text))
                result.add(dish);
        }

        return result;
    }

    private String normalizeString(String string) {
        return string.trim().toLowerCase(Locale.ROOT);
    }
}
