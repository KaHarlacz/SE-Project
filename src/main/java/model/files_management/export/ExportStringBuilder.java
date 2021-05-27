package model.files_management.export;

public class ExportStringBuilder {
    private StringBuilder result = new StringBuilder();

    public void addIngredient(String ingredient) {
        result.append(ingredient).append("\n");
    }

    public void nextList() {
        result.append("\n");
        result.append("============================");
        result.append("\n\n");
    }

    public String get() {
        return result.toString();
    }
}
