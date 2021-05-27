package model.enumerative;

import model.splitter.CategoriesSplitter;
import model.splitter.EqualListsSplitter;
import model.splitter.SplitStrategy;

import java.util.Arrays;
import java.util.Optional;

public enum SplitOption {
    SPLIT_BY_CATEGORIES("Podziel kategoriami", new CategoriesSplitter()),
    SPLIT_EQUALLY("Podziel po równo", new EqualListsSplitter());

    private String description;
    private SplitStrategy strategy;

    SplitOption(String description, SplitStrategy strategy) {
        this.description = description;
        this.strategy = strategy;
    }

    public static Optional<SplitOption> fromDescription(String description) {
        return Arrays.stream(SplitOption.values())
                .filter(option -> option.description.equals(description))
                .findFirst();
    }

    public String getDescription() {
        return description;
    }

    public SplitStrategy getStrategy() {
        return strategy;
    }
}
