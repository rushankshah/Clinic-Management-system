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
import javafx.scene.control.TextField;


import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class OldPatient implements Initializable {

    @FXML
    private AutocompletionlTextField name;
    @FXML
    private AutocompletionlTextField number;
    @FXML
    private Button searchButton;
    @FXML
    private Button backButton;
    @FXML
    private Label errorLabel;

    int checker = 0;

    public int validate() {
        if ("".equals(name.getText()))
            return 0;
        else if ("".equals(number.getText()))
            return 1;
        else
            return 2;
    }


    public void backButtonPushed(ActionEvent e) throws IOException {

        Parent scene2Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Home Page");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();

    }

    public void searchButtonPushed(ActionEvent e) throws IOException {

        if (validate() == 0) {
            Patient_Information patient_information = getData();
            getPatientMobileData(patient_information);
            if (checker == 1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Old patient data.fxml"));
                Parent scene2Parent = loader.load();
                Scene newPatientScene = new Scene(scene2Parent);
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

                OldPatientData controller = loader.getController();
                controller.initData(patient_information);

                window.setScene(newPatientScene);
                window.setTitle("See the details!");
                window.setResizable(true);
                window.setFullScreen(true);
                window.setFullScreenExitHint("");
                window.show();

            } else
                errorLabel.setText("Data Not Found");
        } else if (validate() == 1) {
            Patient_Information patient_information = getData();
            getPatientNameData(patient_information);
            if (checker == 1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Old patient data.fxml"));
                Parent scene2Parent = loader.load();
                Scene newPatientScene = new Scene(scene2Parent);
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

                OldPatientData controller = loader.getController();
                controller.initData(patient_information);

                window.setScene(newPatientScene);
                window.setTitle("See the details!");
                window.setResizable(true);
                window.setFullScreen(true);
                window.setFullScreenExitHint("");
                window.show();
            } else
                errorLabel.setText("Data Not Found");
        } else if (validate() == 2) {
            errorLabel.setText("Enter any one credential");
        }

    }

    public void getPatientMobileData(Patient_Information pi) {

        try {
            String patientID = "0";

            String mobileNumberInput = pi.getMobileNumber();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_ID from patient_info where patient_mobile_number like '" + mobileNumberInput + "'");
            while (rs.next()) {
                System.out.println(rs.getString(1));
                patientID = rs.getString(1);
            }
            if (patientID.equals("0")) {
                errorLabel.setText("Patient Does not exist");
            } else {
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("select patient_known_case,patient_name,patient_mobile_number,patient_age,patient_gender from patient_info where patient_ID = " + patientID);
                while (rs2.next()) {
                    System.out.println("Known Case is " + rs2.getString(1));
                    pi.setKnownCase(rs2.getString(1));
                    pi.setName(rs2.getString(2));
                    pi.setAge(rs2.getString(4));
                    pi.setMobileNumber(rs2.getString(3));
                    pi.setGender(rs2.getString(5));
                }
            }
            con.close();
            checker = 1;
        } catch (Exception e) {
            System.out.println(e);
            errorLabel.setText("Data Not Found");
        }
    }

    public void getPatientNameData(Patient_Information pi) {

        try {
            String patientID = "0";
            String nameInput = pi.getName();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_ID from patient_info where patient_name like '" + nameInput + "'");
            while (rs.next()) {
                System.out.println(rs.getString(1));
                patientID = rs.getString(1);
            }
            if (patientID.equals("0")) {
                errorLabel.setText("Patient Does not exist");
            } else {
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("select patient_known_case,patient_name,patient_mobile_number,patient_age,patient_gender from patient_info where patient_ID = " + patientID);
                while (rs2.next()) {
                    System.out.println("Known Case is " + rs2.getString(1));
                    pi.setKnownCase(rs2.getString(1));
                    pi.setName(rs2.getString(2));
                    pi.setAge(rs2.getString(4));
                    pi.setMobileNumber(rs2.getString(3));
                    pi.setGender(rs2.getString(5));
                }
            }
            con.close();
            checker = 1;
        } catch (Exception e) {
            System.out.println(e);
            errorLabel.setText("Data Not Found");
        }
    }

    public Patient_Information getData() {
        Patient_Information p = new Patient_Information();
        p.setName(name.getText());
        p.setMobileNumber(number.getText());
        return p;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //String suggestions[]  = {"HI","Hello"};
        errorLabel.setText("");
        //TextFields.bindAutoCompletion(name,suggestions);

//        name=new AutoCompletionTextBox();
//        AutoCompletionTextBox dummy=new AutoCompletionTextBox();
//        dummy.getEntries().addAll(Arrays.asList("AA", "AB", "AC","BCA"));

        List<String> nameList=new ArrayList<>();
        nameList=fetchNamesFromDB();
        name.getEntries().addAll(nameList);
        List<String> numberList = new ArrayList<>();
        numberList = fetchNumbersFromDB();
        number.getEntries().addAll(numberList);

    }

    private List<String> fetchNamesFromDB()
    {
        List<String> nameList=new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_name from patient_info");
            while (rs.next()) {
                System.out.println(rs.getString(1));

                nameList.add(rs.getString(1));

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return nameList;
    }
    private List<String> fetchNumbersFromDB()
    {
        List<String> numberList=new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_mobile_number from patient_info");
            while (rs.next()) {
                System.out.println(rs.getString(1));

                numberList.add(rs.getString(1));

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return numberList;
    }



}

