module ShoppingOrganiser {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    exports controller;
    opens controller;
}