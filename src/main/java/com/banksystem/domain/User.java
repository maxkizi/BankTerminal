package com.banksystem.domain;

import com.banksystem.domain.Account;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Map<String , Account> accountMap = new HashMap<>();


    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public void createAccount( String currencyType, int number, int pinCode, String accountName){
        Account account = new Account(currencyType, number, pinCode);
        accountMap.put(accountName, account);
    }
    public Map<String, Account> getAccounts(){
        return accountMap;
    }

}
