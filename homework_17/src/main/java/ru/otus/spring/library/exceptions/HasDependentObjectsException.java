package ru.otus.spring.library.exceptions;

public class HasDependentObjectsException extends Exception {
    public HasDependentObjectsException(String errorMessage) {
        super(errorMessage);
    }
}
