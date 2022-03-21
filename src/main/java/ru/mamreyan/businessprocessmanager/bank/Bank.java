package ru.mamreyan.businessprocessmanager.bank;

import com.sun.istack.NotNull;
import ru.mamreyan.businessprocessmanager.address.Address;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bank {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private String name;
    @NotNull private String bik;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "address_id",
                 foreignKey = @ForeignKey (name = "address_id_fkey"))
    private Address address;

    @NotNull private boolean active;

    Bank() {
    }

    Bank(
            String name,
            String bik,
            Address address,
            boolean active
    ) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        if (bik == null) {
            throw new IllegalArgumentException("bik is null");
        }

        if (address == null) {
            throw new IllegalArgumentException("address is null");
        }

        this.name    = name;
        this.bik     = bik;
        this.address = address;
        this.active  = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        this.name = name;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        if (bik == null) {
            throw new IllegalArgumentException("bik is null");
        }

        this.bik = bik;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("address is null");
        }

        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isNotValid() {
        return name == null || bik == null || address == null;
    }

    public boolean is(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Bank bank)) {
            return false;
        }

        return Objects.equals(
                this.name,
                bank.name
        ) && Objects.equals(
                this.bik,
                bank.bik
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Bank bank)) {
            return false;
        }

        return Objects.equals(
                this.name,
                bank.name
        ) && Objects.equals(
                this.bik,
                bank.bik
        ) && Objects.equals(
                this.address,
                bank.address
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.name,
                this.bik,
                this.address,
                this.active
        );
    }

    @Override
    public String toString() {
        return ("Bank №" + this.id + ": {" + "\n") +
               ("name = " + this.name + "\n") +
               ("bik = " + this.bik + "\n") +
               ("address = " + this.address.toString() + "\n") +
               ("active = " + this.active + "\n") +
               "}";
    }
}