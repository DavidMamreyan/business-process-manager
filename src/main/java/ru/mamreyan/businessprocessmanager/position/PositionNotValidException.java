package ru.mamreyan.businessprocessmanager.position;

public class PositionNotValidException extends RuntimeException {
    public PositionNotValidException() {
        super("Position is not valid");
    }
}