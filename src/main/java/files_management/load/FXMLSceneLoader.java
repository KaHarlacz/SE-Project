package files_management.load;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Optional;

public class FXMLSceneLoader implements Loader<Scene> {
    private final String path;

    public FXMLSceneLoader(String path) {
        this.path = path;
    }

    @Override
    public Optional<Scene> load() {
        try {
            var loader = new FXMLLoader(getClass().getResource(path));
            var scene = new Scene(loader.load());
            return Optional.of(scene);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load scene from " + path);
        }
    }
}
