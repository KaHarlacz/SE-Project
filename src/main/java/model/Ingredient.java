package model;

import model.exception.NotImplementedException;

public class Ingredient implements Comparable<Ingredient> {
    private String name;
    private String unit;
    private IngredientCategory category;

    public Ingredient(String name, String unit) {
        throw new NotImplementedException();
    }

    public String getUnit() {
        throw new NotImplementedException();
    }

    public void setUnit(String unit) {
        throw new NotImplementedException();
    }

    public String getName() {
        throw new NotImplementedException();
    }

    public void setName(String orange) {
        throw new NotImplementedException();
    }

    public IngredientCategory getCategory() {
        throw new NotImplementedException();
    }

    public void setCategory(IngredientCategory category) {
        throw new NotImplementedException();
    }

    @Override
    public int compareTo(Ingredient o) {
        throw new NotImplementedException();
    }
}
