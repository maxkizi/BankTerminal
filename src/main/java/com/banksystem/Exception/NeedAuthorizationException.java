package com.banksystem.Exception;

public class NeedAuthorizationException extends Exception {
    public NeedAuthorizationException(){
        super("Нужно авторизоваться");
    }
}
