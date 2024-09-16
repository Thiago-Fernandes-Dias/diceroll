package com.mycompany.app;

import java.util.Optional;

public interface IConsoleInterface {
    public void write(String text);

    public Optional<String> readLine();
}
