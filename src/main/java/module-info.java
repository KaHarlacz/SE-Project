module ShoppingOrganiser {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    exports app;
    opens controller;
}