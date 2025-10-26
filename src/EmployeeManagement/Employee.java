package EmployeeManagement;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private Position position;
    private double salary;

    private Employee(String firstName, String lastName, String email, String companyName, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.companyName = companyName;
        this.position = position;
        this.salary = position.getBaseSalary();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Position getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    //Metoda do porównywania e-mailów (equals/hashmap, etc.)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return email.equals(employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "EmployeeManagement.Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position=" + position +
                ", salary=" + salary +
                '}';
    }

    //Builder do employee'a
    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    //class builder'a
    public static class EmployeeBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String companyName;
        private Position position;

        public EmployeeBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public EmployeeBuilder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public EmployeeBuilder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public Employee build() {
            if (firstName == null || lastName == null || email == null || companyName == null || position == null) {
                throw new IllegalStateException("Uzupelnij pola");
            }
            return new Employee(firstName, lastName, email, companyName, position);
        }
    }
}
