package com.banksystem.domain.accounts;

import com.banksystem.Exception.MyAccessException;
import com.banksystem.domain.enums.CurrencyType;
import com.banksystem.domain.users.User;
import com.banksystem.domain.Quotes;

import java.util.HashMap;
import java.util.Map;

import static com.banksystem.domain.enums.CurrencyType.*;

public abstract class Account {
    private int balance;
    private CurrencyType currencyType;
    private int number;
    private int pinCode;
    private User user;
    private Map<String, Integer> mapQuotes = new HashMap<>();

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

    public void convertTo(CurrencyType currencyType){
        switch (currencyType){
            case DOLLAR:
                if(this.currencyType == CurrencyType.RUBLE){
                balance = (int) (balance/Quotes.RUB_TO_USD);
                }else if (this.currencyType == EURO) {
                    balance = (int) (balance / Quotes.EUR_TO_USD);
                }else System.out.println("Твоя валюта и так + " + currencyType);
                this.currencyType = DOLLAR;
                break;
            case EURO:
                if(this.currencyType == CurrencyType.RUBLE){
                    balance = (int) (balance/Quotes.RUB_TO_EUR);
                }else if (this.currencyType == DOLLAR) {
                    balance = (int) (balance / Quotes.USD_TO_EUR);
                }else System.out.println("Твоя валюта и так + " + currencyType);
                this.currencyType = EURO;
                break;
            case RUBLE:
                if(this.currencyType == DOLLAR){
                    balance = (int) (balance/Quotes.USD_TO_RUB);
                }else if (this.currencyType == EURO) {
                    balance = (int) (balance / Quotes.EUR_TO_RUB);
                }else System.out.println("Твоя валюта и так + " + currencyType);
                this.currencyType = RUBLE;
                break;
        }
        System.out.println("Твой баланс: " + balance + " " + this.currencyType );
    }
}
