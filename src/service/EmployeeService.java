package service;

import model.CompanyStatistics;
import model.Employee;
import model.Position;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private Set<Employee> employees = new HashSet<>();

    public boolean addEmployee(Employee employee) {
        if (employees.stream().anyMatch(e -> e.getEmail().equals(employee.getEmail()))) {
            System.out.println("Pracownik o tym adresie email już istnieje!");
            return false;  // Email jest już w systemie
        }
        employees.add(employee);
        return true;
    }
    private static final EmployeeService instance = new EmployeeService();
    private EmployeeService() {}
    public static EmployeeService getInstance() {
        return instance;
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

    public List<Employee> validateSalaryConsistency(){
        List<Employee> validatedEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            if (employee.getSalary() < employee.getPosition().baseSalary()) {
                validatedEmployees.add(employee);
            }
        });
        return validatedEmployees;
    }

    public Map<String, CompanyStatistics> getCompanyStatistics() {
        Map<String, CompanyStatistics> companyStatisticsBomb = new HashMap<>();
        for (Employee employee : employees) {
            String companyName = employee.getCompanyName();
            if(!companyStatisticsBomb.containsKey(companyName)){
                CompanyStatistics companyStatistics = new CompanyStatistics();
                companyStatistics.setCompanyName(companyName);
                companyStatistics.setEmployeeCount(1);
                companyStatistics.setAverageSalary(employee.getSalary());
                companyStatistics.setHighestSalary(employee.getSalary());
                companyStatistics.setEmployeeWithHighestSalary(employee.getFirstName()+" "+employee.getLastName());
                companyStatisticsBomb.put(companyName, companyStatistics);

            }
            else{
                CompanyStatistics companyStatistics = companyStatisticsBomb.get(companyName);
                companyStatistics.setEmployeeCount(companyStatistics.getEmployeeCount()+1);
                companyStatistics.setAverageSalary(
                        (companyStatistics.getAverageSalary()*(companyStatistics.getEmployeeCount()-1) + employee.getSalary())/companyStatistics.getEmployeeCount()
                );
                if(companyStatistics.getHighestSalary() < employee.getSalary()){
                    companyStatistics.setHighestSalary(employee.getSalary());
                    companyStatistics.setEmployeeWithHighestSalary(employee.getFirstName()+" "+employee.getLastName());
                }
            }
        }
        return companyStatisticsBomb;
    }
}
