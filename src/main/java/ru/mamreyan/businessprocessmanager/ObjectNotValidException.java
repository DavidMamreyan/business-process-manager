package ru.mamreyan.businessprocessmanager;

public class ObjectNotValidException extends RuntimeException {
    public ObjectNotValidException() {
        super("Object is not valid");
    }
}