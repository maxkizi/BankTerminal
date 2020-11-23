package com.banksystem.domain;

import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.Exception.NeedAuthorizationException;
import com.banksystem.Exception.NotEnoghMoneyException;

import java.util.Map;

public class Terminal {
    private User user;
    private boolean successfulAuthorization;
    private Account currentAccount;

    public Terminal(User user) {
        this.user = user;
    }

    public boolean authorize(int number, int pinCode, String accountName) throws MyAuthorizeException {
        Map<String, Account> accountMap = user.getAccounts();
        currentAccount = accountMap.get(accountName);
        if (currentAccount.getNumber() == number && currentAccount.getPinCode() == pinCode) {
            System.out.println("Добро рожаловать");
            successfulAuthorization = true;
            return true;
        } else {
            successfulAuthorization = false;
            throw new MyAuthorizeException();
        }
    }

    public int getBalance() throws NeedAuthorizationException {
        if (successfulAuthorization) {
            return currentAccount.getBalance();
        } else throw new NeedAuthorizationException();
    }

    public int deposit(int amount) throws MyAuthorizeException {
        if (successfulAuthorization) {

            return currentAccount.deposit(amount);
        } else throw new MyAuthorizeException();
    }

    public int withdraw(int amount) throws NotEnoghMoneyException, MyAuthorizeException {
        if (currentAccount.getBalance() < amount){
            throw new NotEnoghMoneyException();
        }
        if (successfulAuthorization){
            return  currentAccount.withdraw(amount);
        }else throw new MyAuthorizeException();
    }


}
