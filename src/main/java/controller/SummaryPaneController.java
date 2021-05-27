package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import model.data.Ingredient;
import model.data.ShoppingList;
import model.enumerative.SplitOption;
import model.files_management.Paths;
import model.files_management.export.ExportStringBuilder;
import model.files_management.export.TXTExporter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SummaryPaneController {

    private static final int LIST_VIEWS = 3;
    @FXML
    private Button goBackButton;
    @FXML
    private Button exportButton;
    @FXML
    private ListView<String> firstResultListView;
    @FXML
    private ListView<String> secondResultListView;
    @FXML
    private ListView<String> thirdResultListView;
    @FXML
    private Slider numberOfListsSlider;
    @FXML
    private ChoiceBox<String> splitterTypeChoiceBox;

    private MainController parent;
    private ShoppingList shoppingList = ShoppingList.getInstance();
    private List<ListView<String>> listViews;

    public void init() {
        wrapListViewsIntoOneList();
        setGoBackButtonOnAction();
        setSliderOnAction();
        setUpSplitOptions();
        setUpExportButton();
    }

    private void setUpSplitOptions() {
        addSplitOptionsToChoiceBox();
        setDefaultSplitOption();
        setSplitOptionsOnAction();
    }

    protected void showIngredientLists() {
        chosenSplitOption().ifPresent(this::applySplit);
    }

    private void applySplit(SplitOption option) {
        var splitStrategy = option.getStrategy();
        var splitCount = (int) numberOfListsSlider.getValue();
        var split = shoppingList.splitIngredientListUsing(splitStrategy, splitCount);

        for (int i = 0; i < splitCount; i++)
            showIngredientsOnList(listViews.get(i), split.get(i));

        for (int i = splitCount; i < LIST_VIEWS; i++)
            clearListView(listViews.get(i));
    }

    private void showIngredientsOnList(ListView<String> listView, List<Ingredient> ingredients) {
        clearListView(listView);
        addIngredientsToListView(listView, ingredients);
    }

    private void setUpExportButton() {
        exportButton.setOnAction(e -> {
            exportIngredientList();
            parent.goToMainMenu();
        });
    }

    private void setGoBackButtonOnAction() {
        goBackButton.setOnAction(e -> parent.goToChoosingDishes());
    }

    private void exportIngredientList() {
        new TXTExporter(Paths.EXPORT_PATH).export(buildExportString());
    }

    private String buildExportString() {
        var exportStringBuilder = new ExportStringBuilder().appendHeader();
        for (int i = 0; i < numberOfListsSlider.getValue(); i++) {
            listViews.get(i).getItems().forEach(exportStringBuilder::nextIngredient);
            exportStringBuilder.nextList();
        }
        return exportStringBuilder.get();
    }

    private void addIngredientsToListView(ListView<String> listView, List<Ingredient> ingredients) {
        listView.getItems().addAll(ingredients
                .stream()
                .map(Ingredient::toString)
                .collect(Collectors.toSet())
        );
    }

    private void clearListView(ListView<String> listView) {
        listView.getItems().clear();
    }

    private void setSplitOptionsOnAction() {
        splitterTypeChoiceBox.setOnAction(e -> showIngredientLists());
    }

    private void setDefaultSplitOption() {
        splitterTypeChoiceBox.setValue(SplitOption.values()[0].getDescription());
    }

    private void addSplitOptionsToChoiceBox() {
        Arrays.stream(SplitOption.values()).forEach(this::addSplitOptionToChoiceBox);
    }

    private void addSplitOptionToChoiceBox(SplitOption splitOption) {
        splitterTypeChoiceBox.getItems().add(splitOption.getDescription());
    }

    private void wrapListViewsIntoOneList() {
        listViews = List.of(firstResultListView, secondResultListView, thirdResultListView);
    }

    private void setSliderOnAction() {
        numberOfListsSlider.setOnMouseReleased(e -> showIngredientLists());
    }

    private Optional<SplitOption> chosenSplitOption() {
        return SplitOption.fromDescription(splitterTypeChoiceBox.getValue());
    }

    public void setParent(MainController mainController) {
        parent = mainController;
    }
}
