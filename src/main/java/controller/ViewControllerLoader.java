package controller;

import files_management.load.Loader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Optional;

public class ViewControllerLoader implements Loader<ViewController> {
    private final String path;

    public ViewControllerLoader(String path) {
        this.path = path;
    }

    @Override
    public Optional<ViewController> load() {
        try {
            var loader = new FXMLLoader(getClass().getResource(path));
            var scene = new Scene(loader.load());
            var controller = (ViewController) loader.getController();
            controller.setScene(scene);
            return Optional.of(controller);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load scene from " + path);
        }
    }
}
