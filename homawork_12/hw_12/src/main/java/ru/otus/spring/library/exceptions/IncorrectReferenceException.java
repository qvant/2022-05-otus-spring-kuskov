package ru.otus.spring.library.exceptions;

public class IncorrectReferenceException extends RuntimeException {
    public IncorrectReferenceException() {
    }

    ;

    public IncorrectReferenceException(String message) {
        super(message);
    }

    ;
}
