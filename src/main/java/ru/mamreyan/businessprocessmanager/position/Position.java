package ru.mamreyan.businessprocessmanager.position;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Position {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private String  name;
    @NotNull private boolean active;

    Position() {
    }

    public Position(
            String name
    ) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is null");
        }

        this.name   = name;
        this.active = true;
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
        if (name == null || name.isBlank()) {
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

    public boolean isNotValid() {
        return name == null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Position position)) {
            return false;
        }

        return Objects.equals(
                this.name,
                position.name
        );
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
        StringBuilder stringBuilder = new StringBuilder();

        return (stringBuilder
                .append("Position №")
                .append(this.id)
                .append(": {\nname = ")
                .append(this.name)
                .append("\nactive = ")
                .append(this.active)
                .append("\n}")).toString();
    }
}