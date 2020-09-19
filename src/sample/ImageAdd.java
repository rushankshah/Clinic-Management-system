package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ImageAdd implements Initializable {
    @FXML
    private AutocompletionlTextField name;
    @FXML
    private AutocompletionlTextField number;
    @FXML
    private ImageView patientImage;
    @FXML
    private ImageView patientImage2;
    @FXML
    private ImageView patientImage3;
    @FXML
    private ImageView patientImage4;
    @FXML
    private ImageView patientImage5;
    @FXML
    private ImageView patientImage6;
    @FXML
    private ImageView patientImage7;
    @FXML
    private ImageView patientImage8;
    @FXML
    private ImageView patientImage9;
    @FXML
    private ImageView patientImage10;
    @FXML
    private ImageView patientImage11;
    @FXML
    private ImageView patientImage12;
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Label errorLabel;




    int i = 0;
    int j;
    int count = -1;
    int patientID;



    public void backButtonPushed(ActionEvent e) throws IOException {


        while (j>0)
        {
            String path2 = "D:/Image " + j + ".jpg";
            File f = new File(path2);
            f.delete();
            j--;
        }
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Home Page");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();
    }

    public void fullImageButtonPushed(ActionEvent e) throws IOException {
        if (count == 0) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed2(ActionEvent e) throws IOException {
        if (count == 1) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage2.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 1) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage2.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed3(ActionEvent e) throws IOException {
        if (count == 2) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 2) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed4(ActionEvent e) throws IOException {
        if (count == 3) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 3) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed5(ActionEvent e) throws IOException {
        if (count == 4) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 4) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed6(ActionEvent e) throws IOException {
        if (count == 5) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 5) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed7(ActionEvent e) throws IOException {
        if (count == 6) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 6) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed8(ActionEvent e) throws IOException {
        if (count == 7) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 7) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed9(ActionEvent e) throws IOException {
        if (count == 8) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 8) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed10(ActionEvent e) throws IOException {
        if (count == 9) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 9) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed11(ActionEvent e) throws IOException {
        if (count == 10) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 10) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    public void fullImageButtonPushed12(ActionEvent e) throws IOException {
        if (count == 11) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the new Image");

            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if (!userDirectory.canRead())
                userDirectory = new File("C:/");

            fileChooser.setInitialDirectory(userDirectory);
            this.filePath = fileChooser.showOpenDialog(stage);

            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                patientImage3.setImage(image);
            } catch (IOException error) {
                System.err.println(error.getMessage());
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                PreparedStatement ps = con.prepareStatement("insert into photo(patient_id,photo) values(?,?)");
                File f = new File(String.valueOf(filePath));
                FileInputStream fs = new FileInputStream(f);
                ps.setString(1, String.valueOf(patientID));
                ps.setBinaryStream(2, fs, (int) f.length());
                ps.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }
        } else if (count < 11) {
            errorLabel.setText("Enter the previous images");
        } else {
            ImageViewController.bufferedImage = SwingFXUtils.fromFXImage(patientImage3.getImage(), null);
            Parent scene2Parent = FXMLLoader.load(getClass().getResource("ImageView.fxml"));
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(newPatientScene);
            window.setTitle("See the whole image");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
    }

    private FileChooser fileChooser;
    private File filePath;

//    public void chooseImageButtonPushed(ActionEvent e) {
//        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        fileChooser = new FileChooser();
//        fileChooser.setTitle("Choose the new Image");
//
//        String userDirectoryString = System.getProperty("user.home");
//        File userDirectory = new File(userDirectoryString);
//        if (!userDirectory.canRead())
//            userDirectory = new File("C:/");
//
//        fileChooser.setInitialDirectory(userDirectory);
//        this.filePath = fileChooser.showOpenDialog(stage);
//
//        try {
//            BufferedImage bufferedImage = ImageIO.read(filePath);
//            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//            patientImage.setImage(image);
//        } catch (IOException error) {
//            System.err.println(error.getMessage());
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> nameList = new ArrayList<>();
        nameList = fetchNamesFromDB();
        name.getEntries().addAll(nameList);
        List<String> numberList = new ArrayList<>();
        numberList = fetchNumbersFromDB();
        number.getEntries().addAll(numberList);
        errorLabel.setText("");
    }

    public void searchButtonPushed() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_id from patient_info where patient_name like '" + name.getText() + "'");
            while (rs.next())
                patientID = Integer.parseInt(rs.getString(1));

        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            System.out.println(patientID);
            ResultSet rs = stmt.executeQuery("select count(photo_ID) from photo where patient_id = " + patientID);
            while (rs.next())
                count = rs.getInt(1);
            System.out.println(count);

        } catch (Exception e) {
            System.err.println(e);
        }

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select photo from photo where patient_id = " + patientID);
            while (rs.next()) {
                i++;
                String path = "D:/Image " + i + ".jpg";
                Blob b = rs.getBlob(1);
                byte barr[] = b.getBytes(1, (int) b.length());

                FileOutputStream fout = new FileOutputStream(path);
                fout.write(barr);
                fout.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        j=i;
        try {
            while (i > 0) {
//                System.out.println("Retrieving images:");
                String path = "D:/Image " + i + ".jpg";

                if (i == 1) {

                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage.setImage(image);
                } else if (i == 2) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage2.setImage(image);
                } else if (i == 3) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage3.setImage(image);
                } else if (i == 4) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage4.setImage(image);
                }
                else if (i == 5) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage5.setImage(image);
                }
                else if (i == 6) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage6.setImage(image);
                }
                else if (i == 7) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage7.setImage(image);
                }
                else if (i == 8) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage8.setImage(image);
                }
                else if (i == 9) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage9.setImage(image);
                }
                else if (i == 10) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage10.setImage(image);
                }
                else if (i == 11) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage11.setImage(image);
                }
                else if (i == 12) {
                    File f = new File(path);
                    BufferedImage bufferedImage = ImageIO.read(f);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    patientImage12.setImage(image);
                }
                i--;
            }
        } catch (Exception err) {
            System.err.println(err);
        }
    }




    private List<String> fetchNamesFromDB() {
        List<String> nameList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_name from patient_info");
            while (rs.next()) {
                System.out.println(rs.getString(1));

                nameList.add(rs.getString(1));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return nameList;
    }

    private List<String> fetchNumbersFromDB() {
        List<String> numberList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_mobile_number from patient_info");
            while (rs.next()) {
                System.out.println(rs.getString(1));
                numberList.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return numberList;
    }

}

