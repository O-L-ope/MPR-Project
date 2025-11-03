import model.CompanyStatistics;
import model.Employee;
import model.ImportSummary;
import service.ApiService;
import service.EmployeeService;
import model.Position;
import service.ImportService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        EmployeeService employeeService = EmployeeService.getInstance();

        //builder


//        system.addEmployee(Employee.builder()
//                .setFirstName("Osk")
//                .setLastName("L")
//                .setEmail("osk.l@techcorp")
//                .setCompanyName("TechCorp")
//                .setPosition(Position.VICEPREZES)
//                .build());
//
//        system.addEmployee(Employee.builder()
//                .setFirstName("Kamil")
//                .setLastName("Slimak")
//                .setEmail("Kamil.slimak@techcorp")
//                .setCompanyName("TechCorp")
//                .setPosition(Position.PREZES)
//                .build());
//
//
//
//        //wypluwanie informacji przy użyciu metod z class service.EmployeeManagementSystem
//        System.out.println("Lista pracowników:");
//        system.displayAllEmployees();
//
//        System.out.println("\nPracownicy w firmie ...:");
//        system.findEmployeesByCompany("Test").forEach(System.out::println);
//
//        System.out.println("\nPracownicy w firmie TechCorp:");
//        system.findEmployeesByCompany("TechCorp").forEach(System.out::println);
//
//        System.out.println("\nPracownicy posortowani po nazwisku:");
//        system.sortEmployeesByLastName().forEach(System.out::println);
//
//        System.out.println("\nGrupowanie pracowników według stanowiska:");
//        Map<Position, List<Employee>> groupedByPosition = system.groupEmployeesByPosition();
//        groupedByPosition.forEach((position, employees) -> {
//            System.out.println(position + ":");
//            employees.forEach(System.out::println);
//        });
//
//        System.out.println("\nLiczba pracowników na każdym stanowisku:");
//        system.countEmployeesByPosition().forEach((position, count) -> {
//            System.out.println(position + ": " + count);
//        });
//
//        System.out.println("\nŚrednie wynagrodzenie: " + system.calculateAverageSalary());
//
//        System.out.println("\nPracownik z najwyższym wynagrodzeniem:");
//        System.out.println(system.findEmployeeWithHighestSalary());


        System.out.println("CSV TEST");
        ImportService importService = new ImportService();
        ImportSummary importSummary;
        importSummary = importService.ImportFromCsv("src\\resources.csv");
        System.out.println("\nAfter CSV import:");
        employeeService.displayAllEmployees();

        System.out.println("\nEmployees imported:"+importSummary.getNumberOfImportedEmployees());
        System.out.println("Errors: "+importSummary.getErrorsDuringImport());


        System.out.println("\n\n");

        System.out.println("API TEST");
        System.out.println("Returned List:");
        ApiService apiService = new ApiService();
        List<Employee> emps = apiService.fetchEmployeesFromApi("https://mocki.io/v1/18dfb986-b83b-476c-b739-1787a8f8c277");
        emps.forEach(emp -> {
            System.out.println(emp.toString());
        });

        System.out.println("\n\n");
        System.out.println("ANALYTICAL OPERATIONS TEST");
        System.out.println("\nList of employees with unfair salaries:");
        List<Employee> wrongedEmployees = new ArrayList<>();
        wrongedEmployees = employeeService.validateSalaryConsistency();
        for (Employee emp : wrongedEmployees) {
            System.out.println(emp.toString());
        }

        System.out.println("\nCompany statistics:");

        Map<String, CompanyStatistics> companyMap = new HashMap<>();
        companyMap = employeeService.getCompanyStatistics();
        for (Map.Entry<String, CompanyStatistics> entry : companyMap.entrySet()) {
            CompanyStatistics companyStatistics = entry.getValue();
            System.out.println(companyStatistics);
        }

    }
}