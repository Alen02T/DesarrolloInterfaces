/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorAbout;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author alen
 */
public class about implements Initializable {
    
    @FXML
    private ImageView imgViewAbout;
    @FXML
    private Pane pnlAbout;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgViewAbout.fitWidthProperty().bind(pnlAbout.widthProperty());
        imgViewAbout.fitHeightProperty().bind(pnlAbout.heightProperty());
    }    
    
}
