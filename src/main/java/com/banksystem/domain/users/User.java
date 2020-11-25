package com.banksystem.domain.users;

import com.banksystem.Exception.BankNotFoundException;
import com.banksystem.domain.accounts.Account;
import com.banksystem.domain.enums.BankName;
import com.banksystem.domain.enums.CurrencyType;
import com.banksystem.domain.accounts.factory.AccountFactory;
import com.banksystem.domain.accounts.factory.SderBankAccountFactory;
import com.banksystem.domain.accounts.factory.TazPromBankAccountFactory;
import com.banksystem.domain.accounts.factory.VtfBankAccountFactory;

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
    //при создании счета указываем: тип валюты, номер счета(номер карты), пин код
    public void createAccount(CurrencyType currencyType, int number, int pinCode, BankName nameOfBankName)
            throws BankNotFoundException {
        AccountFactory accountFactory = createAccountFactory(nameOfBankName);
        Account account = accountFactory.createAccount(currencyType, number, pinCode);
        accountMap.put(number, account);
    }

    public Map<Integer, Account> getAccounts() {
        return accountMap;
    }

    public void transfer(Account donor, Account acceptor){

    }

    private AccountFactory createAccountFactory(BankName nameOfBank) throws BankNotFoundException {

        switch (nameOfBank) {
            case SDER:
                return new SderBankAccountFactory();
            case VTF:
                return new VtfBankAccountFactory();
            case TAZPROM:
                return new TazPromBankAccountFactory();
            default:
                throw new BankNotFoundException();
        }

    }
}
