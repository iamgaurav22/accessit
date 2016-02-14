/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ResetPasswordWindowController implements Initializable {

    @FXML
    private Button sendOTP;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField otpTextField;
    @FXML
    private TextField passTextField;
    @FXML
    private TextField conPassTextField;
    @FXML
    private Button submitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void onSendOTPClicked(ActionEvent event) 
    {
        ClientForCommunication.sendOTP(this.usernameTextField.getText());
    }

    @FXML
    private void onSubmitButtonClicked(ActionEvent event) 
    {
        
    }
    
}
