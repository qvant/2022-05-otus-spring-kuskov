package ru.otus.spring.exam.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@Service
public class IOServiceConsole implements IOService {
    private final PrintStream out;
    private final InputStream in;
    private final BufferedReader bufferedReader;

    public IOServiceConsole() throws java.io.UnsupportedEncodingException {
        this.out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        this.in = System.in;
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.in));
    }

    @Override
    public void print(String text) {
        out.println(text);
    }

    @Override
    public void printWithParameters(String text, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5) {
        out.printf(text, obj1, obj2, obj3, obj4, obj5);
        out.println();
    }

    @Override
    public void printWithParameters(String text, Object obj1, Object obj2, Object obj3, Object obj4) {
        out.printf(text, obj1, obj2, obj3, obj4);
        out.println();
    }

    @Override
    public String read() {
        String answer;
        try {
            answer = this.bufferedReader.readLine();
        } catch (Exception e) {
            answer = "";
        }
        return answer;
    }
}
