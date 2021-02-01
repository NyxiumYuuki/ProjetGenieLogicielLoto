package fr.myny.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe permettant d'initialiser et d'écouter le changement des ChoiceBox
 */
public class DocumentController implements Initializable {

    /**
     * ChoiceBox créé grâce à FXML
     */
    private ChoiceBox<Integer> integerChoiceBox;

    /**
     *
     * ChoiceBox créé grâce à FXML
     */
    private ChoiceBox<String> stringChoiceBox;

    /**
     * Liste d'entier observable grâce à FX
     */
    private ObservableList observableIntegerList;

    /**
     * Liste de chaîne de caractère observable grâce à FX
     */
    private ObservableList observableStringList;

    /**
     * Initialisation de la première ChoiceBox du nombre de numéros voulu
     * @param url Paramètre non utilisé mais obligatoire en argument
     * @param resourceBundle Paramètre non utilisé mais obligatoire en argument
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Action lors d'un évenement concernant la garantie du système
     * @param event Action de l'évenement
     */
    @FXML
    private void numberGuarantee(ActionEvent event){

    }

    /**
     * Action lors d'un évenement concernant le nombre de numéros à jouer
     * @param event Action de l'évenement
     */
    @FXML
    private void numberPlay(ActionEvent event){

    }

    /**
     * Chargement de toutes les options garantie du systeme
     */
    private void loadDataGuarantee(){

    }

    /**
     * Chargement de toutes les options liés au nombre de numéro voulu
     */
    private void loadDataNumbers(){

    }

}
