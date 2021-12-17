/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;


import Common.Constants;
import gestorAbrir.FXMLAbrirController;
import static gestorAbrir.FXMLAbrirController.CodigoVentana;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author alen
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    public TextArea textArea; // el original
//    @FXML
//    public Stage MainStage; //Stage Principal
//    
//    public void setMainStage(Stage stg) {
//        MainStage = stg;
//    }
//
//    public TextArea getTextArea() {
//        return textArea;
//    }
    
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
        Constants.path=Constants.PATH_INICIAL;
       
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestorAbrir/abrir.fxml"));
            Parent root = loader.load();
            
            //Aqui cargo el controller de abrirController para que la pestaña reciba el MainStage y el textArea
            //FXMLAbrirController ac = (FXMLAbrirController) loader.getController();
           // ac.setMainStage(MainStage, textArea);
            //ac.setMainStage(textArea);
           
            
            
            
            
            Stage stage = new Stage();
            stage.setTitle(Constants.path);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
    }
    }
    
    
    @FXML
    public void MenuGuardar(ActionEvent event) throws IOException{
       CodigoVentana=1;
       
       File archivo =new File(Constants.path+"/"+Constants.Main.getTitle());
       
       
       if(archivo.exists()){
           //System.out.println(MainStage.getTitle());
           Constants.TextArea = textArea.getText();
           
           File myObj = new File(Constants.path +"/"+Constants.Main.getTitle());
           System.out.println(Constants.path +"/"+Constants.Main.getTitle());
           FileWriter myWriter = new FileWriter(myObj);
           myWriter.write(Constants.TextArea);
           myWriter.close();
          
       }else{ 
            try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestorMenu/menu_contextual.fxml"));
                 Parent root = loader.load();
                 Stage stage = new Stage();
                 stage.initModality(Modality.APPLICATION_MODAL);
                 Constants.TextArea = textArea.getText();
                 stage.setScene(new Scene(root));
                 stage.show();
             } catch (Exception e) {
                 e.printStackTrace();
         }
       }
  
      } 
           
    
    
    @FXML
    public void MenuBorrar(ActionEvent event){
        File archivo =new File(Constants.path+"/"+Constants.Main.getTitle());
        if(archivo.exists()){
            archivo.delete();
            textArea.setText("");
            ((Stage) textArea.getScene().getWindow()).setTitle("NUEVO ARCHIVO");
        }

//System.out.println(MainStage.getTitle());
    }
    
    
   
    @FXML
    public void MenuNewFile(ActionEvent event) {
        textArea.setText("");
        ((Stage) textArea.getScene().getWindow()).setTitle("NUEVO ARCHIVO");
    }
    
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      FXMLAbrirController.recibirTextArea(textArea);
    }
    
    
   
            
  
            
            

}
