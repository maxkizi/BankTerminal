package com.banksystem;

public class Account {
    private int balance;
    private String currencyType;
    private int number;
    private int pinCode;


    public Account(String currencyType, int number, int password) {
        this.currencyType = currencyType;
        this.number = number;
        this.pinCode = password;
    }

    public Account() {
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setPinCode(int pinCode){
        this.pinCode = pinCode;
    }

    public int deposit(int amount){
        balance += amount;
        return balance;
    }

    public int withdraw(int amount){
        balance -= amount;
        return balance;
    }

    public int getBalance(){
        return balance;
    }
}
