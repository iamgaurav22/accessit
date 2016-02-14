/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessit;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class ACCESSit extends Application {
    
    static Stage window = null;    
    static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException{
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("LogInWindow.fxml"));
        scene = new Scene(root);
        showStage();
    }
    
    public static void showStage() throws IOException
    {        
        window.setScene(scene);
        window.show();
    }
    
    public static void closeStage()
    {
        window.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
