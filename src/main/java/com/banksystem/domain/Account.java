package com.banksystem.domain;

import com.banksystem.Exception.MyAccessException;
import com.banksystem.domain.users.User;

public class Account {
    private int balance;
    private CurrencyType currencyType;
    private int number;
    private int pinCode;
    private User user;

    public Account(CurrencyType currencyType, int number, int password) {
        this.currencyType = currencyType;
        this.number = number;
        this.pinCode = password;
    }

    public Account() {
    }

    public boolean getAccess(int pinCode) throws MyAccessException {
        if (this.pinCode == pinCode)
            return true;
        else throw new  MyAccessException();
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }


    public int getBalance(){
        return balance;
    }


    public int deposit(int amount){
        balance += amount;
        return balance;
    }

    public int withdraw(int amount){
        balance -= amount;
        return balance;
    }

}
