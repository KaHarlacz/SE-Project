package model.filter;

import model.data.Dish;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class NameFilter implements Filter {
    private String filterText;

    public NameFilter(String text) {
        this.filterText = normalizeString(text);
    }

    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        var result = new HashSet<Dish>();

        for (var dish : dishes)
            if (containsFilterText(dish))
                result.add(dish);

        return result;
    }

    private boolean containsFilterText(Dish dish) {
        var name = dish.getName();
        var normalizedName = normalizeString(name);
        return normalizedName.contains(filterText);
    }

    private String normalizeString(String string) {
        return string.trim().toLowerCase(Locale.ROOT);
    }
}
