package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

abstract class ViewController extends Controller {
    protected ApplicationController parent;
    protected ViewController prev;
    protected ViewController next;
    protected Scene scene;

    void setNext(ViewController controller) {
        next = controller;
    }
    void setPrev(ViewController controller) {
        prev = controller;
    }
    void setParent(ApplicationController controller) {
        parent = controller;
    }
    void setScene(Scene scene) {
        this.scene = scene;
    }
    void show(Stage stage) {
        stage.setScene(scene);
    }
    void refresh() {}
}
