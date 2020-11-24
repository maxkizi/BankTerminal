package com.banksystem.domain.terminals;

import com.banksystem.Exception.MyAuthorizeException;
import com.banksystem.Exception.NeedAuthorizationException;
import com.banksystem.Exception.NotEnoughMoneyException;
import com.banksystem.domain.accounts.SderBankAccount;

import static com.banksystem.domain.accounts.SderBankAccount.PERCENT_FOR_OTHERS_BANKS;


public class SderBankTerminal extends Terminal {


    public int deposit(int amount) throws MyAuthorizeException, NeedAuthorizationException {
        if (successfulAuthorization) {
            if (currentAccount instanceof SderBankAccount) {
                currentAccount.deposit(amount);
            } else {
                calcCommission(amount, PERCENT_FOR_OTHERS_BANKS);
                currentAccount.deposit( amount -= bankCommission);
            }
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
                currentAccount.withdraw(amount);
            } else {
                calcCommission(amount, PERCENT_FOR_OTHERS_BANKS);
                amount += bankCommission;
                currentAccount.withdraw(amount);
            }
            printResultOfOperation(amount - bankCommission, "Выдано");
            return currentAccount.getBalance();
        }
    }
}
