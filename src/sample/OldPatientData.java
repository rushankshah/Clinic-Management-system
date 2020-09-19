package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Date;


public class OldPatientData implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label knownCase;
    @FXML
    private Label mobileNumber;
    @FXML
    private Label age;
    @FXML
    private Label gender;
    @FXML
    private TableView<medicinalInformation> historyView;
    @FXML
    private TextField formulaNumber;
    @FXML
    private TextArea formula;
    @FXML
    private TextField date;
    @FXML
    private TextField numberOfDays;
    @FXML
    private TextField pulseReading;
    @FXML
    private TextArea complaints;
    @FXML
    private Button addDataButton;
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
    private Label errorLabel;
    @FXML
    private TableColumn<medicinalInformation, String> formulaNumberColumn;
    @FXML
    private TableColumn<medicinalInformation, String> formulaColumn;
    @FXML
    private TableColumn<medicinalInformation, String> numberOfDaysColumn;
    @FXML
    private TableColumn<medicinalInformation, String> dateColumn;
    @FXML
    private TableColumn<medicinalInformation, String> pulseReadingColumn;
    @FXML
    private TableColumn<medicinalInformation, String> complaintsColumn;
    @FXML
    private TableColumn<medicinalInformation, String> amountPaidColumn;
    @FXML
    private TableColumn<medicinalInformation, String> prescriptionsColumn;
    @FXML
    private TextField ageChange;
    @FXML
    private TextField mobileChange;



    String dateEntered;
    String prescriptionEntered;

    private Patient_Information data;
    public String pName;


    public void printButtonPushed()
    {
        PDFTester p =new PDFTester();
        p.PDF_Generator(nameLabel.getText(),age.getText(),gender.getText(),dateEntered,prescriptionEntered);
    }


    public void initData(Patient_Information patient_information) {
        data = patient_information;
        pName = data.getName();
        System.out.println(pName);
        nameLabel.setText(data.getName());
        knownCase.setText(data.getKnownCase());
        gender.setText(data.getGender());
        mobileNumber.setText(data.getMobileNumber());
        age.setText(data.getAge());
        historyView.setItems(getItems());
    }

    public void changeAgeButtonPushed() {
        String changedAge = ageChange.getText();
        age.setText(changedAge);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            String insertStmt = "update patient_info set patient_age='" + changedAge + "' where patient_name like '" + nameLabel.getText() + "'";
            stmt.executeUpdate(insertStmt);
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
    public void changeMobileNumberButtonPushed() {
        String changedMobileNumber = mobileChange.getText();
        if(10!=changedMobileNumber.length())
        {
            errorLabel.setText("Mobile number not of 10 digits");
        }
        else {
            mobileNumber.setText(changedMobileNumber);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
                Statement stmt = con.createStatement();
                String insertStmt = "update patient_info set patient_mobile_number='" + changedMobileNumber + "' where patient_name like '" + nameLabel.getText() + "'";
                stmt.executeUpdate(insertStmt);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }



    public void backButtonPushed(ActionEvent e) throws IOException {

        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Old patient.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Enter the details");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();

    }

    public void homeButtonPushed(ActionEvent e) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene newPatientScene = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(newPatientScene);
        window.setTitle("Home Page");
        window.setFullScreen(false);
        window.setFullScreenExitHint("");
        window.show();
    }

    public String patientID = "0";

    public void addButtonPushed() {
        dateEntered=date.getText();
        prescriptionEntered=prescriptions.getText();
        medicinalInformation newMedicinal = new medicinalInformation(formulaNumber.getText(), formula.getText(), numberOfDays.getText(), date.getText(), pulseReading.getText(), complaints.getText(), amountPaid.getText(), prescriptions.getText());
        historyView.getItems().add(newMedicinal);
        try {
            String nameInput = pName;
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
                String insertStmt = "insert into treatment values(" + patientID + ",'" + formulaNumber.getText() + "','" + formula.getText() + "','" + complaints.getText() + "','" + prescriptions.getText() + "','" + numberOfDays.getText() + "','" + date.getText() + "','" + pulseReading.getText() + "','" + amountPaid.getText() + "')";
                stmt2.executeUpdate(insertStmt);
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formulaNumberColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("formulaNumber"));
        formulaColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("formula"));
        numberOfDaysColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("numberOfDays"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("date"));
        pulseReadingColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("pulseReading"));
        complaintsColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("complaints"));
        amountPaidColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("amountPaid"));
        prescriptionsColumn.setCellValueFactory(new PropertyValueFactory<medicinalInformation, String>("prescriptions"));

        /*historyView.setEditable(true);
        formulaNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        formulaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numberOfDaysColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pulseReadingColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        complaintsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        amountPaidColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prescriptionsColumn.setCellFactory(TextFieldTableCell.forTableColumn());*/
        /*System.out.println("Connection starts!");
        try {
            String nameInput = pName;
            System.out.println(nameInput);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_ID from patient_info where patient_name like '" + nameInput + "'");
            System.out.println("Query execution Starts!");
            while (rs.next()) {
                System.out.println(rs.getString(1));
                patientID = rs.getString(1);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }*/
        errorLabel.setText("");
        nameLabel.setText("");
        knownCase.setText("");




    }

    public ObservableList<medicinalInformation> getItems() {
        ObservableList<medicinalInformation> medicinals = FXCollections.observableArrayList();
        try {
            String nameInput = pName;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic_Management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select patient_ID from patient_info where patient_name like '" + nameInput + "'");
            while (rs.next()) {
                System.out.println(rs.getString(1));
                patientID = rs.getString(1);
            }
            System.out.println(patientID);
            System.out.println("Query execution starts");
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select patient_id,formula_no,formula,complaints,prescription,No_of_days,Date,pulse_reading,amount_paid from treatment where patient_id = " + patientID);
            while (rs2.next()) {
                medicinals.add(new medicinalInformation(rs2.getString(2), rs2.getString(3), rs2.getString(6), rs2.getString(7), rs2.getString(8), rs2.getString(4), rs2.getString(9), rs2.getString(5)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return medicinals;
    }
}
