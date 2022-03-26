package ru.mamreyan.businessprocessmanager.address;

class AddressNotValidException extends RuntimeException {
    public AddressNotValidException(Address address) {
        super("Address is not valid: " + address.toString());
    }
}