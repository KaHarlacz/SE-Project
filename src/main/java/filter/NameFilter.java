package filter;

import model.data.Dish;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class NameFilter implements DishesFilterStrategy {
    private String filterText;

    public NameFilter(String text) {
        this.filterText = normalizeString(text);
    }

    @Override
    public Set<Dish> filter(Set<Dish> dishes) {
        return dishes.stream()
                .filter(this::containsFilterText)
                .collect(Collectors.toSet());
    }

    private boolean containsFilterText(Dish dish) {
        return normalizeString(dish.getName()).contains(filterText);
    }

    private String normalizeString(String string) {
        return string.trim().toLowerCase(Locale.ROOT);
    }
}
