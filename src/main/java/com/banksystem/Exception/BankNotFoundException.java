package com.banksystem.Exception;

public class BankNotFoundException extends Throwable {
    public BankNotFoundException(){
        super("Данного банка нет в списке");
    }
}
