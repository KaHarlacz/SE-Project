package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;

public class SummaryPaneController {

    @FXML
    private Button goBackButton;

    @FXML
    private Button saveAllChangesButton;

    @FXML
    private TitledPane resultListA;

    @FXML
    private TitledPane resultListB;

    @FXML
    private TitledPane resultListC;

    @FXML
    private Slider numberOfListsSlider;

    @FXML
    private ChoiceBox<?> splitterTypeChoiceBox;

    private MainController parent;

    public void init() {
        goBackButton.setOnAction(e -> parent.goToChoosingDishes());
    }

    public void setParent(MainController mainController) {
        parent = mainController;
    }
}
