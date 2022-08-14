package ru.otus.spring.exam.service;

public interface IOService {
    void print(String text);

    void printWithParameters(String text, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5);

    void printWithParameters(String text, Object obj1, Object obj2, Object obj3, Object obj4);

    String read();
}
