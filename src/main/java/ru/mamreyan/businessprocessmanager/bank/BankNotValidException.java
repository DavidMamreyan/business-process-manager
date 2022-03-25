package ru.mamreyan.businessprocessmanager.bank;

class BankNotValidException extends RuntimeException {
    public BankNotValidException() {
        super("Bank is not valid");
    }
}