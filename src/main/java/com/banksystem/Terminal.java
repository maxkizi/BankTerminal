package com.banksystem;

import java.lang.reflect.Field;
import java.util.Map;

public class Terminal {
    User user;

    Terminal(){

    }

    public void authorize(int number, int pinCode, String accountName){
        Map<String, Account> accountMap = user.getAccounts();
        Account account = accountMap.get(accountName);
        getPinAndNumber(account);

    }

    public int getBalance(){
        return 0;
    }

    public int deposit(){
        return 0;
    }

    public int withdraw(){
        return 0;
    }

    void getPinAndNumber(Account account){

    }
}
