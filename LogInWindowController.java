/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessit;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class LogInWindowController implements Initializable {

    private static Stage stage;
    
    @FXML
    private TextField logInTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label invalidLabel;
    @FXML
    private AnchorPane infoLabel;
    @FXML
    private Button resetPasswordButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    
    public static void closeStage()
    {
        stage.close();
    }
    
/******************************************************************************
    When the LogIn button is clicked and credentials are verified, home window
    should open.
******************************************************************************/ 
    
    @FXML
    private void onLogInButtonClicked(ActionEvent event) throws IOException, NoSuchAlgorithmException
    {   
        boolean isUsernameValid = false;
        boolean isCombinationValid = false;
        stage = new Stage();
        Parent root = null;
        
        ClientForCommunication.communicate();
        ClientForCommunication.logInCommunication(logInTextField.getText(),passwordTextField.getText());
        isUsernameValid = ClientForCommunication.getIsUsernameValid();
        isCombinationValid = ClientForCommunication.getIsCombinationValid();
        
        if((isUsernameValid)&&(isCombinationValid))
        {
            try {
                root = FXMLLoader.load(getClass().getResource("HomeWindow.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(LogInWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ACCESSit.closeStage();
        
        }       
        else
        {
            this.invalidLabel.setVisible(true);
        }
    }

    @FXML
    private void onSignUpButtonClicked(ActionEvent event) {
        stage = new Stage();
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("SignUpWindow.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LogInWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ACCESSit.closeStage();
    }

    @FXML
    private void onResetPasswordButton(ActionEvent event) {
    }
}
