package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label headLabel;
    @FXML
    private Button newPatientAddButton;
    @FXML
    private Button oldPatientViewButton;
    @FXML
    private Button addImageButton;

    public void newPatientButtonPushed(ActionEvent e) throws IOException
    {

            Parent scene2Parent = FXMLLoader.load(getClass().getResource("Page 2.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("Add the details of new patient");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();

    }
    public void oldPatientButtonPushed(ActionEvent e) throws IOException
    {

        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Old patient.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Add the details of the old patient");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();

    }
    public void addImageButtonPushed(ActionEvent e) throws IOException
    {

        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Image add.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Add the image of patient");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
}
