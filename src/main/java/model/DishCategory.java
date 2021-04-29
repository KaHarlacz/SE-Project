package model;

import model.exception.NotImplementedException;

public enum DishCategory {
    BREAKFAST("Sniadanie"),
    BRUNCH("Drugie śniadanie"),
    DINNER("Obiad"),
    TEA("Podwieczorek"),
    SUPPER("Kolacja"),
    SNACK("Przekąska"),
    DESSERT("Deser"),
    OTHER("Inne");

    private final String name;

    DishCategory(String name) {
        this.name = name;
    }

    public String getName() {
        throw new NotImplementedException();
    }
}
