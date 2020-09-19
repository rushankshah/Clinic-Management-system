package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Medicinal implements Initializable {

    @FXML
    private TableView<medicinalInformation> formulaTable;
    @FXML
    private TextField formulaNumber;
    @FXML
    private TextField formula;
    @FXML
    private TextField numberOfDays;
    @FXML
    private TextField date;
    @FXML
    private TextField pulseReading;
    @FXML
    private TextArea complaints;
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homePageButton;
    @FXML
    private TextField amountPaid;
    @FXML
    private TextArea prescriptions;
    @FXML
    private Button print;
    @FXML
    private TableColumn<medicinalInformation,String> formulaNumberColumn;
    @FXML
    private TableColumn<medicinalInformation,String> formulaColumn;
    @FXML
    private TableColumn<medicinalInformation,String> numberOfDaysColumn;
    @FXML
    private TableColumn<medicinalInformation,String> dateColumn;
    @FXML
    private TableColumn<medicinalInformation,String> pulseReadingColumn;
    @FXML
    private TableColumn<medicinalInformation,String> complaintsColumn;
    @FXML
    private TableColumn<medicinalInformation,String> amountPaidColumn;
    @FXML
    private TableColumn<medicinalInformation,String> prescriptionsColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label errorLabel;


    String dateEntered;
    String prescriptionEntered;

    private Patient_Information data;
    public String pName;



//    public void initData(Patient_Information patient_information) {
//        data = patient_information;
//        pName = data.getName();
//        System.out.println(pName);
//        nameLabel.setText(data.getName());
//        knownCase.setText(data.getKnownCase());
//        gender.setText(data.getGender());
//        mobileNumber.setText(data.getMobileNumber());
//        age.setText(data.getAge());
//        historyView.setItems(getItems());
//    }

    public void printButtonPushed()
    {
        PDFTester p =new PDFTester();
        p.PDF_Generator(name,age,gender,dateEntered,prescriptionEntered);
    }

    public void homeButtonPushed(ActionEvent e) throws IOException
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

    public void backButtonPushed(ActionEvent e) throws IOException
    {

        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Page 2.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Enter the details of the patient");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        formulaNumberColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("formulaNumber"));
        formulaColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("formula"));
        numberOfDaysColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation,String>("numberOfDays"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation,String>("date"));
        pulseReadingColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation,String>("pulseReading"));
        complaintsColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation,String>("complaints"));
        amountPaidColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation,String>("amountPaid"));
        prescriptionsColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation,String>("prescriptions"));
        formulaTable.setItems(getItems());
        nameLabel.setText("");
    }

    public ObservableList<medicinalInformation> getItems()
    {
        ObservableList<medicinalInformation> medicinals = FXCollections.observableArrayList();
        return medicinals;
    }

    public String name = "";

    public String age = "";

    public String gender = "";

    private Patient_Information p;
    public void initData(Patient_Information patient_information)
    {
        p = patient_information;
        nameLabel.setText(p.getName());
        name = p.getName();
        age=p.getAge();
        gender = p.getGender();
    }


    public void addButtonPushed()
    {
        medicinalInformation newMedicinal = new medicinalInformation(formulaNumber.getText(),formula.getText(),numberOfDays.getText(),date.getText(),pulseReading.getText(),complaints.getText(),amountPaid.getText(),prescriptions.getText());
        formulaTable.getItems().add(newMedicinal);
        try {
            String patientID = "0";
            String nameInput = name;
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
            }
            else
            {
                Statement stmt2 = con.createStatement();
                String insertStmt="insert into treatment values("+patientID+",'"+formulaNumber.getText()+"','"+formula.getText()+"','"+complaints.getText()+"','"+prescriptions.getText()+"','"+numberOfDays.getText()+"','"+date.getText()+"','"+pulseReading.getText()+"','"+amountPaid.getText()+"')";
                stmt2.executeUpdate(insertStmt);
                dateEntered=date.getText();
                prescriptionEntered=prescriptions.getText();
            }
            con.close();
            formulaNumber.clear();
            formula.clear();
            numberOfDays.clear();
            date.clear();
            pulseReading.clear();
            complaints.clear();
            amountPaid.clear();
            prescriptions.clear();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}

