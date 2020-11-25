package com.banksystem.domain.terminals;

import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.Exception.NeedAuthorizationException;
import com.banksystem.Exception.NotEnoughMoneyException;
import com.banksystem.domain.accounts.SderBankAccount;
import com.banksystem.domain.accounts.VtfBankAccount;
import com.banksystem.domain.users.User;

public class TazPromBankTerminal extends Terminal {
    public static final int PERCENT_FOR_SDERBANK = 12;
    public static final int PERCENT_FOR_VTF = 6;

    public TazPromBankTerminal(User user) {
        super(user);
    }

    public TazPromBankTerminal() {
    }


    public int deposit(int amount) throws MyAuthorizeException, NeedAuthorizationException {
        int rememberAmount = amount;
        if (successfulAuthorization) {
            if (currentAccount instanceof SderBankAccount) {
                calcCommission(amount, PERCENT_FOR_SDERBANK);
                currentAccount.deposit(amount -= bankCommission);
            } else if (currentAccount instanceof VtfBankAccount) {
                calcCommission(amount, PERCENT_FOR_VTF);
                currentAccount.deposit(amount -= bankCommission);
            } else
                currentAccount.deposit(amount);
            printResultOfOperation(rememberAmount, "Внесено");
            return currentAccount.getBalance();

        } else throw new MyAuthorizeException();
    }


    public int withdraw(int amount) throws NotEnoughMoneyException, MyAuthorizeException, NeedAuthorizationException {
        int rememberAmount = amount;
        if (currentAccount.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        } else if (!successfulAuthorization) {
            throw new MyAuthorizeException();
        } else {
            if (currentAccount instanceof SderBankAccount) {
                calcCommission(amount, PERCENT_FOR_SDERBANK);
                currentAccount.withdraw(amount += bankCommission);
            } else if (currentAccount instanceof VtfBankAccount) {
                calcCommission(amount, 6);
                currentAccount.withdraw(amount += bankCommission);
            } else
                currentAccount.withdraw(amount);
            printResultOfOperation(rememberAmount, "Выдано");
            return rememberAmount;
        }
    }
}
