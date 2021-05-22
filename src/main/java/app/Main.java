package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/main_menu/mainMenuPane.fxml"));
        Scene scene = new Scene(pane);
        stage.setTitle("Shopping Organiser");
        stage.setScene(scene);
        stage.show();
    }
}
