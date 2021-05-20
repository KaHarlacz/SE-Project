package model.data;

public class Quantity {
    private final double value;
    private final String unit;

    public Quantity(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
