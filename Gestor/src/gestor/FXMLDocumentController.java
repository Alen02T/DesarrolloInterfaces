/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alen
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestorAbout/about.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Funcion para abrir el menu abrir
    @FXML 
    private void MenuAbrir(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestorAbrir/abrir.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
    }
    }
    
    
    
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
