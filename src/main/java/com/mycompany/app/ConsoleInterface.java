package com.mycompany.app;

import java.util.Optional;

public class ConsoleInterface implements IConsoleInterface{
    @Override
    public void write(String text) {
        System.out.println(text);
    }

    @Override
    public Optional<String> readLine() {
        var input = System.console().readLine();
        return Optional.ofNullable(input);
    }
}
