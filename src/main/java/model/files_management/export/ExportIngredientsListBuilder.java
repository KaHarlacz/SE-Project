package model.files_management.export;

import java.time.LocalDate;

public class ExportIngredientsListBuilder {
    private StringBuilder result = new StringBuilder();

    public ExportIngredientsListBuilder appendHeader() {
        result.append("Lista zakupów ")
                .append(LocalDate.now())
                .append("\n")
                .append("============================")
                .append("\n\n");
        return this;
    }

    public ExportIngredientsListBuilder appendIngredient(String ingredient) {
        result.append(ingredient)
                .append("\n");
        return this;
    }

    public ExportIngredientsListBuilder nextList() {
        result.append("\n")
                .append("============================")
                .append("\n\n");
        return this;
    }

    public String get() {
        return result.toString();
    }
}
