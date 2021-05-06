package model;

import model.exception.NotImplementedException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name) && Objects.equals(unit, that.unit) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit, category);
    }
}
