/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessit;

import static accessit.ACCESSit.window;
import static java.awt.Color.red;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class SignUpWindowController implements Initializable {

    private boolean isUsernameAvailable;
    
    @FXML
    private Button checkUsernameAvailabilityButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField confirmPasswordTextField;
    private TextField passworddTextField;
    @FXML
    private Button sendEmailButton;
    @FXML
    private TextField oTPCodeTextField;
    @FXML
    private Button submitButton;
    @FXML
    private CheckBox termsAndConditionsCheckBox;
    @FXML
    private Button backButton;
    @FXML
    private Button helpPasswordButton;
    @FXML
    private Button troubleEmailButton;
    @FXML
    private Label availableLabel;
    @FXML
    private Label invalidLabel;
    @FXML
    private PasswordField passwordTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onCheckUsernameAvailabilityButtonClicked(ActionEvent event) throws IOException 
    {
        if(!this.usernameTextField.getText().isEmpty())
        {
            ClientForCommunication.communicate();
            boolean b = ClientForCommunication.checkUsernameCommunication(this.usernameTextField.getText());
            if(b)
            {
                this.availableLabel.setText("Available");
                this.availableLabel.setTextFill(Paint.valueOf("Green"));
                this.availableLabel.setVisible(true);
                isUsernameAvailable = true;
            }
            else
            {
                this.availableLabel.setText("Entered Username isn't available.");
                this.availableLabel.setTextFill(Paint.valueOf("Red"));
                this.availableLabel.setVisible(true);
                isUsernameAvailable = false;
            }
        }
    }

    @FXML
    private void onSendEmailButtonClicked(ActionEvent event) throws IOException
    {
        if(!this.passwordTextField.getText().equals(this.confirmPasswordTextField.getText()))
        {
            this.invalidLabel.setText("Passwords do not match!");
            this.invalidLabel.setTextFill(Paint.valueOf("Red"));
            this.invalidLabel.setVisible(true);
            this.passwordTextField.setStyle("-fx-border-color: red ;");
            this.confirmPasswordTextField.setStyle("-fx-border-color: red ;");
        }
        else if(isUsernameAvailable)
        {            
            ClientForCommunication.communicate();
            boolean flag = ClientForCommunication.sendMail(this.emailTextField.getText());

            if(flag)
            {
                this.invalidLabel.setText("OTP sent!");
                this.invalidLabel.setTextFill(Paint.valueOf("Green"));
                this.invalidLabel.setVisible(true);            
            }
            else
            {
                this.invalidLabel.setText("Sorry cannot send OTP");
                this.invalidLabel.setTextFill(Paint.valueOf("Red"));
                this.invalidLabel.setVisible(true);
            }
        }
        else
        {
            this.invalidLabel.setText("Enter available username!!!");
            this.invalidLabel.setTextFill(Paint.valueOf("Black"));
            this.invalidLabel.setVisible(true);
        }
    }

    @FXML
    private void onSubmitButtonClicked(ActionEvent event) throws IOException, NoSuchAlgorithmException 
    {
        if(!this.passwordTextField.getText().equals(this.confirmPasswordTextField.getText()))
        {
            this.invalidLabel.setText("Passwords do not match!");
            this.invalidLabel.setTextFill(Paint.valueOf("Red"));
            this.invalidLabel.setVisible(true);
            this.passwordTextField.setStyle("-fx-border-color: red ;");
            this.confirmPasswordTextField.setStyle("-fx-border-color: red ;");
        }
        else if(this.firstNameTextField.getText().isEmpty()) 
        {
            this.availableLabel.setText("Marked field is invalid or cannot be empty.");
            this.availableLabel.setTextFill(Paint.valueOf("Red"));    
            this.availableLabel.setVisible(true);
            this.firstNameTextField.setStyle("-fx-border-color: red ;");
        }
        else if(this.lastNameTextField.getText().isEmpty())
        {
            this.availableLabel.setText("Marked field is invalid or cannot be empty.");
            this.availableLabel.setTextFill(Paint.valueOf("Red"));   
            this.availableLabel.setVisible(true);
            this.lastNameTextField.setStyle("-fx-border-color: red ;");
        }
        else if(this.emailTextField.getText().isEmpty())
        {
            this.availableLabel.setText("Marked field is invalid or cannot be empty.");
            this.availableLabel.setTextFill(Paint.valueOf("Red"));    
            this.availableLabel.setVisible(true);
            this.emailTextField.setStyle("-fx-border-color: red ;");
        }
        else if(this.usernameTextField.getText().isEmpty())
        {
            this.availableLabel.setText("Marked field is invalid or cannot be empty.");
            this.availableLabel.setTextFill(Paint.valueOf("Red"));  
            this.availableLabel.setVisible(true);
            this.usernameTextField.setStyle("-fx-border-color: red ;");
        }
        else if(isUsernameAvailable)
        {
            this.invalidLabel.setText("Enter available username!!!");
            this.invalidLabel.setTextFill(Paint.valueOf("Black"));
            this.invalidLabel.setVisible(true);            
        }
        else
        {
            ClientForCommunication.communicate();
            boolean flag = ClientForCommunication.signUpCommunication(this.firstNameTextField.getText(),this.lastNameTextField.getText(),this.emailTextField.getText(),this.usernameTextField.getText(),this.passwordTextField.getText(),Integer.parseInt(this.oTPCodeTextField.getText()));
         
            if(flag)
            {
                ACCESSit.showStage();
                LogInWindowController.closeStage();
            }
            else
            {
                this.availableLabel.setText("Sorry but our server is too busy!");
                this.availableLabel.setTextFill(Paint.valueOf("Red"));
            }
        }
    }

    @FXML
    private void onTermsAndConditionsBoxActtion(ActionEvent event) {
        this.submitButton.setDisable(!this.termsAndConditionsCheckBox.isSelected());
    }

    @FXML
    private void onBackButtonClicked(ActionEvent event) throws IOException 
    {
        //Shows home page
        ACCESSit.showStage();
        //Closes Sign Up Window
        LogInWindowController.closeStage();
    }

    @FXML
    private void onHelpPasswordButtonClicked(ActionEvent event) {
    }

    @FXML
    private void onTroubleButtonClicked(ActionEvent event) 
    {
        
    }
}
