module ShoppingOrganiser {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    exports controller;
    exports model.data;
    exports model.filter;
    opens controller;
}