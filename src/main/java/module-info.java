module ShoppingOrganiser {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires lombok;

    exports controller;
    exports model.data;
    exports filter;
    opens controller;
    opens model.data;
}