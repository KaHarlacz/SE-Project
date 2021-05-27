package model.files_management.export;

import java.time.LocalDate;

public class ExportStringBuilder {
    private StringBuilder result = new StringBuilder();

    public ExportStringBuilder appendHeader() {
        result.append("Lista zakupów ")
                .append(LocalDate.now())
                .append("\n")
                .append("============================")
                .append("\n\n");
        return this;
    }

    public ExportStringBuilder nextIngredient(String ingredient) {
        result.append(ingredient)
                .append("\n");
        return this;
    }

    public ExportStringBuilder nextList() {
        result.append("\n")
                .append("============================")
                .append("\n\n");
        return this;
    }

    public String get() {
        return result.toString();
    }
}
