package ru.mamreyan.businessprocessmanager.employee;

import com.sun.istack.NotNull;
import ru.mamreyan.businessprocessmanager.bank.Bank;
import ru.mamreyan.businessprocessmanager.position.Position;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private String lastName;
    @NotNull private String firstName;
    private          String middleName;
    private          Sex    sex;
    @NotNull private Date   birthDate;
    @NotNull private Date   employmentDate;

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

    @NotNull
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
            String lastName,
            String firstName,
            String middleName,
            Sex sex,
            Date birthDate,
            Date employmentDate,
            Position position,
            double salary,
            String workPhone,
            String mobilePhone,
            Employee head,
            Bank bank,
            boolean active
    ) {
        if (lastName == null) {
            throw new IllegalArgumentException("last name is null");
        }

        if (firstName == null) {
            throw new IllegalArgumentException("first name is null");
        }

        if (sex == null) {
            throw new IllegalArgumentException("sex is null");
        }

        if (birthDate == null) {
            throw new IllegalArgumentException("birth date is null");
        }

        if (employmentDate == null) {
            throw new IllegalArgumentException("employment date is null");
        }

        if (position == null) {
            throw new IllegalArgumentException("position is null");
        }

        if (salary < 0.00d) {
            throw new IllegalArgumentException("salary is negative");
        }

        if (head == null) {
            throw new IllegalArgumentException("head is null");
        }

        if (bank == null) {
            throw new IllegalArgumentException("bank is null");
        }

        this.lastName       = lastName;
        this.firstName      = firstName;
        this.middleName     = middleName;
        this.sex            = sex;
        this.birthDate      = birthDate;
        this.employmentDate = employmentDate;
        this.position       = position;
        this.salary         = salary;
        this.workPhone      = workPhone;
        this.mobilePhone    = mobilePhone;
        this.head           = head;
        this.bank           = bank;
        this.active         = active;
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
        if (lastName == null) {
            throw new IllegalArgumentException("last name is null");
        }

        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("birth date is null");
        }

        this.birthDate = birthDate;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
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
        if (head == null) {
            throw new IllegalArgumentException("head is null");
        }

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
               head == null ||
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
        return ("Employee №" + this.id + ": {" + "\n") +
               ("lastName = " + this.lastName + "\n") +
               ("firstName = " + this.firstName + "\n") +
               ("middleName = " + this.middleName + "\n") +
               ("sex = " + this.sex + "\n") +
               ("birthDate = " + this.birthDate.toString() + "\n") +
               ("employmentDate = " + this.employmentDate.toString() + "\n") +
               ("position = " + this.position.toString() + "\n") +
               ("salary = " + this.salary + "\n") +
               ("workPhone = " + this.workPhone + "\n") +
               ("mobilePhone = " + this.mobilePhone + "\n") +
               ("head = " + this.head.toString() + "\n") +
               ("bank = " + this.bank.toString() + "\n") +
               ("active = " + this.active + "\n") +
               "}";
    }
}