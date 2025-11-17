package model;

import java.util.List;

public class ImportSummary {
    private int numberOfImportedEmployees;
    List<String> errorsDuringImport;
    public ImportSummary() {}

    public void setErrorsDuringImport(List<String> errorsDuringImport) {
        this.errorsDuringImport = errorsDuringImport;
    }
    public int getNumberOfImportedEmployees() {
        return numberOfImportedEmployees;
    }
    public void setNumberOfImportedEmployees(int numberOfImportedEmployees) {
        this.numberOfImportedEmployees = numberOfImportedEmployees;
    }
    public List<String> getErrorsDuringImport() {
        return errorsDuringImport;
    }
}