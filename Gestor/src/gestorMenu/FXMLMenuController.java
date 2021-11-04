/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorMenu;

import gestorAbrir.FXMLAbrirController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author alen
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Button btnCreate;
    
    @FXML 
    private Button btnCancel;
    
    @FXML
    private TextField txtValue;
    
    
    @FXML 
    private Text textCreate;
    private static FXMLAbrirController aController;
    

    @FXML
    public void botonDisable(){
        btnCreate.setDisable(true);
    
    }
    
    @FXML
    public void botonEnable(){
        btnCreate.setDisable(false);
    
    }
    
    @FXML
    public void cerrar(MouseEvent event){
        //Enlaza el el stage que creamos con el que queremos cerrar
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        //cierra el stage
        stage.close();
        
    
    }
    
    
    
    
    
   
            
    @FXML
    public void cargarMenuFile(){
        if(gestorAbrir.FXMLAbrirController.CodigoVentana==1){
            textCreate.setText("Create File");
            
            
        }
     }
    @FXML
    public void cargarMenuDirectory(){
       
        if(gestorAbrir.FXMLAbrirController.CodigoVentana==2){
            textCreate.setText("Create Directory");
        }
    }
    
    
    @FXML
    private void valores(KeyEvent event) {
        if(event.getCharacter().matches("[0-9]+")){
            event.consume();
        }
    }
    
     @FXML
    private void valoresReleased(KeyEvent event) {
        String texto = String.valueOf(txtValue.getCharacters());
        if(texto.equals("")){
            botonDisable();
        }else{
            botonEnable();
        }
    }
    
    @FXML
    private void onCreate(MouseEvent event){
        
      
        
       if(gestorAbrir.FXMLAbrirController.CodigoVentana==1){    
        //Utilizo un try y un catch para crear el archivo  
            
            try{
                Path ruta = Paths.get("/home/alen/NetBeansProjects/Gestor/FILES/"+txtValue.getText()+".txt");
                //String fileName = "/home/alen/NetBeansProjects/Gestor/FILES/"+texto;
                //Files.createDirectories(fileName);
                Files.createFile(ruta);
                aController.dibujar();
                System.out.println("Se ha creado el archivo ");
                   
                
                //Las dos lineas de abajo cierran el panel 
                    Stage stage = (Stage) btnCancel.getScene().getWindow();
                    //cierra el stage

                    //stage.setOnCloseRequest();
                    
                    stage.close();
            
                    
                    
            }catch(IOException e){
                e.printStackTrace();
            }
            
       }else{
           //Utilizo un try y un catch para crear el archivo  
           try{
                Path ruta = Paths.get("/home/alen/NetBeansProjects/Gestor/FILES/"+txtValue.getText());
                //String fileName = "/home/alen/NetBeansProjects/Gestor/FILES/"+texto;
                Files.createDirectories(ruta);
                //Files.createFile(ruta);
                System.out.println("Se ha creado la carpeta ");
                aController.dibujar();
                
               //Las dos lineas de abajo cierran el panel 
                    Stage stage = (Stage) btnCancel.getScene().getWindow();
                    //cierra el stage
                    stage.close();
            
            }catch(IOException e){
                e.printStackTrace();
            }
           
       }
           
       
    }
    
    public static void recibir(FXMLAbrirController controlador){
        aController=controlador;
    }
    
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      cargarMenuDirectory();
      cargarMenuFile();

       
    }

}
