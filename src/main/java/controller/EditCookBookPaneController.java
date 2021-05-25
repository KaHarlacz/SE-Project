package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.data.CookBook;
import model.files_management.Paths;
import model.files_management.SerialObjectLoader;

import java.util.Set;

public class EditCookBookPaneController {
    @FXML
    private FiltersPaneController filtersPaneController;
    @FXML
    private Button confirmChangesButton;
    @FXML
    private Button toMainMenuButton;
    @FXML
    private Button undoChangesButton;
    @FXML
    private ListView<?> dishListView;
    @FXML
    private ListView<?> ingredientsNeededListView;
    @FXML
    private TextArea recipTextArea;

}
