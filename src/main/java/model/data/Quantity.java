package model.data;

import java.io.Serializable;

public class Quantity implements Serializable {
    private static final long serialVersionUID = 1L;

    private double value;
    private final String unit;

    public Quantity(double value, String unit) {
        if(value <= 0)
            throw new IllegalArgumentException();

        if(unit.isEmpty())
            unit = "szt";

        this.value = value;
        this.unit = unit;
    }

    //TODO: Impl
    public static Quantity copyOf(Quantity quantity) {
        return new Quantity(quantity.getValue(), quantity.getUnit());
    }

    public void multiplyValueBy(double factor) {
        if(factor <= 0)
            throw new IllegalArgumentException();

        value *= factor;
    }

    public void subtractValue(double val) {
        this.value -= val;
    }

    public void subtract(Quantity quantity) {
        if(!this.unit.equals(quantity.getUnit()))
            throw new IllegalArgumentException("Quantity units don't match");
        this.value -= quantity.getValue();
    }

    public void add(Quantity quantity) {
        if(!this.unit.equals(quantity.getUnit()))
            throw new IllegalArgumentException("Quantity units don't match");
        this.value += quantity.getValue();
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
