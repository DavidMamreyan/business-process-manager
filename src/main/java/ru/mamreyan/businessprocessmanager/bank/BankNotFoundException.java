package ru.mamreyan.businessprocessmanager.bank;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException(Long id) {
        super("Could not find bank " + id);
    }
}