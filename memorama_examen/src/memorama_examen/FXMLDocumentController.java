/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorama_examen;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author rjalamanac
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    TilePane tPane;

    @FXML
    Label tLives;

    @FXML
    Label tTime;

    private Image lastBackgrounImageButton;

    private Button lastClickedButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DateFormat timeFormat = new SimpleDateFormat("dd:MM:YYYY HH:mm:ss");
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> {
                            tTime.setText("Tiempo: " + timeFormat.format(System.currentTimeMillis()));
                        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();

        Image imageBlue = new LocatedImage(Memorama_examen.class.getResource("resources/blue.png").toString());
        Image imageRed = new LocatedImage(Memorama_examen.class.getResource("resources/red.png").toString());

        String imageBlueURL = ((LocatedImage) imageBlue).getURL();
        String imageRedURL = ((LocatedImage) imageRed).getURL();

        Image imageCero = new LocatedImage(Memorama_examen.class.getResource("resources/0.png").toString());
        Image imageOne = new LocatedImage(Memorama_examen.class.getResource("resources/1.jpg").toString());
        Image imageTwo = new LocatedImage(Memorama_examen.class.getResource("resources/2.jpg").toString());
        Image imageThree = new LocatedImage(Memorama_examen.class.getResource("resources/3.jpg").toString());
        Image imageFour = new LocatedImage(Memorama_examen.class.getResource("resources/4.jpg").toString());

        List<Image> imagenesNumeros = new ArrayList<>();

        imagenesNumeros.add(imageCero);

        imagenesNumeros.add(imageOne);

        imagenesNumeros.add(imageTwo);

        imagenesNumeros.add(imageThree);

        imagenesNumeros.add(imageFour);

        imagenesNumeros.add(imageCero);

        imagenesNumeros.add(imageOne);

        imagenesNumeros.add(imageTwo);

        imagenesNumeros.add(imageThree);

        imagenesNumeros.add(imageFour);

        Collections.shuffle(imagenesNumeros);

        tPane.setVgap(
                10);
        tPane.setHgap(
                10);

        for (int i = 0;
                i < 10; i++) {
            Button button = new Button();

            ImageView imageView = new ImageView();
            imageView.setImage(i % 2 == 0 ? imageBlue : imageRed);
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            button.setGraphic(imageView);
            button.setMaxHeight(60);
            button.setMaxWidth(60);
            button.setMinHeight(60);
            button.setMinWidth(60);
            tPane.getChildren().addAll(button);
            final Integer integer = i;

            button.setOnMouseClicked((MouseEvent event) -> {
                if (!event.getButton().equals(MouseButton.PRIMARY)) {
                    return;
                }

                if (button == lastClickedButton) {
                    ImageView image = (ImageView) button.getGraphic();
                    image.setImage(lastBackgrounImageButton);
                    button.setGraphic(image);
                }

                if (event.getClickCount() == 2) {
                    return;
                }
                ImageView currentImageView = (ImageView) button.getGraphic();

                if (currentImageView.getImage() != imageBlue && currentImageView.getImage() != imageRed) {
                    return;
                }

                if (lastClickedButton == null && lastBackgrounImageButton == null) {
                    currentImageView.setImage(imagenesNumeros.get(integer));
                    currentImageView.setFitHeight(60);
                    currentImageView.setFitWidth(60);
                    button.setGraphic(currentImageView);

                    if (integer % 2 == 0) {
                        lastBackgrounImageButton = imageBlue;
                    } else {
                        lastBackgrounImageButton = imageRed;
                    }
                    lastClickedButton = button;
                    return;
                }

                currentImageView.setImage(imagenesNumeros.get(integer));
                currentImageView.setFitHeight(60);
                currentImageView.setFitWidth(60);
                button.setGraphic(currentImageView);

                ImageView lastCickedImageView = ((ImageView) lastClickedButton.getGraphic());

                String lastURL = ((LocatedImage) lastCickedImageView.getImage()).getURL();
                String currentURL = ((LocatedImage) currentImageView.getImage()).getURL();

                if (!lastURL.equals(currentURL)) {
                    String[] textoSplitted = tLives.getText().split(" ");
                    Integer lives = Integer.parseInt(textoSplitted[textoSplitted.length - 1]) - 1;
                    if (lives <= 0) {
                        Platform.exit();
                    }
                    tLives.setText("Lives: " + lives);
                    currentImageView.setImage(integer % 2 == 0 ? imageBlue : imageRed);
                    currentImageView.setFitHeight(60);
                    currentImageView.setFitWidth(60);
                    button.setGraphic(currentImageView);

                    ImageView image = (ImageView) lastClickedButton.getGraphic();
                    image.setImage(lastBackgrounImageButton);
                    lastClickedButton.setGraphic(image);
                }
                lastBackgrounImageButton = null;
                lastClickedButton = null;

                for (Node node : tPane.getChildren()) {

                    if (node instanceof Button) {
                        ImageView view = (ImageView) ((Button) node).getGraphic();
                        String auxURL = ((LocatedImage) view.getImage()).getURL();
                        if (auxURL.equals(imageBlueURL) || auxURL.equals(imageRedURL)) {

                            return;
                        }
                    }
                }
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Has ganado"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();

            });

        }

    }

}
