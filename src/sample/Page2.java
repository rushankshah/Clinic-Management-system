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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Page2 implements Initializable
{

    @FXML
    private Button addDataButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField patientName;
    @FXML
    private TextField patientAge;
    @FXML
    private TextField patientMobileNo;
    @FXML
    private TextField patientGender;
    @FXML
    private TextArea patientKnownCase;
    @FXML
    private Label errorMobileNo;
    @FXML
    private Label errorAge;
    @FXML
    private Label errorMessage;

    public void backButtonPushed(ActionEvent e) throws IOException
    {

        Parent scene2Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Home Page");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();

    }

    public void addButtonPushed(ActionEvent e) throws IOException
    {
        if(validate()==1)
        {
            Patient_Information p = addData();
            addPatientData(p);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Medicinal.fxml"));
            Parent scene2Parent = loader.load();
            Scene newPatientScene = new Scene(scene2Parent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

            Medicinal controller = loader.getController();
            controller.initData(p);

            window.setScene(newPatientScene);
            window.setTitle("Add the remedies");
            window.setFullScreen(false);
            window.setFullScreenExitHint("");
            window.show();
        }
        else {
            errorMessage.setText("All credentials not filled");
            System.out.print("Error Found");
        }
    }


    public Patient_Information addData()
    {
        Patient_Information pi = new Patient_Information();
        pi.setName(patientName.getText());
        pi.setAge(patientAge.getText().trim());
        pi.setGender(patientGender.getText());
        pi.setKnownCase(patientKnownCase.getText());
        pi.setMobileNumber(patientMobileNo.getText());
        return pi;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        errorMobileNo.setText("");
        errorAge.setText("");
        errorMessage.setText("");
    }

    public void addPatientData(Patient_Information p)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management","root","root");
            Statement stmt=con.createStatement();
            //ResultSet rs=stmt.executeQuery("insert into patient_info(patient_name,patient_known_case,patient_age,patient_gender,patient_mobile_number) values('"+p.getName()+"','"+p.getKnownCase()+"','"+p.getAge()+"','"+p.getGender()+"','"+p.getMobileNumber()+"')");
            String insertStmt="insert into patient_info(patient_name,patient_known_case,patient_age,patient_gender,patient_mobile_number) values('"+p.getName().toUpperCase()+"','"+p.getKnownCase()+"','"+p.getAge()+"','"+p.getGender()+"','"+p.getMobileNumber()+"')";
            stmt.executeUpdate(insertStmt);
            //("insert into patient_info(patient_name,patient_known_case,patient_age,patient_gender,patient_mobile_number) values('"+p.getName()+"','"+p.getKnownCase()+"','"+p.getAge()+"','"+p.getGender()+"','"+p.getMobileNumber()+"')");
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public int validate()
    {
        if("".equals(patientName.getText()))
        {
            return 0;
        }
        else if("".equals(patientAge.getText()))
        {
            return 0;
        }
        else if("".equals(patientGender.getText()))
        {
            return 0;
        }
        else if("".equals(patientKnownCase.getText()))
        {
            return 0;
        }
        else if("".equals(patientMobileNo.getText()))
        {
            return 0;
        }
        else if(10!=(patientMobileNo.getText()).length())
        {
            System.out.println("Mobile number not 10 digits");
            errorMobileNo.setText("Mobile number is not of 10 digits");
            return 0;
        }
        else if(3<=(patientAge.getText()).length())
        {
            System.out.println("Age cannot exceed 999(Age limit))");
            errorAge.setText("Age cannot exceed 999(Age limit))");
            return 0;
        }
        else
        {
            return 1;
        }
    }
    public void getPatientData()
    {

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management","root","root");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from patient_info");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

