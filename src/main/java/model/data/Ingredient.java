package model.data;

import model.enumerative.IngredientCategory;
import model.exception.NotImplementedException;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable, Comparable<Ingredient> {
    private static final long serialVersionUID = 1L;

    private String name;
    private String unit;
    private IngredientCategory category;

    public Ingredient(String name, String unit, IngredientCategory category) {
        this(name, unit);
        this.category = category;
    }

    public Ingredient(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public Ingredient(String name) {
        this.name = name;
        this.unit = "other";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
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

    @Override
    public int compareTo(Ingredient other) {
        return this.name.compareTo(other.name);
    }
}
