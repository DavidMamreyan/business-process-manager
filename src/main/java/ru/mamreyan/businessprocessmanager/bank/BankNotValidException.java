package ru.mamreyan.businessprocessmanager.bank;

class BankNotValidException extends RuntimeException {
    public BankNotValidException(Bank bank) {
        super("Bank is not valid: " + bank.toString());
    }
}