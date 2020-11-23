package com.banksystem.domain.users;

import com.banksystem.domain.Account;
import com.banksystem.domain.CurrencyType;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Map<Integer, Account> accountMap = new HashMap<>();


    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //создаем аккаунт(счет), у одного пользователя может быть несколько счетов
    //при создании счета указываем: тип валюты, номер счета(номер карты), пин кодж
    public void createAccount(CurrencyType currencyType, int number, int pinCode){
        Account account = new Account(currencyType, number, pinCode);
        accountMap.put(number, account);
    }
    public Map<Integer, Account> getAccounts(){
        return accountMap;
    }
}
