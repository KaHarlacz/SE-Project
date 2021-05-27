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
        goBackButton.setOnAction(e -> parent.goToChoosingDishes());
        setSliderOnAction();
        wrapListViewsIntoOneList();
        setUpSplitOptions();
        setUpExportButton();
    }

    private void setUpExportButton() {
        exportButton.setOnAction(e -> {
            export();
            parent.goToMainMenu();
        });
    }

    private void export() {
        var exportStringBuilder = new ExportStringBuilder();
        for (int i = 0; i < numberOfListsSlider.getValue(); i++) {
            var currentList = listViews.get(i);
            currentList.getItems().forEach(exportStringBuilder::addIngredient);
            exportStringBuilder.nextList();
        }
        new TXTExporter(Paths.EXPORT_PATH).export(exportStringBuilder.get());
    }

    private void setUpSplitOptions() {
        Arrays.stream(SplitOption.values())
                .forEach(splitOption -> splitterTypeChoiceBox.getItems().add(splitOption.getDescription()));

        splitterTypeChoiceBox.setValue(SplitOption.values()[0].getDescription());
        splitterTypeChoiceBox.setOnAction(e -> showIngredientsLists());
    }

    private void wrapListViewsIntoOneList() {
        listViews = List.of(firstResultListView, secondResultListView, thirdResultListView);
    }

    private void setSliderOnAction() {
        // TODO: Throws exceptions, doesn't work!!
        numberOfListsSlider.setOnMouseReleased(e -> showIngredientsLists());
    }

    protected void showIngredientsLists() {
        var splitOption = chosenSplitOption();
        splitOption.ifPresent(this::applySplitOption);
    }

    private void applySplitOption(SplitOption option) {
        var splitStrategy = option.getStrategy();
        var splitCount = (int) numberOfListsSlider.getValue();
        var split = shoppingList.splitIngredientsUsing(splitStrategy, splitCount);

        for (int i = 0; i < splitCount; i++)
            showIngredientsOnList(listViews.get(i), split.get(i));

        for (int i = splitCount; i < LIST_VIEWS; i++)
            listViews.get(i).getItems().clear();
    }

    private void showIngredientsOnList(ListView<String> listView, List<Ingredient> ingredients) {
        listView.getItems().clear();
        listView.getItems().addAll(ingredients
                .stream()
                .map(Ingredient::toString)
                .collect(Collectors.toSet())
        );
    }

    private Optional<SplitOption> chosenSplitOption() {
        return SplitOption.fromDescription(splitterTypeChoiceBox.getValue());
    }

    public void setParent(MainController mainController) {
        parent = mainController;
    }
}
