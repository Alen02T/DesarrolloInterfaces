/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenLogin;

import examenturnitin.ExamenTurnitin;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alen
 */
public class FXMLLoginController implements Initializable {

    
    @FXML
    private TextField txtUser;
    
    @FXML
    private TextField txtPass;
    
    
    
    @FXML
    public void txtCredentials(KeyEvent event) throws Exception {
        
        try (InputStream input = FXMLLoginController.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            //System.out.println(prop.getProperty("dbpassword"));
            //System.out.println(prop.getProperty("dbuser"));
           //System.out.println(prop.getProperty("db.password"));


        if(txtUser.getText().equals(prop.getProperty("dbuser")) && txtPass.getText().equals(prop.getProperty("dbpassword"))){
                FXMLLoader loader = new FXMLLoader(ExamenTurnitin.class.getResource("/examenAdmin/FXMLAdmin.fxml"));

                Parent root = loader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Administrador");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                
                ((Node)(event.getSource())).getScene().getWindow().hide();
           }
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
            


    }
    
    
    @FXML
    private void User(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ExamenTurnitin.class.getResource("/examenturnitin/FXMLDocument.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Inicia Sesión");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

//        Constants.Inicio=stage;
//        Constants.Main.close();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }


         public void conectar(){
                 try (InputStream input = FXMLLoginController.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            System.out.println(prop.getProperty("dbpassword"));
            System.out.println(prop.getProperty("dbuser"));
           //System.out.println(prop.getProperty("db.password"));
                     

           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         }  
         
}

