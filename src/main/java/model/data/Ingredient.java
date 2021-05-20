package model.data;

import model.enumerative.IngredientCategory;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable, Comparable<Ingredient> {
    private static final long serialVersionUID = 1L;

    private String name;
    private IngredientCategory category;
    private Quantity quantity;

    public Ingredient(String name, IngredientCategory category, Quantity quantity) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name)
                && category == that.category
                && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, quantity);
    }

    @Override
    public int compareTo(Ingredient other) {
        return this.name.compareTo(other.name);
    }
}
