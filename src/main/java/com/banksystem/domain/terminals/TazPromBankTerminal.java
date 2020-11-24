package com.banksystem.domain.terminals;

import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.Exception.NeedAuthorizationException;
import com.banksystem.Exception.NotEnoughMoneyException;
import com.banksystem.domain.accounts.SderBankAccount;
import com.banksystem.domain.accounts.TazPromBankAccount;
import com.banksystem.domain.accounts.VtfBankAccount;

public class TazPromBankTerminal extends Terminal {
    public int deposit(int amount) throws MyAuthorizeException, NeedAuthorizationException {
        if (successfulAuthorization) {
            if (currentAccount instanceof SderBankAccount) {
              calcCommission(amount, TazPromBankAccount.PERCENT_FOR_SDERBANK);
                currentAccount.deposit(amount -= bankCommission);
            } else if (currentAccount instanceof VtfBankAccount) {
                calcCommission(amount, TazPromBankAccount.PERCENT_FOR_VTF);
                currentAccount.deposit(amount -= bankCommission);
            } else
                currentAccount.deposit(amount);
            printResultOfOperation(amount, "Внесено");
            return currentAccount.getBalance();

        } else throw new MyAuthorizeException();
    }

    public int withdraw(int amount) throws NotEnoughMoneyException, MyAuthorizeException, NeedAuthorizationException {
        if (currentAccount.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        } else if (!successfulAuthorization) {
            throw new MyAuthorizeException();
        } else {
            if (currentAccount instanceof SderBankAccount) {
                calcCommission(amount, TazPromBankAccount.PERCENT_FOR_SDERBANK);
                currentAccount.withdraw(amount += bankCommission);
            } else if (currentAccount instanceof VtfBankAccount) {
                calcCommission(amount, TazPromBankAccount.PERCENT_FOR_VTF);
                currentAccount.withdraw(amount -= bankCommission);
            }else
            currentAccount.withdraw(amount);
            printResultOfOperation(amount - bankCommission, "Выдано");
            return currentAccount.getBalance();
        }
    }
}
