/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorAbrir;

import gestor.Gestor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alen
 */
public class FXMLAbrirController implements Initializable {
    
    //Posible funcion para posteriormente imprimir
    //private void imprimir(File fichero){
    //}
    
    
    //Este codigo de aqui sirve para poder identificar mas tarde y asignarle la ventana correspondiente
    public static int CodigoVentana = 0 ; 
    
    
    @FXML
    private TilePane tlPane;
    
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //tlPane.getChildren().clear();
           
        File path = new File("/home/alen/NetBeansProjects/Gestor/FILES");
        File[] archivos = path.listFiles();
        
        for(int i=0;i<archivos.length;i++){
               if(archivos[i].isDirectory()){
                    Text texto = new Text();
                    texto.setText(archivos[i].getName());
                    ImageView imageView = new ImageView();
                    Image imagE = new Image(Gestor.class.getResource("/gestorResources/directorio.png").toString());
                    imageView.setImage(imagE);
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    BorderPane borde = new BorderPane();
                    borde.setCenter(imageView);
                    borde.setBottom(texto);
                    
                    tlPane.getChildren().addAll(borde);
               }else{
                    Text texto = new Text();
                    texto.setText(archivos[i].getName());
                    ImageView imageView = new ImageView();
                    Image imagE = new Image(Gestor.class.getResource("/gestorResources/archivo.png").toString());
                    imageView.setImage(imagE);
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    BorderPane borde = new BorderPane();
                    borde.setCenter(imageView);
                    borde.setBottom(texto);
                    tlPane.getChildren().addAll(borde);
               }
            
                
           }
        
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItem1 = new MenuItem("Crear Archivo");
        MenuItem menuItem2 = new MenuItem("Crear Carpeta");
    
        contextMenu.getItems().addAll(menuItem1,menuItem2);
        
       
        
        
    //Esto es para el archivo
    menuItem1.setOnAction(event ->{
      CodigoVentana=1;
          try{
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestorMenu/menu_contextual.fxml"));
              Parent root = loader.load();
              Stage stage = new Stage();
              
              //stage.initModality(Modality.APPLICATION_MODAL);
              stage.setScene(new Scene(root));
              stage.show();
          }catch(Exception a){
             a.printStackTrace();
          }
    });
    
    //Esto es para el directorio
     menuItem2.setOnAction(event ->{
      CodigoVentana=2;
          try{
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestorMenu/menu_contextual.fxml"));
              Parent root = loader.load();
              Stage stage = new Stage();
              
              //stage.initModality(Modality.APPLICATION_MODAL);
              stage.setScene(new Scene(root));
              stage.show();
          }catch(Exception a){
             a.printStackTrace();
          }
    });
    
        tlPane.setOnMousePressed((event) -> {
                if (contextMenu.isShowing()) {
                    contextMenu.hide();
                }
            });
  
        //Esto es para esconder el contextMenu 
        //contextMenu.hide();
 tlPane.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(tlPane,e.getScreenX(),e.getScreenY());
         });
        
    
        
    }
    
    

}
