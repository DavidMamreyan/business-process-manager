package ru.mamreyan.businessprocessmanager.address;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Long id) {
        super("Could not find address " + id);
    }
}