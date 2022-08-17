package ru.otus.spring.exam.service;

public interface IOService {
    void print(String text);

    void printWithParameters(String text, Object ...args);

    String read();
}
