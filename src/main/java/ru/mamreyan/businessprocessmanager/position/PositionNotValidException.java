package ru.mamreyan.businessprocessmanager.position;

public class PositionNotValidException extends RuntimeException {
    public PositionNotValidException(Position position) {
        super("Position is not valid: " + position.toString());
    }
}