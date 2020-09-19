package sample;

public class medicinalInformation {
    String formulaNumber;
    String formula;
    String numberOfDays;
    String date;
    String pulseReading;

    public String getFormulaNumber() {
        return formulaNumber;
    }

    public void setFormulaNumber(String formulaNumber) {
        this.formulaNumber = formulaNumber;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPulseReading() {
        return pulseReading;
    }

    public void setPulseReading(String pulseReading) {
        this.pulseReading = pulseReading;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    String complaints;
    String amountPaid;
    String prescriptions;

    public medicinalInformation(String formulaNumber, String formula, String numberOfDays, String date, String pulseReading, String complaints, String amountPaid, String prescriptions) {
        this.formulaNumber = formulaNumber;
        this.formula = formula;
        this.numberOfDays = numberOfDays;
        this.date = date;
        this.pulseReading = pulseReading;
        this.complaints = complaints;
        this.amountPaid = amountPaid;
        this.prescriptions = prescriptions;
    }
}
