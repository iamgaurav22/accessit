/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class HomeWindowController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSearchButtonClicked(ActionEvent event) {
        
    }

    @FXML
    private void onShareButtonClicked(ActionEvent event) {
    }

    @FXML
    private void onDownloadButtonClicked(ActionEvent event) {
    }

    @FXML
    private void onAddAccountClicked(ActionEvent event) 
    {
        Stage s = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddAccountWindow.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();
    }
}
