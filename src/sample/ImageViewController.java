package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImageViewController implements Initializable {


    @FXML
    public ImageView fullSizeImage;
    @FXML
    private ImageView backImage;

    public static BufferedImage bufferedImage;

    @FXML
    private void backButtonPushed(ActionEvent e)throws IOException
    {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Image add.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Check the images");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        ImageViewer imageViewer = new ImageViewer();
//        BufferedImage bufferedImage = imageViewer.getBufferedImage();
//        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
//        fullSizeImage.setImage(image);
        Image i = SwingFXUtils.toFXImage(bufferedImage,null);
        fullSizeImage.setImage(i);
    }
}

