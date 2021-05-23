package model.enumerative;

public enum DishCategory {
    ALL("Wszystkie"),
    BREAKFAST("Sniadanie"),
    BRUNCH("Drugie śniadanie"),
    DINNER("Obiad"),
    DESSERT("Deser"),
    TEA("Podwieczorek"),
    SUPPER("Kolacja"),
    SNACK("Przekąska"),
    OTHER("Inne");

    private final String name;

    DishCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
