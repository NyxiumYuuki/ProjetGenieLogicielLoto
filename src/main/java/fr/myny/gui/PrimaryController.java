package fr.myny.gui;

import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Gui.setRoot("secondary");
    }
}
