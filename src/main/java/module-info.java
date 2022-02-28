module seg.g33 {
    requires javafx.controls;
    requires javafx.fxml;

    opens seg.g33 to javafx.fxml;
    exports seg.g33;
}
