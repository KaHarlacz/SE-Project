package controller;

import files_management.Paths;
import files_management.export.SerializableObjectsExporter;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.data.CookBook;

import java.util.List;

public class MainMenuController extends MenuController {
    private ViewController choosingDishesController;
    private ViewController editCookBookController;

    @FXML
    private Button toCookBookEdit;
    @FXML
    private Button toLeave;
    @FXML
    private Button toDishChoose;

    @Override
    public void init() {
        setNavigationButtonsOnAction();
    }

    @Override
    public void exit() {
        var cookBook = CookBook.getInstance();
        var exporter = new SerializableObjectsExporter<CookBook>(Paths.COOK_BOOK);
        exporter.export(cookBook);
    }

    @Override
    public void setMenuReferences(List<ViewController> controllerList) {
        choosingDishesController = controllerList.get(0);
        editCookBookController = controllerList.get(1);
    }

    private void setNavigationButtonsOnAction() {
        toDishChoose.setOnAction(e -> parent.goToSceneOf(choosingDishesController));
        toCookBookEdit.setOnAction(e -> parent.goToSceneOf(editCookBookController));
        toLeave.setOnAction(e -> parent.exit());
    }
}
