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

    public IOServiceConsole(){
        this.out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        this.in = System.in;
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.in));
    }

    @Override
    public void print(String text) {
        out.println(text);
    }

    @Override
    public void printWithParameters(String text, Object ...args) {
        out.printf(text, args);
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
