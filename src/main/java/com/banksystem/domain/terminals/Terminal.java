package com.banksystem.domain.terminals;
import com.banksystem.Exception.MyAccessException;
import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.Exception.NeedAuthorizationException;
import com.banksystem.Exception.NotEnoughMoneyException;
import com.banksystem.domain.accounts.Account;
import com.banksystem.domain.users.User;

import java.util.Map;

public abstract class Terminal {
    private User user;
    protected boolean successfulAuthorization;
    protected Account currentAccount;
    int bankCommission;

    public Terminal(User user) {
        this.user = user;
    }

    public Terminal() {

    }

    public boolean isSuccessfulAuthorization() {
        return successfulAuthorization;
    }

    private Account getCurrentAccount() {
        return currentAccount;
    }

    //При авторизации вводим номер счета(вставляем карту в банкомат), пин-код
    //по номеру карты(счета) вытаскиваем аккаунт из map
    //получаем доступ к аккаунту по пин коду
    public boolean authorize(int number, int pinCode) throws MyAuthorizeException {
        Map<Integer, Account> accountMap = user.getAccounts();
        currentAccount = accountMap.get(number);
        boolean accessToAccount = false;
        try {
            accessToAccount = currentAccount.getAccess(pinCode);
        } catch (MyAccessException e) {
            System.out.println("Неверный логин(номер)/пароль");
            e.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("Возможно номер счета введен неправильно (Вставьте карту правильно)");
        }

        if (accessToAccount) {
            successfulAuthorization = true;
            System.out.println("Авторизация прошла успешно");
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

    void printResultOfOperation(int amount, String operation) throws NeedAuthorizationException {
        System.out.println("Операция прошла успешно");
        System.out.println(operation + " " + amount + " " + currentAccount.getCurrencyType());
        System.out.println("Комиссия: " + bankCommission);
        System.out.println("Ваш баланс: " + getBalance());
    }

    int calcCommission(int amount, int percent) {
        bankCommission = 0;
        return bankCommission = amount * percent / 100;
    }

    public abstract int deposit(int amount) throws MyAuthorizeException, NeedAuthorizationException;

    public abstract int withdraw(int amount) throws NotEnoughMoneyException, MyAuthorizeException, NeedAuthorizationException;

}
