package com.mycompany.app;

import java.util.Optional;

public class ConsoleInterfaceImpl implements ConsoleInterface {
    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public Optional<String> readLine() {
        var input = System.console().readLine();
        return Optional.ofNullable(input);
    }
}
