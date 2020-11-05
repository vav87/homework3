package ru.digitalhabbits.homework3.model;

import javax.persistence.PersistenceException;

public class ConflictException extends PersistenceException {

    public ConflictException() {
        super();
    }

    public ConflictException(String message) {
        super(message);
    }
}
