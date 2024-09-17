package com.mycompany.app;

import java.util.function.Function;

public interface UserInterface {
    public void showMessage(String message);

    public int readInteger(String message);

    public String readString(String message);

    public int readInteger(String message, Function<Integer, Boolean> validationDel);

    public String readString(String message, Function<String, Boolean> validationDel);
}
