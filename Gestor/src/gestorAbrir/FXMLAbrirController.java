/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorAbrir;

import Common.Constants;
import gestor.Gestor;
import gestorMenu.FXMLMenuController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alen
 */
public class FXMLAbrirController implements Initializable {
    
    //Este codigo de aqui sirve para poder identificar mas tarde y asignarle la ventana correspondiente
    public static int CodigoVentana = 0 ; 
    
    
    @FXML
    private TilePane tlPane;
    public Stage MainStage;
    public TextArea MainTextArea;
    
    public static TextArea textArea;
    
    @FXML
    public ImageView atras;
    
//    @FXML
//    private Pane tPain;
    
//     public void setMainText(TextArea textArea) {
//        //MainStage = stg;
//        MainTextArea = textArea;
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        dibujar();
        
        
    }
    
    

   
    
    
    public void dibujar(){
        
        
        tlPane.getChildren().clear();
           
        File path = new File(Constants.path);
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
                    AñadirDelete(borde,texto.getText());
                    tlPane.getChildren().addAll(borde);
                    
                    borde.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 2){
                                
                                
                               
                                Constants.currentFolder = texto.getText();
                                
                                Constants.path = Constants.path + "/" + Constants.currentFolder;
                                System.out.println(Constants.path);
                               
                                File path = new File(Constants.path);
                                ((Stage) borde.getScene().getWindow()).setTitle(Constants.path); // cambiar titulo con al nuevo path                }
                                dibujar();
                               
                                
//                                Stage stage = (Stage) borde.getScene().getWindow();
//                                // do what you have to do
//                                stage.close();
                            }
                        }
                    }
    });
                    
                    
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
                    AñadirDelete(borde,texto.getText());
                    tlPane.getChildren().addAll(borde);
               
                    
                    
                  borde.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 2){
                                System.out.println(texto.getText());
                                
                                
                                try(BufferedReader br = new BufferedReader(new FileReader(path+"/"+texto.getText()))) {
                                    StringBuilder sb = new StringBuilder();
                                    String line = br.readLine();

                                    while (line != null) {
                                        sb.append(line);
                                        sb.append(System.lineSeparator());
                                        line = br.readLine();
                                    }
                                    
                                    
                                    
                                    String everything = sb.toString();
                                    System.out.println(everything);
                                    //MainTextArea.setText(everything);
                                    //MainStage.setTitle(texto.getText());
                                    Constants.Main.setTitle(texto.getText());
                                    //Constants.TextArea=everything;
                                    
                                    textArea.setText(everything);
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                               
                                Stage stage = (Stage) borde.getScene().getWindow();
                                // do what you have to do
                                stage.close();
                            }
                        }
                    }
    });
               }
            
                
           }
        
        atras.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 1){
                                
                               
                               
                                if(Constants.path.equals(Constants.PATH_INICIAL) ){
                                   Stage stage = (Stage) tlPane.getScene().getWindow();
                                // do what you have to do
                                stage.close();
                                    
                                }else{    
                                    Constants.path = Constants.path.subSequence(0, Constants.path.lastIndexOf("/")).toString();
                                    ((Stage) atras.getScene().getWindow()).setTitle(Constants.path); // cambiar titulo con al nuevo path                }
                                    
                                    
                                    dibujar();
                                }

                            }
                        }
                    }
    });
        
        
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItem1 = new MenuItem("Crear Archivo");
        MenuItem menuItem2 = new MenuItem("Crear Carpeta");
        
        contextMenu.getItems().addAll(menuItem1,menuItem2);
        
      
        
        menuItem1.setOnAction(event ->{
        CodigoVentana=1;
          try{
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestorMenu/menu_contextual.fxml"));
              FXMLMenuController c = (FXMLMenuController) loader.getController();
              Parent root = loader.load();
              Stage stage = new Stage();
              c.recibir(this);
              
              //stage.initModality(Modality.APPLICATION_MODAL);
              stage.setScene(new Scene(root));
              stage.show();
          }catch(Exception a){
             a.printStackTrace();
          }
          });
        
        
        
     
        
        
        //Accion del menuItem2 (Crear Menu para la creacion de directorios)
        menuItem2.setOnAction(event ->{
        CodigoVentana=2;
          try{
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestorMenu/menu_contextual.fxml"));
              FXMLMenuController c = (FXMLMenuController) loader.getController(); //carga el controlador c
              Parent root = loader.load();
              Stage stage = new Stage();
              c.recibir(this);
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
  
       
            tlPane.setOnContextMenuRequested((ContextMenuEvent a) -> {
               contextMenu.show(tlPane,a.getScreenX(),a.getScreenY());
            });
    
            
            
    }



   private void AñadirDelete(BorderPane borde,String texto) {
        borde.setOnContextMenuRequested((ContextMenuEvent e)
                -> {
            ContextMenu contextMenu = new ContextMenu();
            MenuItem delete = new MenuItem("Delete");

          
            delete.setOnAction((ActionEvent event) -> {
                //Que es el setManaged?
                borde.setManaged(false);
                tlPane.getChildren().remove(borde);
                
                //BorrarArchivoDirectorio(texto);
                File archivo = new File(Constants.path+"/"+texto);
                BorrarContenidos(archivo);
                System.out.println(archivo);
            });

            contextMenu.getItems().addAll(delete);
            contextMenu.show(borde, e.getScreenX(), e.getScreenY());
            e.consume();
        });
    }
    
//    public static void borrarElementosRecursivo(File dir) {
//        File[] archivos = dir.listFiles();
//        if (archivos != null) {
//            for (File temp : archivos) {
//                borrarElementosRecursivo(temp);
//            }
//        }
//        dir.delete();
//    }
    
   public static void BorrarContenidos(File element) {
    File[] contenido  = element.listFiles();
    if (element.isDirectory()) {
        for (File sub : element.listFiles()) {
            BorrarContenidos(sub);
        }
    }
    element.delete();
}
   
   
   public static void recibirTextArea(TextArea txtArea){
        textArea = txtArea;
        
   }
   
   
    
//   private void BorrarArchivoDirectorio(String texto){
//       String fileName = Constants.path + "/" + texto; 
//       //System.out.println(fileName);       
//       
//        try {
//            boolean result = Files.deleteIfExists(Paths.get(fileName));
//            if (result) {
//                System.out.println("File is deleted!");
//            } else {
//                System.out.println("Sorry, unable to delete the file.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//   
//   }
   
}
