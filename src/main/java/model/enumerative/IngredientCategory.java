package model.enumerative;

public enum IngredientCategory {
    CEREAL_GRAIN("Ziarno zboz"),
    DAIRY("Nabial"),
    FLOUR("Maka"),
    FRUIT("Owoc"),
    HERBS("Ziola"),
    MEAT("Mieso"),
    NUT("Orzech"),
    OTHER("Inna"),
    OIL("Tluszcze"),
    SEAFOOD("Owoce morza"),
    SPICE("Przyprawa"),
    SWEETENER("Slodzik"),
    VEGETABLE("Warzywo");

    private final String name;

    IngredientCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
