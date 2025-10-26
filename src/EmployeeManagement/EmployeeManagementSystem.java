package EmployeeManagement;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeManagementSystem {
    private Set<Employee> employees = new HashSet<>();

    public boolean addEmployee(Employee employee) {
        if (employees.stream().anyMatch(e -> e.getEmail().equals(employee.getEmail()))) {
            System.out.println("Pracownik o tym adresie email już istnieje!");
            return false;  // Email jest już w systemie
        }
        employees.add(employee);
        return true;
    }

    public void displayAllEmployees() {
        employees.forEach(System.out::println);
    }

    public List<Employee> findEmployeesByCompany(String companyName) {
        return employees.stream()
                .filter(e -> e.getCompanyName().equalsIgnoreCase(companyName))
                .collect(Collectors.toList());
    }

    public List<Employee> sortEmployeesByLastName() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .collect(Collectors.toList());
    }

    public Map<Position, List<Employee>> groupEmployeesByPosition() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition));
    }

    public Map<Position, Long> countEmployeesByPosition() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.counting()));
    }

    public double calculateAverageSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }
    public Employee findEmployeeWithHighestSalary() {
        return employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);  // Zwraca null, jeśli lista jest pusta
    }
}
