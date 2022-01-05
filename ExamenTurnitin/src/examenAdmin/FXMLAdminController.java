/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenAdmin;

import examenCommon.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    private TilePane tlPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tlPane.getChildren().clear();
        
        
        
        
        File path = new File(Constants.path);
        File[] archivos = path.listFiles();
        
        
         
        for(int i=0;i<archivos.length;i++){
            int numeroTotal=0;
            //System.out.println(archivos[i].getName());
            String archivo1 = leer(archivos[i].getName());
             
             for(int j=0;j<archivos.length;j++){
                
                 if(j!=i){
                    String archivo2 = leer(archivos[j].getName());
                    int comparacionNumerica = compareStringsPercentage(archivo1, archivo2);
                    System.out.println("Comparacion entre " + archivos[i].getName() + " " + archivos[j].getName() + " = " + comparacionNumerica);
                    numeroTotal+=comparacionNumerica;  
                 }
             }
             
            int  numeroFinal = numeroTotal/(archivos.length-1);
            String numeroFinalTexto = String.valueOf(numeroFinal);
            
            
            Text texto = new Text();
            texto.setText(archivos[i].getName());
            Text texto2 = new Text();
            texto2.setText(numeroFinalTexto+"%");
            BorderPane borde = new BorderPane();
            borde.setCenter(texto2);
            borde.setBottom(texto);
            borde.setMargin(borde,new Insets(200,50,50,50));
            
            if(numeroFinal>45){
                texto2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                texto2.setFill(Color.RED);
            }

            


            tlPane.getChildren().addAll(borde);
        
            
                    borde.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 2){
                                final Stage dialog = new Stage();
                                dialog.initModality(Modality.WINDOW_MODAL);
                                //Stage primaryStage = (Stage) fieldJugadorUno.getScene().getWindow();
                                //dialog.initOwner(primaryStage);
                                VBox dialogVbox = new VBox(20);
                                dialogVbox.getChildren().add(new Text("Propiedades del archivo"));
                                dialogVbox.getChildren().add(new Text("Nombre del archivo: " + texto.getText()));
                                dialogVbox.getChildren().add(new Text("Nombre del propietario: " + Constants.User));
                                dialogVbox.getChildren().add(new Text("Porcentaje Total de Similaridad: " + texto2.getText()));
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
    
    
    
    
    
       public static int compareStringsPercentage(String s1, String s2) {
        int percentage = 0;

        int total = 0;
        int fullMatch = 0;
        // Check for each character at same location
        total += charMatch(s1, s2);
        fullMatch += charMatch(s1, s1);

        // Calc percentage
        percentage = (int) Math.round(total / (fullMatch / 100.0));
        return percentage;
    }

    /**
     * Check how many characters of string1 are in the same location as string2
     * @param s1
     * @param s2
     * @return
     */
    private static int charMatch(String s1, String s2) {
        char[] as1 = s1.toCharArray();
        char[] as2 = s2.toCharArray();
        int match = 0;
        for (int i = 0; i < as1.length; i++) {
            char c = as1[i];
            if (i < as2.length) {
                if (as2[i] == c) {
                    match++;
                }
            }

        }
        return match;
    }
    
    
    public static String leer(String nombre){
    
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
                                    //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
                                    
                                }
       return null; 
     
    }
}

