package fr.myny.gui;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Classe permettant le changement de scène ou de l'affichage de la popup
 */
public class SceneController {

    /**
     * Permet l'affichage de la scène 1
     * @throws IOException
     */
    @FXML
    private void switchToScene1() throws IOException {
        Gui.setRoot("scene1");
    }

    /**
     * Permet l'affichage de la scène 2
     * @throws IOException
     */
    @FXML
    private void switchToScene2() throws IOException {
        Gui.setRoot("scene2");
    }

    /**
     * Permet l'affichage de la scène 3
     * @throws IOException
     */
    @FXML
    private void switchToScene3() throws IOException {
        Gui.setRoot("scene3");
    }

    /**
     * Permet l'affichage de la scène 4
     * @throws IOException
     */
    @FXML
    private void switchToScene4() throws IOException {
        Gui.setRoot("scene4");
    }

    /**
     * Permet l'affichage de la PopUp
     * @throws IOException
     */
    @FXML
    private void showPopUp() throws IOException {
        Gui.setRoot("popup1");
    }
}
