module fr.myny.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens fr.myny.gui to javafx.fxml;
    exports fr.myny.gui;
}