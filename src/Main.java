import EmployeeManagement.Employee;
import EmployeeManagement.EmployeeManagementSystem;
import EmployeeManagement.Position;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem();

        //builder
        system.addEmployee(Employee.builder()
                .setFirstName("Test")
                .setLastName("Testing")
                .setEmail("test@techcrp")
                .setCompanyName("Test")
                .setPosition(Position.PREZES)
                .build());

        system.addEmployee(Employee.builder()
                .setFirstName("Osk")
                .setLastName("L")
                .setEmail("osk.l@techcorp")
                .setCompanyName("TechCorp")
                .setPosition(Position.VICEPREZES)
                .build());

        system.addEmployee(Employee.builder()
                .setFirstName("Kamil")
                .setLastName("Slimak")
                .setEmail("Kamil.slimak@techcorp")
                .setCompanyName("TechCorp")
                .setPosition(Position.PREZES)
                .build());



        //wypluwanie informacji przy użyciu metod z class EmployeeManagement.EmployeeManagementSystem
        System.out.println("Lista pracowników:");
        system.displayAllEmployees();

        System.out.println("\nPracownicy w firmie ...:");
        system.findEmployeesByCompany("Test").forEach(System.out::println);

        System.out.println("\nPracownicy w firmie TechCorp:");
        system.findEmployeesByCompany("TechCorp").forEach(System.out::println);

        System.out.println("\nPracownicy posortowani po nazwisku:");
        system.sortEmployeesByLastName().forEach(System.out::println);

        System.out.println("\nGrupowanie pracowników według stanowiska:");
        Map<Position, List<Employee>> groupedByPosition = system.groupEmployeesByPosition();
        groupedByPosition.forEach((position, employees) -> {
            System.out.println(position + ":");
            employees.forEach(System.out::println);
        });

        System.out.println("\nLiczba pracowników na każdym stanowisku:");
        system.countEmployeesByPosition().forEach((position, count) -> {
            System.out.println(position + ": " + count);
        });

        System.out.println("\nŚrednie wynagrodzenie: " + system.calculateAverageSalary());

        System.out.println("\nPracownik z najwyższym wynagrodzeniem:");
        System.out.println(system.findEmployeeWithHighestSalary());
    }
}