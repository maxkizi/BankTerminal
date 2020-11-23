package com.banksystem.Exception;

public class MyAuthorizeException extends Exception {
    public MyAuthorizeException(){
    super("Неверынй логин/пароль");
}
}
