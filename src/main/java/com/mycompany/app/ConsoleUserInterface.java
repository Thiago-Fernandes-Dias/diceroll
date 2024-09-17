package com.mycompany.app;

import java.util.Optional;
import java.util.function.Function;

public class ConsoleUserInterface implements UserInterface {
    public ConsoleUserInterface(ConsoleInterfaceImpl consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    private final ConsoleInterface consoleInterface;

    public int readInteger(String message) {
        return readInteger(message, r -> true);
    }

    public int readInteger(String message, Function<Integer, Boolean> validationDel)
    {
        while (true)
        {
            consoleInterface.write(message);
            Optional<String> input = consoleInterface.readLine();
            if (input.isEmpty()) {
                consoleInterface.write(Messages.incorrectInput + System.lineSeparator());
                continue;
            }
            Optional<Integer> number = Helpers.parseInt(input.get());
            if (number.isEmpty()) {
                consoleInterface.write(Messages.incorrectInput + System.lineSeparator());
                continue;
            }
            if (!validationDel.apply(number.get())) {
                consoleInterface.write(Messages.incorrectInput + System.lineSeparator());
                continue;
            }
            return number.get();
        }
    }

    @Override
    public String readString(String message)
    {
        return readString(message, r -> true);
    }

    @Override
    public String readString(String message, Function<String, Boolean> validationDel)
    {
        while (true)
        {
            consoleInterface.write(message);
            Optional<String> result = consoleInterface.readLine();
            if (result.isEmpty()) {
                consoleInterface.write(Messages.incorrectInput + System.lineSeparator());
                continue;
            }
            if (!validationDel.apply(result.get())) {
                consoleInterface.write(Messages.incorrectInput + System.lineSeparator());
            }
            return result.get();
        }
    }

    @Override
    public void showMessage(String message)
    {
        consoleInterface.write(message + System.lineSeparator());
    }
}
