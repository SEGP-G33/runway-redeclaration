module seg.g33 {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.xml;
    requires org.apache.logging.log4j;

    opens seg.g33 to javafx.fxml;
    exports seg.g33;
}
