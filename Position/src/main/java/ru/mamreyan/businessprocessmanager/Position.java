package ru.mamreyan.businessprocessmanager;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private boolean active;

    Position() {}

    Position(
            String name,
            boolean active
    ) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        this.name = name;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isValid() {
        return !(
                name == null
        );
    }

    public boolean is(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Position position)) {
            return false;
        }

        return Objects.equals(this.name, position.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Position position)) {
            return false;
        }

        return Objects.equals(this.name, position.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.name,
                this.active
        );
    }

    @Override
    public String toString() {
        return "Position №" + this.id + ": {" + "\n" +
                "name = " + this.name + "\n" +
                "active = " + this.active + "\n" +
                "}";
    }
}