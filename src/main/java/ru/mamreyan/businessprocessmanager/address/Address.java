package ru.mamreyan.businessprocessmanager.address;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private String  country;
    @NotNull private String  region;
    @NotNull private String  locality;
    @NotNull private String  street;
    @NotNull private String  building;
    private          String  apartment;
    @NotNull private boolean active;

    Address() {
    }

    Address(
            String country,
            String region,
            String locality,
            String street,
            String building,
            String apartment,
            boolean active
    ) {
        if (country == null) {
            throw new IllegalArgumentException("country is null");
        }

        if (region == null) {
            throw new IllegalArgumentException("region is null");
        }

        if (locality == null) {
            throw new IllegalArgumentException("locality is null");
        }

        if (street == null) {
            throw new IllegalArgumentException("street is null");
        }

        if (building == null) {
            throw new IllegalArgumentException("building is null");
        }

        this.country   = country;
        this.region    = region;
        this.locality  = locality;
        this.street    = street;
        this.building  = building;
        this.apartment = apartment;
        this.active    = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null) {
            throw new IllegalArgumentException("country is null");
        }

        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (region == null) {
            throw new IllegalArgumentException("region is null");
        }

        this.region = region;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        if (locality == null) {
            throw new IllegalArgumentException("locality is null");
        }

        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street == null) {
            throw new IllegalArgumentException("street is null");
        }

        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        if (building == null) {
            throw new IllegalArgumentException("building is null");
        }

        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isNotValid() {
        return country == null || region == null || locality == null || street == null || building == null;
    }

    public boolean is(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Address address)) {
            return false;
        }

        return Objects.equals(
                this.country,
                address.country
        ) && Objects.equals(
                this.region,
                address.region
        ) && Objects.equals(
                this.locality,
                address.locality
        ) && Objects.equals(
                this.street,
                address.street
        ) && Objects.equals(
                this.building,
                address.building
        ) && Objects.equals(
                this.apartment,
                address.apartment
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Address address)) {
            return false;
        }

        return Objects.equals(
                this.country,
                address.country
        ) && Objects.equals(
                this.region,
                address.region
        ) && Objects.equals(
                this.locality,
                address.locality
        ) && Objects.equals(
                this.street,
                address.street
        ) && Objects.equals(
                this.building,
                address.building
        ) && Objects.equals(
                this.apartment,
                address.apartment
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.country,
                this.region,
                this.locality,
                this.street,
                this.building,
                this.apartment,
                this.active
        );
    }

    @Override
    public String toString() {
        return ("Address №" + this.id + ": {" + "\n") +
               ("country = " + this.country + "\n") +
               ("region = " + this.region + "\n") +
               ("locality = " + this.locality + "\n") +
               ("street = " + this.street + "\n") +
               ("building = " + this.building + "\n") +
               ("apartment = " + this.apartment + "\n") +
               ("active = " + this.active + "\n") +
               "}";
    }
}