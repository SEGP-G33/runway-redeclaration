module seg.g33 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    requires java.xml;
    requires org.apache.logging.log4j;
    requires java.desktop;
    requires controlsfx;

    opens seg.g33 to javafx.fxml;
    exports seg.g33;
    exports seg.g33.Controllers;
    opens seg.g33.Controllers to javafx.fxml;
}
