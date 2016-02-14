/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class AddAccountWindowController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        this.choiceBox.getItems().add("Dropbox");
        this.choiceBox.getItems().add("Google Drive");
        this.choiceBox.getItems().add("One Drive");
        this.choiceBox.getItems().add("Amazon s3");
    }    
    
}
