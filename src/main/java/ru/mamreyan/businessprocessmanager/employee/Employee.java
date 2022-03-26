package ru.mamreyan.businessprocessmanager.employee;

import com.sun.istack.NotNull;
import ru.mamreyan.businessprocessmanager.bank.Bank;
import ru.mamreyan.businessprocessmanager.position.Position;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private String            lastName;
    @NotNull private String            firstName;
    private          String            middleName;
    private          Sex               sex;
    @NotNull private GregorianCalendar birthDate;
    @NotNull private GregorianCalendar employmentDate;

    @NotNull
    @OneToOne
    @JoinColumn (name = "position_id",
                 foreignKey = @ForeignKey (name = "position_id_fkey"))
    private Position position;

    @NotNull
    @Column (precision = 2)
    private double salary;

    private String workPhone;
    private String mobilePhone;

    @ManyToOne
    @JoinColumn (name = "head_id",
                 foreignKey = @ForeignKey (name = "head_id_fkey"))
    private Employee head;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "bank_id",
                 foreignKey = @ForeignKey (name = "bank_id_fkey"))
    private Bank bank;

    @NotNull private boolean active;

    Employee() {
    }

    Employee(
            EmployeeBuilder employeeBuilder
    ) {
        if (employeeBuilder.lastName == null || employeeBuilder.lastName.isBlank()) {
            throw new IllegalArgumentException("last name is null");
        }

        if (employeeBuilder.firstName == null || employeeBuilder.lastName.isBlank()) {
            throw new IllegalArgumentException("first name is null");
        }

        if (employeeBuilder.sex == null) {
            throw new IllegalArgumentException("sex is null");
        }

        if (employeeBuilder.birthDate == null) {
            throw new IllegalArgumentException("birth date is null");
        }

        if (employeeBuilder.employmentDate == null) {
            throw new IllegalArgumentException("employment date is null");
        }

        if (employeeBuilder.position == null) {
            throw new IllegalArgumentException("position is null");
        }

        if (employeeBuilder.salary < 0.00d) {
            throw new IllegalArgumentException("salary is negative");
        }

        if (employeeBuilder.bank == null) {
            throw new IllegalArgumentException("bank is null");
        }

        this.lastName       = employeeBuilder.lastName;
        this.firstName      = employeeBuilder.firstName;
        this.middleName     = employeeBuilder.middleName;
        this.sex            = employeeBuilder.sex;
        this.birthDate      = employeeBuilder.birthDate;
        this.employmentDate = employeeBuilder.employmentDate;
        this.position       = employeeBuilder.position;
        this.salary         = employeeBuilder.salary;
        this.workPhone      = employeeBuilder.workPhone;
        this.mobilePhone    = employeeBuilder.mobilePhone;
        this.head           = employeeBuilder.head;
        this.bank           = employeeBuilder.bank;
        this.active         = employeeBuilder.active;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("last name is null");
        }

        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("first name is null");
        }

        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        if (sex == null) {
            throw new IllegalArgumentException("sex is null");
        }

        this.sex = sex;
    }

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(GregorianCalendar birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("birth date is null");
        }

        this.birthDate = birthDate;
    }

    public GregorianCalendar getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(GregorianCalendar employmentDate) {
        if (employmentDate == null) {
            throw new IllegalArgumentException("employment date is null");
        }

        this.employmentDate = employmentDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position is null");
        }

        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0.00d) {
            throw new IllegalArgumentException("salary is negative");
        }

        this.salary = salary;
    }

    public String getWorkPhone() {
        return this.workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        if (bank == null) {
            throw new IllegalArgumentException("bank is null");
        }

        this.bank = bank;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isNotValid() {
        return lastName == null ||
               firstName == null ||
               sex == null ||
               birthDate == null ||
               employmentDate == null ||
               position == null ||
               salary < 0.00d ||
               bank == null;
    }

    public boolean is(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Employee employee)) {
            return false;
        }

        return Objects.equals(
                this.lastName,
                employee.lastName
        ) && Objects.equals(
                this.firstName,
                employee.firstName
        ) && Objects.equals(
                this.middleName,
                employee.middleName
        ) && Objects.equals(
                this.sex,
                employee.sex
        ) && Objects.equals(
                this.birthDate,
                employee.birthDate
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Employee employee)) {
            return false;
        }

        return Objects.equals(
                this.lastName,
                employee.lastName
        ) && Objects.equals(
                this.firstName,
                employee.firstName
        ) && Objects.equals(
                this.middleName,
                employee.middleName
        ) && Objects.equals(
                this.sex,
                employee.sex
        ) && Objects.equals(
                this.birthDate,
                employee.birthDate
        ) && Objects.equals(
                this.employmentDate,
                employee.employmentDate
        ) && Objects.equals(
                this.position,
                employee.position
        ) && Objects.equals(
                this.salary,
                employee.salary
        ) && Objects.equals(
                this.workPhone,
                employee.workPhone
        ) && Objects.equals(
                this.mobilePhone,
                employee.mobilePhone
        ) && Objects.equals(
                this.head,
                employee.head
        ) && Objects.equals(
                this.bank,
                employee.bank
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.lastName,
                this.firstName,
                this.middleName,
                this.sex,
                this.birthDate,
                this.employmentDate,
                this.position,
                this.salary,
                this.workPhone,
                this.mobilePhone,
                this.head,
                this.bank,
                this.active
        );
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        return (stringBuilder
                .append("Employee №")
                .append(this.id)
                .append(": {\nlastName = ")
                .append(this.lastName)
                .append(",\nfirstName = ")
                .append(this.firstName)
                .append(",\nmiddleName = ")
                .append(this.middleName)
                .append(",\nsex = ")
                .append(this.sex)
                .append(",\nbirthDate = ")
                .append(this.birthDate.toInstant())
                .append(",\nemploymentDate = ")
                .append(this.employmentDate.toInstant())
                .append(",\nposition = ")
                .append(this.position.toString())
                .append(",\nsalary = ")
                .append(this.salary)
                .append(",\nworkPhone = ")
                .append(this.workPhone)
                .append(",\nmobilePhone = ")
                .append(this.mobilePhone)
                .append(",\nhead = ")
                .append(this.head != null ? this.head.toString() : "null")
                .append(",\nbank = ")
                .append(this.bank.toString())
                .append(",\nactive = ")
                .append(this.active)
                .append("\n}")).toString();
    }

    public static class EmployeeBuilder {
        private String            lastName;
        private String            firstName;
        private String            middleName;
        private Sex               sex;
        private GregorianCalendar birthDate;
        private GregorianCalendar employmentDate;
        private Position          position;
        private double            salary;
        private String            workPhone;
        private String            mobilePhone;
        private Employee          head;
        private Bank              bank;
        private boolean           active = true;

        public EmployeeBuilder() {
            super();
        }

        public EmployeeBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public EmployeeBuilder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public EmployeeBuilder birthDate(GregorianCalendar birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public EmployeeBuilder employmentDate(GregorianCalendar employmentDate) {
            this.employmentDate = employmentDate;
            return this;
        }

        public EmployeeBuilder position(Position position) {
            this.position = position;
            return this;
        }

        public EmployeeBuilder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder workPhone(String workPhone) {
            this.workPhone = workPhone;
            return this;
        }

        public EmployeeBuilder mobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public EmployeeBuilder head(Employee head) {
            this.head = head;
            return this;
        }

        public EmployeeBuilder bank(Bank bank) {
            this.bank = bank;
            return this;
        }

        public EmployeeBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee(this);

            if (employee.isNotValid()) {
                throw new EmployeeNotValidException(employee);
            }

            return employee;
        }
    }
}