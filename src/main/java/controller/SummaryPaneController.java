package controller;

import files_management.Paths;
import files_management.export.ExportIngredientsListBuilder;
import files_management.export.TXTExporter;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import model.data.Ingredient;
import model.data.ShoppingList;
import model.enumerative.SplitOption;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SummaryPaneController extends ViewController {
    private static final int LIST_VIEWS = 3;

    private ShoppingList shoppingList = ShoppingList.getInstance();

    private List<ListView<String>> listViews;
    @FXML
    private Button exportButton;
    @FXML
    private Button goBackButton;
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

    @Override
    public void init() {
        wrapListViewsIntoOneList();
        setGoBackButtonOnAction();
        setSliderOnAction();
        setUpSplitOptions();
        setUpExportButton();
    }

    @Override
    public void refresh() {
        showIngredientLists();
    }

    private void setUpSplitOptions() {
        addSplitOptionsToChoiceBox();
        setDefaultSplitOption();
        setSplitOptionsOnAction();
    }

    private void showIngredientLists() {
        chosenSplitOption().ifPresent(this::applySplit);
    }

    private void showIngredientsOnList(ListView<String> listView, List<Ingredient> ingredients) {
        clearListView(listView);
        addIngredientsToListView(listView, ingredients);
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

    private void wrapListViewsIntoOneList() {
        listViews = List.of(firstResultListView, secondResultListView, thirdResultListView);
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

    private void setUpExportButton() {
        exportButton.setOnAction(e -> {
            exportIngredientList();
            parent.goToSceneOf(next);
        });
    }

    private void setGoBackButtonOnAction() {
        goBackButton.setOnAction(e -> parent.goToSceneOf(prev));
    }

    private void setSplitOptionsOnAction() {
        splitterTypeChoiceBox.setOnAction(e -> {
            chosenSplitOption().ifPresent(option -> splitterTypeChoiceBox.setValue(option.getDescription()));
            showIngredientLists();
        });
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

    private Optional<SplitOption> chosenSplitOption() {
        return SplitOption.fromDescription(splitterTypeChoiceBox.getValue());
    }

    private void exportIngredientList() {
        new TXTExporter(Paths.LIST_EXPORT).export(buildExportString());
    }

    private String buildExportString() {
        var exportStringBuilder = new ExportIngredientsListBuilder().appendHeader();
        for (int i = 0; i < numberOfListsSlider.getValue(); i++) {
            listViews.get(i).getItems().forEach(exportStringBuilder::appendIngredient);
            exportStringBuilder.nextList();
        }
        return exportStringBuilder.get();
    }

    private void setSliderOnAction() {
        numberOfListsSlider.setOnMouseReleased(e -> showIngredientLists());
    }
}
