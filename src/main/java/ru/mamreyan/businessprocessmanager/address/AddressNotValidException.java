package ru.mamreyan.businessprocessmanager.address;

class AddressNotValidException extends RuntimeException {
    public AddressNotValidException() {
        super("Address is not valid");
    }
}