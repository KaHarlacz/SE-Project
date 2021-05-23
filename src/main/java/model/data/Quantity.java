package model.data;

public class Quantity {
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

    public void multiplyValueBy(double factor) {
        if(factor <= 0)
            throw new IllegalArgumentException();

        value *= factor;
    }

    public void subtractValue(double val) {
        this.value -= val;
    }

    public void addValue(Quantity quantity) {
        if(!this.unit.equals(quantity.unit))
            throw new IllegalArgumentException();
        this.value += quantity.getValue();
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
