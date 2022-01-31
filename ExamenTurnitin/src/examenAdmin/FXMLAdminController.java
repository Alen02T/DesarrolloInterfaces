/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenAdmin;

import examenCommon.Constants;
import examenturnitin.ExamenTurnitin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author alen
 */
public class FXMLAdminController implements Initializable {

    
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
    
    
    @FXML
    private TilePane tlPane;
    
    
    public void dibujar(){
        File path = new File(Constants.path);
        File[] archivos = path.listFiles();
        
        for(int i=0;i<archivos.length;i++){
            
            
            String archivo1 = leerArchivo(archivos[i].getName());
            
            int suma=0;
            
            for(int j=0;j<archivos.length;j++){
                if(i!=j){
                    String archivo2 = leerArchivo(archivos[j].getName());   
                    int resultado = Constants.compareStringsPercentage(archivo1, archivo2);
                    suma = suma + resultado;   
                }
            }
                
            int resultadoTotal = suma/(archivos.length-1);
            Text texto = new Text();
            texto.setText(archivos[i].getName());
            Text texto2 = new Text();
            
            if(resultadoTotal>60){
                
                texto2.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,30));
                texto2.setFill(Color.RED);
                texto2.setText(resultadoTotal+"%");
            }else{
                texto2.setText(resultadoTotal+"%");
            }
            
            BorderPane borde = new BorderPane();
            borde.setCenter(texto2);
            borde.setBottom(texto);
            borde.setStyle("-fx-border-color:black");
            borde.setPadding(new Insets(10, 20, 10, 20));
            tlPane.getChildren().addAll(borde);
            
            borde.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 2){
                                
                              String nombreSeleccionado=texto.getText();
                              System.out.println(nombreSeleccionado);
                           
                                       String archivo1 = leerArchivo(nombreSeleccionado);

                                       

                                       for(int j=0;j<archivos.length;j++){
                                           if(!nombreSeleccionado.equals(archivos[j].getName())){
                                               System.out.println("Archivos no seleccionados: " + archivos[j].getName());
                                               String archivo2 = leerArchivo(archivos[j].getName());   
                                               int resultado = Constants.compareStringsPercentage(archivo1, archivo2);
                                               
                                               System.out.println(nombreSeleccionado + " vs " + archivos[j].getName() + " = " + resultado);
                                               
                                               if(resultado>Constants.valorMasAlto){
                                                   
                                                   Constants.valorMasAlto=resultado;
                                                   Constants.nombreArchivo=archivos[j].getName();
                                               }
                                           }
                                       }
                            
                            
                                       final Stage dialog = new Stage();
                                        dialog.initModality(Modality.WINDOW_MODAL);
                                        VBox dialogVbox = new VBox(20);
                                        dialogVbox.getChildren().add(new Text("Propiedades del archivo"));
                                        dialogVbox.getChildren().add(new Text("Nombre del archivo: " +  Constants.nombreArchivo));
                                        dialogVbox.getChildren().add(new Text("Nombre del propietario: " + Constants.user));
                                        dialogVbox.getChildren().add(new Text("Porcentaje Total de Similaridad: " + Constants.valorMasAlto+"%"));
                                        Constants.valorMasAlto=0;
                                        Constants.nombreArchivo="";
                                        Scene dialogScene = new Scene(dialogVbox, 300, 200);
                                        dialog.setScene(dialogScene);
                                        dialog.show();
                                        return;      
                                       
                            }
                        }
                    }
                    
            });
            
            
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dibujar();
    }
    
    
       public static String leerArchivo(String nombre){
            
            
            try(BufferedReader br = new BufferedReader(new FileReader(Constants.path+"/"+nombre))) {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    String everything = sb.toString();
                    return everything;
                } catch (IOException ex) {
                   ex.printStackTrace();
                }
        return null;
            
          }

    
    
  
}

