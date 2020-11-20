package com.banksystem;

import java.lang.reflect.Field;
import java.util.Map;

public class Terminal {
    User user;

    Terminal(){

    }

    public void authorize(int number, int pinCode, String accountName){
        Map<String, Account> accountMap = user.getAccountMap();
        Account account = accountMap.get(accountName);

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

    void getPinAndNumber(){
        String className = "User";
        try {
            Class userClass = Class.forName("User");
            Field pinCode = userClass.getDeclaredField("pinCode");
            Field number = userClass.getDeclaredField("number");

        } catch (ClassNotFoundException e) {
            System.out.println("Не могу найти класс с именем " + className);
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
