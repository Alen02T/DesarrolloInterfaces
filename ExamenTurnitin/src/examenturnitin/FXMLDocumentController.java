/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenturnitin;

import examenCommon.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alen
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML 
    private ComboBox comboBox1;
    
    
    @FXML 
    private ComboBox comboBox2;
    
    @FXML
    private TextArea textArea1;
    
    @FXML
    private TextArea textArea2;
    
    @FXML 
    private Label porcentaje;
    
    @FXML 
    private Button btnCheck;
    
    @FXML
    private Button btnReset;
    
    
    @FXML
    private void Login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ExamenTurnitin.class.getResource("/examenLogin/FXMLLogin.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Inicia Sesión");
        stage.setScene(new Scene(root));
        stage.show();

//        Constants.Inicio=stage;
//        Constants.Main.close();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        File path = new File(Constants.path);
        File[] archivos = path.listFiles();
        
        for(int i=0;i<archivos.length;i++){
            comboBox1.getItems().add(archivos[i].getName());
            comboBox2.getItems().add(archivos[i].getName());
        }
        combo1();
        combo2();
        
    }    
    
    
   public void lanzarCheck(MouseEvent event){
       if(textArea1.getText().equals("") && textArea2.getText().equals("")){
           event.consume();
       }else{
           textArea1.setDisable(true);
           textArea2.setDisable(true);
           //compareStringsPercentage(textArea1.getText(), textArea2.getText());
           int porcentajeNumero = compareStringsPercentage(textArea1.getText(), textArea2.getText());
           String porcentajeTexto =  String.valueOf(porcentajeNumero);    
           porcentaje.setText(porcentajeTexto+"%");
       }
   }
   
    public void lanzarReset(MouseEvent event){
           
           textArea1.setDisable(false);
           textArea2.setDisable(false);
           textArea1.clear();
           textArea2.clear();
           comboBox1.getSelectionModel().clearSelection();
           comboBox2.getSelectionModel().clearSelection();
           porcentaje.setText("");
       
   
   }
    
    public void combo1(){
        comboBox1.setOnAction((event) -> {
            //int selectedIndex = comboBox1.getSelectionModel().getSelectedIndex();
            //Object selectedItem = comboBox1.getSelectionModel().getSelectedItem();

            //System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            //System.out.println("   ComboBox.getValue(): " + comboBox1.getValue());
       
                  try(BufferedReader br = new BufferedReader(new FileReader(Constants.path+"/"+comboBox1.getValue()))) {
                                    StringBuilder sb = new StringBuilder();
                                    String line = br.readLine();

                                    while (line != null) {
                                        sb.append(line);
                                        sb.append(System.lineSeparator());
                                        line = br.readLine();
                                    }
                                    
                                    
                                    
                                    String everything = sb.toString();
                                    textArea1.setText(everything);
                                    
                                } catch (IOException ex) {
                                    //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
                                }
        
        });
        
    
    
    
    }
    
    
        public void combo2(){
        comboBox2.setOnAction((event) -> {
            //int selectedIndex = comboBox1.getSelectionModel().getSelectedIndex();
            //Object selectedItem = comboBox1.getSelectionModel().getSelectedItem();

            //System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            //System.out.println("   ComboBox.getValue(): " + comboBox1.getValue());
       
                  try(BufferedReader br = new BufferedReader(new FileReader(Constants.path+"/"+comboBox2.getValue()))) {
                                    StringBuilder sb = new StringBuilder();
                                    String line = br.readLine();

                                    while (line != null) {
                                        sb.append(line);
                                        sb.append(System.lineSeparator());
                                        line = br.readLine();
                                    }
                                    
                                    
                                    
                                    String everything = sb.toString();
                                    textArea2.setText(everything);
                                    
                                } catch (IOException ex) {
                                    //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
                                }
        
        });
        
    
    
    
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
    
    
}
